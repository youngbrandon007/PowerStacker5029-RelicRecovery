package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfigV2;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConstants;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;

/**
 * Created by young on 9/15/2017.
 */

abstract public class RelicRecoveryAbstractAutonomous extends RelicRecoveryConfigV2 {
    public double delay = 0;
    public RelicRecoveryEnums.AutoColor color = RelicRecoveryEnums.AutoColor.BLUE;
    public RelicRecoveryEnums.StartingPosition position = RelicRecoveryEnums.StartingPosition.FRONT;
    public boolean moreGlyph = false;
    public boolean gyroEnabled = true;
    public boolean pidEnabled = false;
    public boolean encoderEnabled = false;
    public boolean jewelsEnabled = true;
    public boolean vuforiaAlign = true;
    public boolean colorAlign = false;


    public void alignToCrypto(VuforiaTrackableDefaultListener listener, VectorF vector) {
        while ((alignWithGyro()) ? !alignToCryptoboxVuforia(listener, vector) : true && opModeIsActive()) {
        }
    }

    //Vuforia Functions
    private boolean alignToCryptoboxVuforia(VuforiaTrackableDefaultListener listener, VectorF vector) {
        if (null != listener.getPose()) {
            VectorF trans = listener.getPose().getTranslation();
            // Extract the X, Y, and Z components of the offset of the target relative to the robot
            double[] robot = {trans.get(1), trans.get(2)};

            double[] target = {robot[0] - vector.get(1), robot[1] - vector.get(2)};//Delta change till target


            telemetry.addData("x", robot[0]); // X
            telemetry.addData("y", robot[1]); // Y
            telemetry.addData("-x", target[0]);
            telemetry.addData("-y", target[1]);


            double dis = getDistance(target);
            double ang = getAngle(target) - 45;
            ang += 90;
            telemetry.addData("Distance", dis);
            telemetry.addData("Angle", ang);

            //Check for stop before motors are set again
            if (dis < RelicRecoveryConstants.VUFORIAALIGNRANGE) {
                robotHandler.drive.stop();
                return true;
            }

            double speed = (dis > RelicRecoveryConstants.VUFORIAALIGNMEDIUM) ? 1 : (dis > RelicRecoveryConstants.VUFORIAALIGNSLOW) ? .5 : .3;

            robotHandler.drive.mecanum.setMecanum(Math.toRadians(ang), speed, 0, 1);

            telemetry.update();

        }
        return false;
    }

    private double getDistance(double[] target) {
        return Math.sqrt(Math.pow(target[0], 2) + Math.pow(target[1], 2));
    }

    private double getAngle(double[] target) {
        double angle = (float) Math.toDegrees(Math.atan2(target[1], target[0]));

        angle += (angle < 0) ? 360 : 0;

        return angle;
    }


    //aling with gyro
    public boolean alignWithGyro() {
        double gyroAngle = this.navx_device.getYaw();
        if (gyroAngle < 0) {
            gyroAngle += 360;
        }
        telemetry.addData("Gyro Angle", gyroAngle);
        telemetry.update();
        double rotation =  (gyroAngle > 1 && gyroAngle < 180) ? -.1 : (gyroAngle < 359 && gyroAngle > 179) ? .1 : 0;


        if(rotation == 0){
//            robotHandler.drive.stop();
            return true;
        }


        robotHandler.drive.tank.setPower(rotation, rotation);

        return false;
    }
    public void hitJewels(PineappleEnum.JewelState jewelState) throws InterruptedException {
        jewelLeverLeft.setPosition(RelicRecoveryConstants.JEWELDOWN);
        jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNMIDDLE);
        Thread.sleep(1000);
        switch (jewelState) {
            case BLUE_RED:
                if (allianceColor == PineappleEnum.AllianceColor.BLUE){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                } else {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
            case RED_BLUE:
                if (allianceColor == PineappleEnum.AllianceColor.RED){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                } else {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
            case NON_BLUE:
                if (allianceColor == PineappleEnum.AllianceColor.RED){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                }
                break;
            case NON_RED:
                if (allianceColor == PineappleEnum.AllianceColor.BLUE){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                }
                break;
            case BLUE_NON:
                if (allianceColor == PineappleEnum.AllianceColor.RED){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
            case RED_NON:
                if (allianceColor == PineappleEnum.AllianceColor.BLUE){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
        }
        Thread.sleep(1500);
        jewelLeverLeft.setPosition(RelicRecoveryConstants.JEWELUP);
        jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
        Thread.sleep(3000);
    }
}


