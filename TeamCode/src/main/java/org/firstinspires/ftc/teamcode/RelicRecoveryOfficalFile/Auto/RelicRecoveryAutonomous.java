package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto;

import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia.PineappleRelicRecoveryVuforia;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConstants;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;

import java.text.DecimalFormat;

/**
 * Created by young on 9/14/2017.
 */

@Autonomous(name = "RelicRecoveryAuto", group = "Linear Opmode")

public class RelicRecoveryAutonomous extends RelicRecoveryAbstractAutonomous {

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine("Init-Started");
        telemetry.addLine("Init-Config");
        telemetry.update();
        config(this);


        //VUFORIA set up
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AdB8VB7/////AAAAGcfBp9I80URFkfBQFUyM+ptmQXBAMGx0svJKz7QE2nm20mBc/zI5sZNHfuP/ziIm+sYnO7fvPqUbFs8QWjRyXVEDmW4mMj+S+l+yaYRkpGZ/pmHyXiDb4aemHx0m70BulMNIce4+NVaCW5S/5BWNNev/AU0P+uWnHYuKNWzD2dPaRuprC4R6b/DgD1zeio1xlssYb9in9mfzn76gChOrE5B0ql6Q9FiHC5cTdacq2lKjm5nlkTiXz1e2jhVK3SddGoqM4FQ3mFks7/A88hFzlPfIIk45K2Lh7GvcVjuIiqNj5mTLaZJVqlsKdTQnKS4trJcc1YV9sjdbmh1agtn1UePy91fDj9uWSBdXvpIowv4B";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        vuforia.setFrameQueueCapacity(1);
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        VuforiaTrackableDefaultListener listener = (VuforiaTrackableDefaultListener) relicTemplate.getListener();
        VuforiaTrackableDefaultListener track = (VuforiaTrackableDefaultListener) relicTrackables.get(0).getListener();

        relicTrackables.activate();
        telemetry.addLine("Init-Vuforia Initialized");

        //function at the bottom of this file
        //loads the switch board --- color and delay TODO position
        loadSwitchBoard();

        telemetry.addLine("Init-Complete");
        telemetry.update();
        int val = 1;
        while (listener.getPose() == null) {
            if (val == 5) {
                val = 1;
            }
            String dots = "";
            for (int i = 0; i < val; i++) {
                dots += ".";
            }
            telemetry.addLine("Finding image" + dots);
            telemetry.update();
            Thread.sleep(300);
            val++;
        }
        phoneTurnLeft.setPosition(0.85);
        PineappleEnum.JewelState state = PineappleRelicRecoveryVuforia.getJewelConfig(PineappleRelicRecoveryVuforia.getImageFromFrame(vuforia.getFrameQueue().take(), PIXEL_FORMAT.RGB565), track, vuforia.getCameraCalibration(), telemetry);
        int i = 1;
        while (!opModeIsActive() && !isStopRequested()) {
            state = PineappleRelicRecoveryVuforia.getJewelConfig(PineappleRelicRecoveryVuforia.getImageFromFrame(vuforia.getFrameQueue().take(), PIXEL_FORMAT.RGB565), track, vuforia.getCameraCalibration(), telemetry);


            switch (state) {
                case NON_NON:
                    telemetry.addData("Config " + i + ": ", "NON NON");
                    break;
                case BLUE_RED:
                    telemetry.addData("Config " + i + ": ", "BLUE RED");
                    break;
                case RED_BLUE:
                    telemetry.addData("Config " + i + ": ", "RED BLUE");
                    break;
            }
            telemetry.update();
            Thread.sleep(500);
        }

        waitForStart();


        switch (position) {
            case FRONT:
                switch (color) {

                    case RED:
                        //Red Front
//                        gyroTurnPID(90);


                        break;
                    case BLUE:
                        //Blue Front
                        hitJewels(state);


                        ElapsedTime el = new ElapsedTime();
                        el.reset();                        //Drive forward
                        robotHandler.drive.mecanum.setPower(-.1, .1);
                        while (el.milliseconds() < 5000) {
                            servoCorrectForPicture(phoneTurnLeft, PineappleRelicRecoveryVuforia.getImageFromFrame(vuforia.getFrameQueue().take(), PIXEL_FORMAT.RGB565), track, vuforia.getCameraCalibration(), telemetry, 0.02);
                            telemetry.update();
                        }

//                        ElapsedTime el = new ElapsedTime();
//                        el.reset();
////        double position = phoneTurnLeft.servoObject.getPosition();
//                        while (driveTillTilt() && opModeIsActive() && !(el.milliseconds() < 4000)) {
//
//                            servoCorrectForPicture(phoneTurnLeft, PineappleRelicRecoveryVuforia.getImageFromFrame(vuforia.getFrameQueue().take(), PIXEL_FORMAT.RGB565),track, vuforia.getCameraCalibration(), telemetry);
//
//                            robotHandler.drive.mecanum.setPower(-.7, .7);
//                        }
//                        while (driveTillFlat() && opModeIsActive() && !(el.milliseconds() < 4000)) {
////            position-=0.004;
////            phoneTurnLeft.setPosition(position);
//                            servoCorrectForPicture(phoneTurnLeft, PineappleRelicRecoveryVuforia.getImageFromFrame(vuforia.getFrameQueue().take(), PIXEL_FORMAT.RGB565),track, vuforia.getCameraCalibration(), telemetry);
//
//                            Thread.sleep(10);
//                        }

//                        if ((el.milliseconds() < 4000)) {
//                            Thread.sleep(1500);
//                        }
                        robotHandler.drive.stop();

//                        //turn
                        phoneTurnLeft.setPosition(1); //Might need to be moved or changed //TODO gyro phone turn

                        gyroTurnPID(-90);

//                        //turn phone
//                        phoneTurnLeft.setPosition(1); //Might need to be moved or changed //TODO gyro phone turn

                        //align to cryptobox
                        RelicRecoveryVuMark keyColumn = RelicRecoveryVuMark.UNKNOWN;
                        while (opModeIsActive() && keyColumn == RelicRecoveryVuMark.UNKNOWN) {
                            keyColumn = RelicRecoveryVuMark.from(relicTemplate);
                        } // THIS SHOULD OCCUR IN INIT RIGHT? -yes, move it when we add the jewels part
                        VectorF vector = (keyColumn == RelicRecoveryVuMark.LEFT) ? RelicRecoveryConstants.BLUESIDELEFT : (keyColumn == RelicRecoveryVuMark.CENTER) ? RelicRecoveryConstants.BLUESIDECENTER : RelicRecoveryConstants.BLUESIDERIGHT;
                        alignToCrypto(-90, listener, vector);
//                        }
//                        VectorF vector = (placement == RelicRecoveryVuMark.LEFT) ? RelicRecoveryConstants.BLUESIDELEFT : (placement == RelicRecoveryVuMark.CENTER) ? RelicRecoveryConstants.BLUESIDECENTER : RelicRecoveryConstants.BLUESIDERIGHT;
//                        alignToCrypto(listener, vector);
                        break;

                    //TODO SPIN out box
                }
                break;
            case BACK:

                break;
        }


    }


}
