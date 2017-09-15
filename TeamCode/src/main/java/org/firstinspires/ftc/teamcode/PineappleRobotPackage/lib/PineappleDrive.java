package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.PineappleSettings;

import java.util.ArrayList;

/**
 * Created by ftcpi on 6/29/2017.
 */

public class PineappleDrive {

    private PineappleResources resources;

    public PineappleDrive(PineappleResources res) {
        resources = res;
    }

    public void setPower(double leftPower, double rightPower) {

        setMotor(PineappleEnum.MotorLoc.LEFT, scalePower(leftPower), false);
        setMotor(PineappleEnum.MotorLoc.RIGHT, scalePower(rightPower), false);
    }

    public double scalePower(double in){

        boolean pos = true;

        if(in < 0){
            pos = false;
        }

        if(PineappleSettings.driveExponential){
            in = in*in;
        }

        double out = in * PineappleSettings.driveScaleSpeed;

        if(!pos){
            out = -out;
        }

        return out;
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
}