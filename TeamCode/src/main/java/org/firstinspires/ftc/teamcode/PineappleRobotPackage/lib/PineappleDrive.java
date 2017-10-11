package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.PineappleSettings;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Created by ftcpi on 6/29/2017.
 */

public class PineappleDrive {

    private PineappleResources resources;

    public PineappleEnum.DriveType driveType = PineappleEnum.DriveType.TANK;
    public PineappleDrive(PineappleResources res) {
        resources = res;
    }
    public void setDriveType(PineappleEnum.DriveType type){
        driveType = type;
    }
    public void update(double leftPower, double rightPower) {
        switch (driveType) {
            case TANK:
                setMotor(PineappleEnum.MotorLoc.LEFT, scalePower(leftPower), false);
                setMotor(PineappleEnum.MotorLoc.RIGHT, scalePower(rightPower), false);
                break;
            case MECANUM:
                setMotor(PineappleEnum.MotorLoc.LEFTFRONT, scalePower(leftPower), false);
                setMotor(PineappleEnum.MotorLoc.LEFTBACK, scalePower(leftPower), false);
                setMotor(PineappleEnum.MotorLoc.RIGHTFRONT, scalePower(rightPower), false);
                setMotor(PineappleEnum.MotorLoc.RIGHTBACK, scalePower(rightPower), false);
                break;
        }
    }
    public  void setPower(double leftPower, double rightPower) {
        switch (driveType) {
            case TANK:
                setMotor(PineappleEnum.MotorLoc.LEFT, leftPower, false);
                setMotor(PineappleEnum.MotorLoc.RIGHT, rightPower, false);
                break;
            case MECANUM:
                setMotor(PineappleEnum.MotorLoc.LEFTFRONT, leftPower, false);
                setMotor(PineappleEnum.MotorLoc.LEFTBACK, leftPower, false);
                setMotor(PineappleEnum.MotorLoc.RIGHTFRONT, rightPower, false);
                setMotor(PineappleEnum.MotorLoc.RIGHTBACK, rightPower, false);
                break;
        }
    }



    //Code source https://www.reddit.com/r/FRC/comments/2ryyrw/programming_mecanum_wheels/
    public void updateMecanum(double forwardBack, double leftRight, double turn) {
        double leftFront = turn + forwardBack + leftRight;
        double rightFront = -turn + forwardBack - leftRight;
        double leftBack = turn + forwardBack - leftRight;
        double rightBack = -turn + forwardBack + leftRight;

        double f = 1.0;

        if (Math.abs(leftFront) > f) f = leftFront;
        if (Math.abs(rightFront) > f) f = rightFront;
        if (Math.abs(leftBack) > f) f = leftBack;
        if (Math.abs(rightBack) > f) f = rightBack;

        setPowerMecanum(leftFront / f, rightFront / f, leftBack / f, rightBack / f);
    }

    private static double mecDirectionFromJoystick(Gamepad pad) {
        return Math.atan2(-pad.left_stick_y, pad.left_stick_x);
    }
    private static double mecSpeedFromJoystick(Gamepad pad) {
        // If the joystick is close enough to the middle, return a 0 (no movement)
        if (abs(pad.left_stick_x) < 0.15f
                && abs(pad.left_stick_y) < 0.15f) {
            return 0.0;
        } else {
            return sqrt((pad.left_stick_y * pad.left_stick_y)
                    + (pad.left_stick_x * pad.left_stick_x));
        }
    }
    public void updateMecanum(Gamepad pad, double scale) {

        double angle = mecDirectionFromJoystick(pad);
        double speed = mecSpeedFromJoystick(pad);
        double rotation = mecSpinFromJoystick(pad);
        angle += Math.PI / 4;
        speed *= sqrt(2);

        double sinDir = sin(angle);
        double cosDir = cos(angle);
        double multipliers[] = new double[4];
        multipliers[0] = (speed * sinDir) + rotation;
        multipliers[1] = (speed * cosDir) + rotation;
        multipliers[2] = (speed * -cosDir) + rotation;
        multipliers[3] = (speed * -sinDir) + rotation;

        double largest = abs(multipliers[0]);
        for (int i = 1; i < 4; i++) {
            if (abs(multipliers[i]) > largest)
                largest = abs(multipliers[i]);
        }

        // Only normalize multipliers if largest exceeds 1.0
        if (largest > 1.0) {
            for (int i = 0; i < 4; i++) {
                multipliers[i] = multipliers[i] / largest;
            }
        }
        setMotor(PineappleEnum.MotorLoc.LEFTFRONT, multipliers[0] * scale, false);
        setMotor(PineappleEnum.MotorLoc.RIGHTFRONT, multipliers[3] * scale, false);
        setMotor(PineappleEnum.MotorLoc.LEFTBACK, multipliers[2] * scale, false);
        setMotor(PineappleEnum.MotorLoc.RIGHTBACK, multipliers[1] * scale, false);
    }
    private static double mecSpinFromJoystick(Gamepad pad) {
        return (abs(pad.right_stick_x) > 0.15f)
                ? pad.right_stick_x : 0.0;
    }

