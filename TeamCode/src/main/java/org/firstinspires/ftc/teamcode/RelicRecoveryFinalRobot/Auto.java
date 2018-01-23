package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;


import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.CameraCalibration;
import com.vuforia.Image;
import com.vuforia.Matrix34F;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Tool;
import com.vuforia.Vec3F;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Old_Robots.RelicRecovery.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia.PineappleRelicRecoveryVuforia;
import org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.column;
import org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.glyph;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia.PineappleRelicRecoveryVuforia.SaveImage;
import static org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia.PineappleRelicRecoveryVuforia.matToBitmap;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.glyph.NONE;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.jewel.jewelHitSide.LEFT;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.jewel.jewelHitSide.RIGHT;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.jewel.jewelState.BLUE_RED;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.jewel.jewelState.NON_NON;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.jewel.jewelState.RED_BLUE;

/**
 * Created by Brandon on 1/8/2018.
 */
@Autonomous(name = "AUTO")
public class Auto extends Config {

    enum InitEnum {
        GYRO, VUFORIA, LOOP
    }

    enum AutoEnum {
        WAIT,
        JEWELS, JEWELDOWN, JEWELHIT, JEWELUP,
        ALIGN, ALIGNDRIVEOFFPLATFORM, ALIGNTURN, ALIGNDRIVEINTOCRYPTO,
        KEYCOLUMNSET,
        GLYPH, GLYPHARMDOWN, GLYPHSTRAFFTOCOLUMN, GLYPHDRIVETOCRYPTO, GLYPHSTRAFFTOALIGN, GLYPHBOTHARMSDOWN, GLYPHPLACE, GLYPHPLACERESET,
        COLLECT, COLLECTDRIVEBACKFROMCRYPTO, COLLECTSTRAFFTOCENTER, COLLECTSTARTTRACKING, COLLECTGOTOPIT, COLLECTGLYPHS, COLLECTRETRACESTEPS, ALIGNBACKINTOPLATFORM, ALIGNDRIVETOCENTER, COLLECTPROCESSFORPLACING
    }

    ElapsedTime wait = new ElapsedTime();

    AutoEnum auto = AutoEnum.WAIT;
    InitEnum init = InitEnum.GYRO;
    RelicRecoveryVuMark targetColumn = RelicRecoveryVuMark.CENTER;
    RelicRecoveryVuMark keyColumn = RelicRecoveryVuMark.UNKNOWN;

    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;
    VuforiaTrackableDefaultListener listener;

    boolean vuforiaInitialized = false;
    boolean imageVisible = false;

