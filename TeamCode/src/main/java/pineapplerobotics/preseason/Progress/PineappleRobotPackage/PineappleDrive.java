package pineapplerobotics.preseason.Progress.PineappleRobotPackage;

import com.qualcomm.robotcore.hardware.DcMotor;

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
        setMotor(PineappleEnum.MotorLoc.LEFT, leftPower, false);
        setMotor(PineappleEnum.MotorLoc.RIGHT, rightPower, false);
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


     void encoderDrive(double speed, double value, PineappleEnum.MotorValueType motorValueType, double wheelSize) {
        if (motorValueType == PineappleEnum.MotorValueType.COUNTS) {
            encoderDriveCounts(speed, (int) value);
        } else {
            encoderDriveDist(speed, value, wheelSize, motorValueType);

        }
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

    private void encoderDriveDist(double speed, double value, double wheelSize, PineappleEnum.MotorValueType motorValueType) {
        int counts = distToCounts(value, motorValueType, wheelSize, getMotorType());

        encoderDrive(speed, counts, PineappleEnum.MotorValueType.COUNTS, wheelSize);
    }
}