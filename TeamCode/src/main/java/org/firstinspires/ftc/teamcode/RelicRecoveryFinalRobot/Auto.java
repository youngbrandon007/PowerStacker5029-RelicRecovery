package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

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
        JEWELS, JEWELDOWN, JEWELPROCESS, JEWELHIT, JEWELUP, JEWELRESET,
        ALIGN,ALIGNDRIVEOFFPLATFORM, ALIGNTURN, ALIGNDRIVEINTOCRYPTO,
        KEYCOLUMNSET,
        GLYPH,GLYPHARMDOWN, GLYPHSTRAFFTOCOLUMN, GLYPHDRIVETOCRYPTO, GLYPHSTRAFFTOALIGN, GLYPHBOTHARMSDOWN, GLYPHPLACE, GLYPHPLACERESET,
        COLLECT, COLLECTDRIVEBACKFROMCRYPTO, COLLECTSTRAFFTOCENTER, COLLECTSTARTTRACKING, COLLECTGOTOPIT, COLLECTGLYPHS, COLLECTRETRACESTEPS, COLLECTPROCESSFORPLACING
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
                    } else {
                        imageVisible = false;
                    }
                    break;
            }
            telemetry.update();
        }

        waitForStart();
        wait.reset();

        //MAIN LOOP
        while (opModeIsActive()) {
            telemetry.addData("AUTO", auto);
            switch (auto) {
                case WAIT:
                    if (!switchDelayEnabled || wait.seconds() >= slideDelay){
                        auto = AutoEnum.JEWELS;
                    }
                    break;
                case JEWELS:
                    if (!switchJewels) {
                        auto = AutoEnum.ALIGN;
                    }else{
                        auto = AutoEnum.JEWELDOWN;
                    }
                    break;
                case JEWELDOWN:
                    break;
                case JEWELPROCESS:
                    break;
                case JEWELHIT:
                    break;
                case JEWELUP:
                    break;
                case JEWELRESET:
                    break;
                case ALIGN:
                    auto = AutoEnum.ALIGNDRIVEOFFPLATFORM;
                    break;
                case ALIGNDRIVEOFFPLATFORM:
                    break;
                case ALIGNTURN:
                    break;
                case ALIGNDRIVEINTOCRYPTO:
                    break;
                case KEYCOLUMNSET:
                    if(switchKeyColumn && keyColumn != RelicRecoveryVuMark.UNKNOWN){
                        targetColumn = keyColumn;
                    }else{
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
                    break;
            }
            telemetry.update();
        }
    }
    //JEWEL FUNCTIONS HERE

    //ALIGN FUNCTIONS HERE


    //GLYPH FUNCTIONS HERE


    //COLLECT FUNCTIONS HERE


    //GENERAL FUNCTIONS HERE

}