    double PIDgyroCorrectionValue = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        //INIT LOOP
        while (!opModeIsActive() && !isStopRequested()) {
            //Switch Board Loading
            loadSwitchBoard();

            //Telemetry
            telemetry.addData("AUTO", init);
            telemetry.addLine();
            displaySwitchBorad();
            telemetry.addLine();
            telemetry.addLine(FontFormating.getMark(calibration_complete) + "GYRO CALIBRATION");
            telemetry.addLine(FontFormating.getMark(vuforiaInitialized) + "VUFORIA");
            telemetry.addLine(FontFormating.getMark(imageVisible) + "IMAGE VISIBLE-" + keyColumn);
            switch (init) {
                case GYRO:
                    calibration_complete = !navx_device.isCalibrating();
                    if (!calibration_complete) {
                    } else {
                        navx_device.zeroYaw();
                        init = InitEnum.VUFORIA;
                    }
                    break;
                case VUFORIA:
                    int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
                    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
                    parameters.vuforiaLicenseKey = "AdB8VB7/////AAAAGcfBp9I80URFkfBQFUyM+ptmQXBAMGx0svJKz7QE2nm20mBc/zI5sZNHfuP/ziIm+sYnO7fvPqUbFs8QWjRyXVEDmW4mMj+S+l+yaYRkpGZ/pmHyXiDb4aemHx0m70BulMNIce4+NVaCW5S/5BWNNev/AU0P+uWnHYuKNWzD2dPaRuprC4R6b/DgD1zeio1xlssYb9in9mfzn76gChOrE5B0ql6Q9FiHC5cTdacq2lKjm5nlkTiXz1e2jhVK3SddGoqM4FQ3mFks7/A88hFzlPfIIk45K2Lh7GvcVjuIiqNj5mTLaZJVqlsKdTQnKS4trJcc1YV9sjdbmh1agtn1UePy91fDj9uWSBdXvpIowv4B";
                    parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
                    vuforia = ClassFactory.createVuforiaLocalizer(parameters);
                    vuforia.setFrameQueueCapacity(1);
                    Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
                    relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
                    relicTemplate = relicTrackables.get(0);
                    relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
                    listener = (VuforiaTrackableDefaultListener) relicTemplate.getListener();
                    relicTrackables.activate();
                    vuforiaInitialized = true;
                    init = InitEnum.LOOP;
                    break;
                case LOOP:
                    if (listener.getPose() != null) {
                        imageVisible = true;
                        keyColumn = RelicRecoveryVuMark.from(relicTemplate);
                        jewelState = getJewelConfig(PineappleRelicRecoveryVuforia.getImageFromFrame(vuforia.getFrameQueue().take(), PIXEL_FORMAT.RGB565), listener, vuforia.getCameraCalibration(), telemetry);

                        switch (jewelState) {
                            case NON_NON:
                                telemetry.addData("Jewel State", "NON NON");
                                break;
                            case BLUE_RED:
                                telemetry.addData("Jewel State", "RED BLUE");
                                break;
                            case RED_BLUE:
                                telemetry.addData("Jewel State", "BLUE RED");
                                break;
                        }
                    } else {
                        imageVisible = false;
                    }
                    break;
            }
            telemetry.update();
        }