    //Code source https://www.reddit.com/r/FRC/comments/2ryyrw/programming_mecanum_wheels/
    public void updateMecanum(double forwardBack, double leftRight, double turn){
        double leftFront = turn + forwardBack + leftRight;
        double rightFront = -turn + forwardBack - leftRight;
        double leftBack = turn + forwardBack - leftRight;
        double rightBack = -turn + forwardBack + leftRight;

        double f = 1;

        if(abs(leftFront) > f) f = leftFront;
        if(abs(rightFront) > f) f = rightFront;
        if(abs(leftBack) > f) f = leftBack;
        if(abs(rightBack) > f) f = rightBack;

        setPowerMecanum(leftFront/f, rightFront/f, leftBack/f, rightBack/f);
    }

    public void setPowerMecanum(double leftFront, double rightFront, double leftBack, double rightBack){
        setMotor(PineappleEnum.MotorLoc.LEFTFRONT, leftFront, false);
        setMotor(PineappleEnum.MotorLoc.RIGHTFRONT, rightFront, false);
        setMotor(PineappleEnum.MotorLoc.LEFTBACK, leftBack, false);
        setMotor(PineappleEnum.MotorLoc.RIGHTBACK, rightBack, false);
    }

    public void setDirectPower(double leftPower, double rightPower) {
        setMotor(PineappleEnum.MotorLoc.LEFT, leftPower, true);
        setMotor(PineappleEnum.MotorLoc.RIGHT, rightPower, true);
    }
    public void stop() {
        setMotor(PineappleEnum.MotorLoc.LEFT, 0, true);
        setMotor(PineappleEnum.MotorLoc.RIGHT, 0, true);
    }
    void setMotor(PineappleEnum.MotorLoc location, double power, boolean direct) {
        ArrayList<PineappleMotor> motors = resources.storage.getDrivemotors(location);
        for (PineappleMotor motor : motors) {
            if (direct) {
                motor.setDirectPower(power);
            } else {
                motor.setPower(power);
            }
        }
    }
    void setEncoderDrive(PineappleEnum.MotorLoc location, double power, int counts) {
        ArrayList<PineappleMotor> motors = resources.storage.getDrivemotors(location);
        for (PineappleMotor motor : motors) {
            int target = motor.motorObject.getCurrentPosition() + counts;
            motor.motorObject.setTargetPosition(target);
            motor.motorObject.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setDirectPower(power);

        }
    }
    boolean isBusy() {
        boolean output = false;
        ArrayList<PineappleMotor> motors = resources.storage.getDrivemotors();
        for (PineappleMotor motor : motors) {
            resources.feedBack.sayFeedBackWithOutUpdate(motor.motorName + " encoder", motor.motorObject.getCurrentPosition());
            if (motor.motorObject.isBusy()) {
                output = true;
            }

        }

        return output;
    }
    void runWithoutEncoder(PineappleEnum.MotorLoc location) {
        ArrayList<PineappleMotor> motors = resources.storage.getDrivemotors(location);
        for (PineappleMotor motor : motors) {
            motor.motorObject.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

    }
    double getDriveCPR()  {
        ArrayList<PineappleMotor> motors = resources.storage.getDrivemotors();
        double lastCPR = 0;
        boolean firsttime = true;
        for (PineappleMotor motor : motors) {
            if (firsttime) {
                lastCPR = motor.cpr;
                firsttime = false;
            } else {
                if (lastCPR != motor.cpr) {
                    resources.feedBack.sayFeedBackWithOutUpdate("ERROR", "Drive motor incompatiable");
                    resources.feedBack.update();
//                    wait(2000);
                }
                lastCPR = motor.cpr;
            }
        }
        return lastCPR;
    }
    PineappleEnum.MotorType getMotorType() {
        ArrayList<PineappleMotor> motors = resources.storage.getDrivemotors();
        PineappleEnum.MotorType motorType = PineappleEnum.MotorType.UNDI;
        boolean firsttime = true;
        for (PineappleMotor motor : motors) {
            if (firsttime) {
                motorType = motor.motorType;
                firsttime = false;
            } else {
                if (motorType != motor.motorType) {
                    resources.feedBack.sayFeedBackWithOutUpdate("ERROR", "Drive motors incompatiable");
                    resources.feedBack.update();
//                    wait(2000);
                }
                motorType = motor.motorType;
            }
        }
        return motorType;
    }



    ///////////////////////////
    //Drive Encoder Functions//
    ///////////////////////////


    void encoderDrive(double speed, String distance, double wheelSize) {
        PineappleEnum.MotorValueType motorValueType = getUnit(distance);
         double value = getVal(distance);
        if (motorValueType == PineappleEnum.MotorValueType.COUNTS) {
            encoderDriveCounts(speed, (int) value);
        } else {
            encoderDriveDist(speed, distance, wheelSize);

        }
    }
    private PineappleEnum.MotorValueType getUnit(String val) {
        val = val.substring(val.length() - 2);
        switch (val) {
            case "in":
                return PineappleEnum.MotorValueType.INCH;
            case "ct":
                return PineappleEnum.MotorValueType.COUNTS;
            case "dg":
                return PineappleEnum.MotorValueType.DEGREES;
            case "cm":
                return PineappleEnum.MotorValueType.CM;
            case "rd":
                return PineappleEnum.MotorValueType.RADIANS;
            case "mt":
                return PineappleEnum.MotorValueType.METER;
            case "ft":
                return PineappleEnum.MotorValueType.FEET;
            default:
                return PineappleEnum.MotorValueType.INCH;
        }
    }
    private double getVal(String val){
        return Double.parseDouble(val.substring(0, val.length() - 2));
    }
    private void encoderDriveCounts(double speed, int counts) {
        if (resources.linearOpMode.opModeIsActive()) {
            if (isPositive(speed) != isPositive(counts)) {
                counts = -counts;
            }

            setEncoderDrive(PineappleEnum.MotorLoc.LEFT, speed, counts);
            setEncoderDrive(PineappleEnum.MotorLoc.RIGHT, speed, counts);

            while (resources.linearOpMode.opModeIsActive() && isBusy()){
                resources.feedBack.update();
            }

            stop();
            isBusy();
            resources.feedBack.update();
            runWithoutEncoder(PineappleEnum.MotorLoc.LEFT);
            runWithoutEncoder(PineappleEnum.MotorLoc.RIGHT);

        }
    }
    private int distToCounts(double value, PineappleEnum.MotorValueType motorValueType, double wheelSize, PineappleEnum.MotorType motorType) {
        double cpr = getDriveCPR();
        switch (motorValueType) {
            case INCH:
                return (int) (cpr * (value / (PineappleRobotConstants.PI * wheelSize)));
            case DEGREES:
                return (int) (cpr * (value / 360));
            case CM:
                return (int) (cpr * ((value * PineappleRobotConstants.CMTOINCH) / (PineappleRobotConstants.PI * wheelSize)));
            case RADIANS:
                return (int) (cpr * (value / (2 * PineappleRobotConstants.PI)));
            case METER:
                return (int) (cpr * (((value*100) * PineappleRobotConstants.CMTOINCH) / (PineappleRobotConstants.PI * wheelSize)));
            case FEET:
                return (int) (cpr * ((value*12) / (PineappleRobotConstants.PI * wheelSize)));
            case YARDS:
                return (int) (cpr * ((value*36) / (PineappleRobotConstants.PI * wheelSize)));
            default:
                return 0;
        }
    }
    private boolean isPositive(double value) {
        if (value >= 0) {
            return true;
        } else {
            return false;
        }
    }
    private void encoderDriveDist(double speed, String distance, double wheelSize) {
        PineappleEnum.MotorValueType motorValueType = getUnit(distance);
        double value = getVal(distance);
        int counts = distToCounts(value, motorValueType, wheelSize, getMotorType());
        String countsSring = counts+"ct";
        encoderDrive(speed, countsSring, wheelSize);
    }
    public double scalePower(double in){

        boolean pos = true;

        if(in < 0){
            pos = false;
        }

        if(PineappleSettings.driveExponential){
            in = in*in;
            if(!pos){

                in = -in;
            }
        }

        double out = in * PineappleSettings.driveScaleSpeed;

        return out;
    }
}