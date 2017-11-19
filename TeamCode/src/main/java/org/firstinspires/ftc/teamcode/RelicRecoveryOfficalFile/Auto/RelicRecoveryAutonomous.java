package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto;

import android.graphics.Point;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.OLD.AutoBranches.BlankAuto;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.OLD.AutoBranches.Blue.BlueFront;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.OLD.AutoBranches.Red.RedFront;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;
import org.opencv.core.Mat;

/**
 * Created by young on 9/14/2017.
 */

@Autonomous(name = "RelicRecoveryAuto", group = "Linear Opmode")

public class RelicRecoveryAutonomous extends RelicRecoveryAbstractAutonomous {

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

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

        //Load Switch Board


        double delay = robotHandler.switchBoard.loadAnalog("delay");
        telemetry.addData("Delay", delay);
        RelicRecoveryEnums.AutoColor color;
        if (robotHandler.switchBoard.loadDigital("color")) {
            color = RelicRecoveryEnums.AutoColor.RED;
        } else {
            color = RelicRecoveryEnums.AutoColor.BLUE;
        }
        RelicRecoveryEnums.StartingPosition position = RelicRecoveryEnums.StartingPosition.FRONT;
//        if(robotHandler.switchBoard.loadDigital("position")){
//            position = RelicRecoveryEnums.StartingPosition.FRONT;
//        }else{
//            position = RelicRecoveryEnums.StartingPosition.BACK;
//        }

        boolean moreGlyph = true;
        boolean gyroEnabled = true;
        boolean pidEnabled = false;
        boolean encoderEnabled = true;
        boolean jewelsEnabled = true;
        boolean vuforiaAlign = true;
        boolean colorAlign = false;

        phoneTurnLeft.setPosition(.23);
        waitForStart();

        switch (position) {
            case FRONT:
                switch (color) {
                    case RED:
                        while (opModeIsActive()) {

                            VectorF vector = new VectorF(0, 0, -900);
                            if(alignWithGyro()) {
                                showVuforiaValues(listener, vector);
                            }
                        }
                        break;
                    case BLUE:
                        while (opModeIsActive() && navx_device.isConnected()) {
                            alignWithGyro();

                        }
                        break;
                }
                break;
            case BACK:

                break;
        }


    }

    public void showVuforiaValues(VuforiaTrackableDefaultListener listener,VectorF vector) {
        if(null != listener.getPose()) {
            VectorF trans = listener.getPose().getTranslation();
            // Extract the X, Y, and Z components of the offset of the target relative to the robot
            double[] robot = {trans.get(1),trans.get(2)};

            double[] target= {robot[0]-vector.get(1),robot[1]-vector.get(2)};//Delta change till target


            telemetry.addData("x",robot[0]); // X
            telemetry.addData("y",robot[1]); // Y
            telemetry.addData("-x", target[0]);
            telemetry.addData("-y",target[1]);



            double dis = getDistance(target);
            double ang = getAngle(target) - 45; //and walmart
            ang += 90;
            telemetry.addData("Distance", dis);
            telemetry.addData("Angle", ang);

            robotHandler.drive.mecanum.setMecanum(Math.toRadians(ang), .3 , 0 , 1);

            telemetry.update();
        }
    }

    public double getDistance(double[] target){
        return Math.sqrt(Math.pow(target[0], 2) + Math.pow(target[1], 2));
    }

    public double getAngle(double[] target) {
        double angle = (float) Math.toDegrees(Math.atan2(target[1], target[0]));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    public boolean alignWithGyro(){
        double gyroAngle = this.navx_device.getYaw();
        if(gyroAngle < 0){
            gyroAngle += 360;
        }
        telemetry.addData("Gyro Angle", gyroAngle);
        telemetry.update();
        double rotation;
        if (gyroAngle > 1 && gyroAngle < 180) {
            //Put Gyro here
            rotation = -.1;
            robotHandler.drive.tank.setPower(rotation, rotation);
        } else if (gyroAngle < 359 && gyroAngle > 179) {
            //and here
            rotation = .1;
            robotHandler.drive.tank.setPower(rotation, rotation);

        }else{
            robotHandler.drive.stop();
            return true;
        }
        return false;
    }
}