        waitForStart();
        wait.reset();
        double TARGETANGLE = 0;
        //MAIN LOOP
        while (opModeIsActive()) {
            //Always On Telemetry
            telemetry.addData("AUTO: ", auto);
            telemetry.addData("GYRO→TARGET: ", navx_device.getYaw()+"→"+TARGETANGLE);

            yawPIDController.setSetpoint(TARGETANGLE);
            if (yawPIDController.isNewUpdateAvailable(yawPIDResult)) {
                if (yawPIDResult.isOnTarget()) {
                } else {
                    double output = yawPIDResult.getOutput();
                }
            }



            switch (auto) {
                case WAIT:
                    if (!switchDelayEnabled || wait.seconds() >= slideDelay) {
                        auto = AutoEnum.JEWELS;
                    }
                    break;
                case JEWELS:
                    if (!switchJewels) {
                        auto = AutoEnum.ALIGN;
                    } else {
                        auto = AutoEnum.JEWELDOWN;
                        wait.reset();
                    }
                    break;
                case JEWELDOWN:
                    if (wait.milliseconds() > jewelDown()) {
                        auto = AutoEnum.JEWELHIT;
                    }
                    jewelCSLEDON();
                    break;
                case JEWELHIT:
                    if (wait.milliseconds() > jewelHit()) {
                        auto = AutoEnum.JEWELUP;
                    }
                    break;
                case JEWELUP:
                    if (wait.milliseconds() > jewelUp()) {
                        auto = AutoEnum.ALIGNDRIVEOFFPLATFORM;
                    }
                    jewelCSLEDOFF();

                    break;
                case ALIGNDRIVEOFFPLATFORM:
                    if (true) {
                        wait.reset();
                        auto = AutoEnum.ALIGNBACKINTOPLATFORM;
                    }
                    break;
                case ALIGNBACKINTOPLATFORM:

                    if (wait.milliseconds() > 500) {
                        auto = AutoEnum.ALIGNDRIVETOCENTER;
                    }

                    break;
                case ALIGNTURN:
                    break;
                case ALIGNDRIVEINTOCRYPTO:
                    break;
                case KEYCOLUMNSET:

                    if (switchKeyColumn && keyColumn != RelicRecoveryVuMark.UNKNOWN) {
                        targetColumn = keyColumn;
                    } else {
                        //default column set here based on position
                    }
                    auto = AutoEnum.GLYPH;
                    break;
                case GLYPH:
                    break;
                case GLYPHARMDOWN:
                    break;
                case GLYPHSTRAFFTOCOLUMN:
                    break;
                case GLYPHDRIVETOCRYPTO:
                    break;
                case GLYPHSTRAFFTOALIGN:
                    break;
                case GLYPHBOTHARMSDOWN:
                    break;
                case GLYPHPLACE:
//                    addGlyphsToColumn(COLUMN, FIRST GLYPH COLOR, SECOND GLYPH COLOR);
                    break;
                case GLYPHPLACERESET:
                    break;
                case COLLECT:
                    break;
                case COLLECTDRIVEBACKFROMCRYPTO:
                    break;
                case COLLECTSTRAFFTOCENTER:
                    break;
                case COLLECTSTARTTRACKING:
                    break;
                case COLLECTGOTOPIT:
                    break;
                case COLLECTGLYPHS:
                    break;
                case COLLECTRETRACESTEPS:
                    break;
                case COLLECTPROCESSFORPLACING:
//                    getColumn(FIRST GLYPH COLOR, SECOND GLYPH COLOR);
                    break;
            }
            telemetry.update();
        }
    }

    //JEWEL FUNCTIONS HERE
    public void jewelCSLEDON() {
        csJewelRight.getValue(PineappleEnum.PineappleSensorEnum.CSLEDON);
        csJewelLeft.getValue(PineappleEnum.PineappleSensorEnum.CSLEDON);

    }

    public void jewelCSLEDOFF() {
        csJewelRight.getValue(PineappleEnum.PineappleSensorEnum.CSLEDOFF);
        csJewelLeft.getValue(PineappleEnum.PineappleSensorEnum.CSLEDOFF);

    }

    public int jewelDown() {
        servoJewel.setPosition(Constants.auto.jewel.JEWELDOWN);
        servoJewelHit.setPosition(Constants.auto.jewel.JEWELHITCENTER);
        return Constants.auto.jewel.JEWELDOWNMILI;
    }

    public int jewelUp() {
        servoJewel.setPosition(Constants.auto.jewel.JEWELUP);
        servoJewelHit.setPosition(Constants.auto.jewel.JEWELHITCENTER);
        return Constants.auto.jewel.JEWELUPMILI;
    }

    public int jewelHit() {
        switch (jewelHitSide()) {
            case RIGHT:
                servoJewelHit.setPosition(Constants.auto.jewel.JEWELHITRIGHT);
                return Constants.auto.jewel.JEWELHITMILI;
            case LEFT:
                servoJewelHit.setPosition(Constants.auto.jewel.JEWELHITLEFT);
                return Constants.auto.jewel.JEWELHITMILI;
            case NONE:
                return 0;
            default:
                return 0;
        }

    }

    public Constants.auto.jewel.jewelHitSide jewelHitSide() {
        Constants.auto.jewel.jewelState left = getLeftCSJewelState();
        Constants.auto.jewel.jewelState right = getRightCSJewelState();
        Constants.auto.jewel.jewelState state;
        if (left == right) {
            if (left != NON_NON) {
                state = left;
            } else {
                state = jewelState;
            }

        } else if (left != NON_NON && left == jewelState) {
            state = left;
        } else if (right != NON_NON && right == jewelState) {
            state = right;
        } else if (left == NON_NON && jewelState == NON_NON && right != NON_NON) {
            state = right;
        } else if (right == NON_NON && jewelState == NON_NON && left != NON_NON) {
            state = left;
        } else {
            state = NON_NON;
        }
        if (state == NON_NON) {
            return Constants.auto.jewel.jewelHitSide.NONE;
        }
        return (switchColor == RelicRecoveryEnums.AutoColor.RED) ? (state == RED_BLUE) ? RIGHT : LEFT : (state == RED_BLUE) ? LEFT : RIGHT;

    }

    private Constants.auto.jewel.jewelState getLeftCSJewelState() {
        if (csJewelLeft.getValue(PineappleEnum.PineappleSensorEnum.CSBLUE) > csJewelLeft.getValue(PineappleEnum.PineappleSensorEnum.CSRED)) {
            return BLUE_RED;
        } else if (csJewelLeft.getValue(PineappleEnum.PineappleSensorEnum.CSBLUE) < csJewelLeft.getValue(PineappleEnum.PineappleSensorEnum.CSRED)) {
            return RED_BLUE;
        } else {
            return NON_NON;
        }
    }

    private Constants.auto.jewel.jewelState getRightCSJewelState() {

        if (csJewelRight.getValue(PineappleEnum.PineappleSensorEnum.CSBLUE) < csJewelRight.getValue(PineappleEnum.PineappleSensorEnum.CSRED)) {
            return BLUE_RED;
        } else if (csJewelRight.getValue(PineappleEnum.PineappleSensorEnum.CSBLUE) > csJewelRight.getValue(PineappleEnum.PineappleSensorEnum.CSRED)) {
            return RED_BLUE;
        } else {
            return NON_NON;
        }
    }

    public static Constants.auto.jewel.jewelState getJewelConfig(Image img, VuforiaTrackableDefaultListener track, CameraCalibration camCal, Telemetry telemetry) {
        try {
            OpenGLMatrix pose = track.getRawPose();
            if (pose != null && img != null && img.getPixels() != null) {
                Matrix34F rawPose = new Matrix34F();
                float[] poseData = Arrays.copyOfRange(pose.transposed().getData(), 0, 12);
                rawPose.setData(poseData);
                float[][] corners = new float[4][2];
                corners[0] = Tool.projectPoint(camCal, rawPose, new Vec3F(120, -55, 50)).getData();//UL TODO FIND NEW LOCATIONS
                corners[1] = Tool.projectPoint(camCal, rawPose, new Vec3F(340, -55, 50)).getData();//UR TODO FIND NEW LOCATIONS
                corners[2] = Tool.projectPoint(camCal, rawPose, new Vec3F(340, -300, 50)).getData();//LR TODO FIND NEW LOCATIONS
                corners[3] = Tool.projectPoint(camCal, rawPose, new Vec3F(120, -300, 50)).getData();//LL TODO FIND NEW LOCATIONS
                Bitmap bm = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);
                ByteBuffer pix = img.getPixels();
                bm.copyPixelsFromBuffer(pix);
                SaveImage(bm, "original");
                Mat crop = new Mat(bm.getHeight(), bm.getWidth(), CvType.CV_8UC3);
                Utils.bitmapToMat(bm, crop);
                float x = Math.min(Math.min(corners[1][0], corners[3][0]), Math.min(corners[0][0], corners[2][0]));
                float y = Math.min(Math.min(corners[1][1], corners[3][1]), Math.min(corners[0][1], corners[2][1]));
                float width = Math.max(Math.abs(corners[0][0] - corners[2][0]), Math.abs(corners[1][0] - corners[3][0]));
                float height = Math.max(Math.abs(corners[0][1] - corners[2][1]), Math.abs(corners[1][1] - corners[3][1]));
                x = Math.max(x, 0);
                y = Math.max(y, 0);
                if (width < 20 || height < 20) {
                    return NON_NON;
                }
                width = (x + width > crop.cols()) ? crop.cols() - x : width;
                height = (x + height > crop.rows()) ? crop.rows() - x : height;
                Mat cropped = new Mat(crop, new Rect((int) x, (int) y, (int) width, (int) height));
                SaveImage(matToBitmap(cropped), "crop");
                Imgproc.cvtColor(cropped, cropped, Imgproc.COLOR_RGB2HSV_FULL);
                Mat mask = new Mat();
                Core.inRange(cropped, new Scalar(50, 20, 70), new Scalar(255, 255, 120), mask);
                SaveImage(matToBitmap(mask), "mask");
                Moments mmnts = Imgproc.moments(mask, true);
                if ((mmnts.get_m10() / mmnts.get_m00()) < cropped.cols() / 2) {
                    return BLUE_RED;
                } else {
                    return RED_BLUE;
                }

            }
            return NON_NON;
        } catch (Exception e)

        {
            return NON_NON;
        }
    }

    //ALIGN FUNCTIONS HERE


    //GLYPH FUNCTIONS HERE
    public column getColumn(glyph firstGlyph, glyph secondGlyph) {
        int[] cipherPoints = new int[3];
        for (int i = 0; i < 3; i++) {
            if (canGlyphsGoInColumn(i, firstGlyph, secondGlyph)) {
                glyph[][] potBox = addGlyphsToColumnAlg(i, BOX, firstGlyph, secondGlyph);
                boolean[] workingCipher = {true, true, true, true, true, true};
                for (int k = 0; k < 6; k++) {
                    for (int j = 0; j < 4; j++) {
                        for (int l = 0; l < 3; l++) {
                            if (potBox[j][l] != NONE && potBox[j][l] != Constants.auto.autoGlyph.CIPHERS[k][j][l]) {
                                j = 5;
                                l = 4;
                                workingCipher[k] = false;
                            }
                        }
                    }
                }
                cipherPoints[i] = cipherPointsFinder(workingCipher);
            }
        }
        int max = cipherPoints[0];
        for (int i = 1; i < cipherPoints.length; i++) {
            if (cipherPoints[i] > max) {
                max = cipherPoints[i];
            }
        }
        switch (max) {
            case 0:
                return column.RIGHT;
            case 1:
                return column.CENTER;
            case 2:
                return column.LEFT;
            default:
                return column.LEFT;

        }
    }

    private static glyph[][] addGlyphsToColumnAlg(int column, glyph[][] box, glyph firstGlyph, glyph secondGlyph) {
        for (int i = 0; i < 4; i++) {
            if (box[i][column] != NONE) {
                if (secondGlyph != NONE) {
                    box[i - 1][column] = secondGlyph;
                    box[i - 2][column] = firstGlyph;
                } else {
                    box[i - 1][column] = firstGlyph;
                }
                i = 5;
            }
        }
        return box;
    }

    public void addGlyphsToColumn(column column, glyph firstGlyph, glyph secondGlyph) {
        int columnNumb = 1;
        switch (column) {
            case LEFT:
                columnNumb = 0;
                break;
            case CENTER:
                columnNumb = 1;
                break;
            case RIGHT:
                columnNumb = 2;
                break;
        }
        for (int i = 0; i < 4; i++) {
            if (BOX[i][columnNumb] != NONE) {
                if (secondGlyph != NONE) {
                    BOX[i - 1][columnNumb] = secondGlyph;
                    BOX[i - 2][columnNumb] = firstGlyph;
                } else {
                    BOX[i - 1][columnNumb] = firstGlyph;
                }
                i = 5;
            }
        }
    }

    private boolean canGlyphsGoInColumn(int column, glyph firstGlyph, glyph secondGlyph) {
        int numbOfGlyphs = 0;
        if (firstGlyph != NONE) {
            numbOfGlyphs++;
        }
        if (secondGlyph != NONE) {
            numbOfGlyphs++;
        }
        int numbOfAvalibaleSpot = 0;
        for (int i = 0; i < 4; i++) {
            if (BOX[i][column] == NONE) {
                numbOfAvalibaleSpot++;
            }
        }
        return numbOfAvalibaleSpot <= numbOfGlyphs;
    }

    private static int cipherPointsFinder(boolean[] cipher) {
        int val = 0;
        int points = 0;
        for (boolean cipherBool : cipher) {
            if (cipherBool) {
                points += (val == 0 || val == 1) ? 4 : (val == 2 || val == 3) ? 2 : 1;
            }
            val++;
        }
        return points;
    }

    //COLLECT FUNCTIONS HERE


    //GENERAL FUNCTIONS HERE

}
