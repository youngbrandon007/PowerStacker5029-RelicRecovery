package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto;

import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfigV2;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConstants;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;

import java.text.DecimalFormat;
import java.util.Calendar;


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

    private boolean usingGyro = false;

    public void alignToCrypto(double angle, VuforiaTrackableDefaultListener listener, VectorF vector) {
        try {
            yawPIDController.enable(true);

            int DEVICE_TIMEOUT_MS = 1000;
            navXPIDController.PIDResult yawPIDResult = new navXPIDController.PIDResult();

            DecimalFormat df = new DecimalFormat("#.###");
            boolean done = false;
            while (opModeIsActive() && !Thread.currentThread().isInterrupted() && !done) {

                double output = 0;
                if (yawPIDController.waitForNewUpdate(yawPIDResult, DEVICE_TIMEOUT_MS)) {
                    if (yawPIDResult.isOnTarget()) {
                        telemetry.addData("PIDOutput", df.format(0.00));
                    } else {
                        output = yawPIDResult.getOutput();
                        telemetry.addData("PIDOutput", df.format(output) + ", " +
                                df.format(-output));
                    }
                } else {
                /* A timeout occurred */
                    telemetry.addData("navXRotateOp", "Yaw PID waitForNewUpdate() TIMEOUT.");
                }
                telemetry.addData("Yaw", df.format(navx_device.getYaw()));
                telemetry.update();


                done = alignToCryptoboxVuforia(listener, vector, output);
            }

        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();

        }
    }

    public navXPIDController makePIDController(double degrees) {

        final double TARGET_ANGLE_DEGREES = degrees;
        final double TOLERANCE_DEGREES = 2.0;

        double YAW_PID_P = RelicRecoveryConstants.ROBOTTURNP;
        double YAW_PID_I = RelicRecoveryConstants.ROBOTTURNI;
        double YAW_PID_D = RelicRecoveryConstants.ROBOTTURND;
        navXPIDController yawPIDController = new navXPIDController(navx_device,
                navXPIDController.navXTimestampedDataSource.YAW);

        /* Configure the PID controller */
        yawPIDController.setSetpoint(TARGET_ANGLE_DEGREES);
        yawPIDController.setContinuous(true);
        yawPIDController.setOutputRange(RelicRecoveryConstants.MIN_MOTOR_OUTPUT_VALUE, RelicRecoveryConstants.MAX_MOTOR_OUTPUT_VALUE);
        yawPIDController.setTolerance(navXPIDController.ToleranceType.ABSOLUTE, TOLERANCE_DEGREES);
        yawPIDController.setPID(YAW_PID_P, YAW_PID_I, YAW_PID_D);
        return yawPIDController;
    }

    //Vuforia Functions
    private boolean alignToCryptoboxVuforia(VuforiaTrackableDefaultListener listener, VectorF vector, double rotation) {
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
            double gyroAngle = navx_device.getYaw();
            gyroAngle += (gyroAngle < 0) ? 360 : 0;

            if (dis < RelicRecoveryConstants.VUFORIAALIGNRANGE && inRange(gyroAngle, 2, 358)) {
                robotHandler.drive.stop();
                return true;
            }

            double speed = (dis > RelicRecoveryConstants.VUFORIAALIGNMEDIUM) ? 1 : (dis > RelicRecoveryConstants.VUFORIAALIGNSLOW) ? .5 : .3;

            robotHandler.drive.mecanum.setMecanum(Math.toRadians(ang), speed, rotation, 1);


        }
        return false;
    }
    private boolean inRange(double value, double lowerLimit, double upperLimit) {
        return value > lowerLimit && value < upperLimit;
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
    public boolean alignWithGyro(double angle) {
        double gyroAngle = this.navx_device.getYaw();

        telemetry.addData("Gyro Angle", gyroAngle);
        telemetry.addData("Target Angle", angle);
        telemetry.update();

        //make  the range between 0-360
        gyroAngle += (gyroAngle < 0) ? 360 : 0;

        //offset to the input angle - positive is clockwise
        gyroAngle -= angle;
        gyroAngle += (gyroAngle < 0) ? 360 : (gyroAngle > 360) ? -360 : 0;

        double rotation = (gyroAngle > 1 && gyroAngle < 180) ? -.1 : (gyroAngle < 359 && gyroAngle > 179) ? .1 : 0;


        if (rotation == 0) {
            if (usingGyro) {
                robotHandler.drive.stop();
                usingGyro = false;
            }
            return true;
        } else usingGyro = true;


        robotHandler.drive.tank.setPower(rotation, rotation);

        return false;
    }

    public void hitJewels(PineappleEnum.JewelState jewelState) throws InterruptedException {
        jewelLeverLeft.setPosition(RelicRecoveryConstants.JEWELDOWN);
        jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNMIDDLE);
        Thread.sleep(1000);
        switch (jewelState) {
            case BLUE_RED:
                if (allianceColor == PineappleEnum.AllianceColor.BLUE) {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                } else {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
            case RED_BLUE:
                if (allianceColor == PineappleEnum.AllianceColor.RED) {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                } else {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
            case NON_BLUE:
                if (allianceColor == PineappleEnum.AllianceColor.RED) {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                }
                break;
            case NON_RED:
                if (allianceColor == PineappleEnum.AllianceColor.BLUE) {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                }
                break;
            case BLUE_NON:
                if (allianceColor == PineappleEnum.AllianceColor.RED) {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
            case RED_NON:
                if (allianceColor == PineappleEnum.AllianceColor.BLUE) {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
        }
        Thread.sleep(1500);
        jewelLeverLeft.setPosition(RelicRecoveryConstants.JEWELUP);
        jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
        Thread.sleep(3000);
    }

    public void driveOffPlate(double speed) throws InterruptedException {
        //

        robotHandler.drive.mecanum.setPower(-speed, speed);

        while (driveTillTilt() && opModeIsActive()) {
        }
        while (driveTillFlat() && opModeIsActive()) {
        }


        Thread.sleep(1000);

        robotHandler.drive.stop();

    }

    private boolean driveTillTilt() {
        double roll = navx_device.getRoll();
        telemetry.addData("Roll", roll);
        telemetry.update();
        return (Math.abs(roll) > 3);
    }

    private boolean driveTillFlat() {
        double roll = navx_device.getRoll();
        telemetry.addData("Roll", roll);
        telemetry.update();
        return (Math.abs(roll) < 2);
    }
}


