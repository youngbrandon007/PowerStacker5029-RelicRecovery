package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConstants;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;

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
        VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        VuforiaTrackableDefaultListener listener = (VuforiaTrackableDefaultListener) relicTemplate.getListener();
        relicTrackables.activate();
        telemetry.addLine("Init-Vuforia Initialized");

        //function at the bottom of this file
        //loads the switch board --- color and delay TODO position
        loadSwitchBoard();

        telemetry.addLine("Init-Complete");
        telemetry.update();
        waitForStart();


        switch (position) {
            case FRONT:
                switch (color) {
                    case RED:
                        //Red Front


                        break;
                    case BLUE:
                        //Blue Front

                        //TODO fix Jewels

                        //Drive forward
                        driveOffPlate(.7);

                        //turn
                        alignWithGyro(90);

                        //turn phone
                        phoneTurnLeft.setPosition(.23); //Might need to be moved or changed

                        //align to cryptobox
                        RelicRecoveryVuMark placement = RelicRecoveryVuMark.UNKNOWN;
                        while (opModeIsActive() && placement == RelicRecoveryVuMark.UNKNOWN) {
                            placement = RelicRecoveryVuMark.from(relicTemplate);
<<<<<<< HEAD
                        } // THIS SHOULD OCCUR IN INIT RIGHT? -yes, move it when we add the jewels part
                        VectorF vector = (placement == RelicRecoveryVuMark.LEFT) ? RelicRecoveryConstants.BLUESIDELEFT : (placement == RelicRecoveryVuMark.CENTER) ? RelicRecoveryConstants.BLUESIDECENTER : RelicRecoveryConstants.BLUESIDERIGHT;
                        alignToCrypto(90, listener, vector);
=======
                        }
                        VectorF vector = (placement == RelicRecoveryVuMark.LEFT) ? RelicRecoveryConstants.BLUESIDELEFT : (placement == RelicRecoveryVuMark.CENTER) ? RelicRecoveryConstants.BLUESIDECENTER : RelicRecoveryConstants.BLUESIDERIGHT;
                        alignToCrypto(listener, vector);
>>>>>>> origin/PID_Testing

                        //TODO SPIN out box

                        break;
                }
                break;
            case BACK:

                break;
        }


    }


    public void loadSwitchBoard() {
        delay = robotHandler.switchBoard.loadAnalog("delay");
        color = (robotHandler.switchBoard.loadDigital("color")) ? RelicRecoveryEnums.AutoColor.BLUE : RelicRecoveryEnums.AutoColor.RED;

        telemetry.addData("Delay", delay);
        telemetry.addData("Color",color);
    }
}
