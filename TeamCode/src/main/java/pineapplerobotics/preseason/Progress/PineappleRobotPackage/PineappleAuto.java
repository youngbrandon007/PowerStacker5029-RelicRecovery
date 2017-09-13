package pineapplerobotics.preseason.Progress.PineappleRobotPackage;

import com.qualcomm.robotcore.hardware.TouchSensor;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleColorSensor;

/**
 * Created by young on 8/6/2017.
 */

public class PineappleAuto {

    private PineappleResources resources;

    private PineappleDrive drive;

    public PineappleAuto(PineappleResources res ,PineappleDrive drive){
        resources = res;
        this.drive = drive;
    }

    public void driveUntil(PineappleSensor sensor, PineappleEnum.PineappleSensorEnum sensorEnum, PineappleEnum.condition condition,double sensorValue, double power){
        if(checkCondition(sensor.getValue(sensorEnum), sensorValue, condition)){
            drive.setMotor(PineappleEnum.MotorLoc.RIGHT, power, true);
            drive.setMotor(PineappleEnum.MotorLoc.LEFT, power, true);
            while (checkCondition(sensor.getValue(sensorEnum), sensorValue, condition) && resources.linearOpMode.opModeIsActive()){
                resources.feedBack.sayFeedBack(sensor.sensorName, sensor.getValue(sensorEnum));
            }

            drive.stop();
        }
    }

    public void lineFollow(PineappleSensor color, PineappleEnum.PineappleSensorEnum colorEnum, PineappleSensor sensor, PineappleEnum.PineappleSensorEnum sensorEnum, PineappleEnum.condition condition, double sensorValue, double power){
        if(checkCondition(sensor.getValue(sensorEnum), sensorValue, condition)){



            while (checkCondition(sensor.getValue(sensorEnum), sensorValue, condition) && resources.linearOpMode.opModeIsActive()){

                double turn = (color.getValue(colorEnum)-.2) * 2;

                double left = 1-turn;
                double right = turn;

                drive.setMotor(PineappleEnum.MotorLoc.RIGHT, (right-.2)*power, true);
                drive.setMotor(PineappleEnum.MotorLoc.LEFT, (left-.2)*power , true);

                resources.feedBack.sayFeedBackWithOutUpdate(color.sensorName, color.getValue(colorEnum));
                resources.feedBack.sayFeedBack(sensor.sensorName, sensor.getValue(sensorEnum));
            }

            drive.stop();
        }
    }

    public void turnUntil(PineappleSensor sensor, PineappleEnum.PineappleSensorEnum sensorEnum, PineappleEnum.condition condition,double sensorValue, double rightPower, double leftPower){
        if(checkCondition(sensor.getValue(sensorEnum), sensorValue, condition)){

            double percent = 0;
            double starting = sensor.getValue(sensorEnum);
            double now = starting;

            drive.setMotor(PineappleEnum.MotorLoc.RIGHT, rightPower, true);
            drive.setMotor(PineappleEnum.MotorLoc.LEFT, leftPower, true);
            while (checkCondition(sensor.getValue(sensorEnum), sensorValue, condition) && resources.linearOpMode.opModeIsActive()){
                now = sensor.getValue(sensorEnum);
                percent = (now-starting)/(sensorValue-starting);
                if (percent>.85) {

                    drive.setMotor(PineappleEnum.MotorLoc.RIGHT, rightPower/3, true);
                    drive.setMotor(PineappleEnum.MotorLoc.LEFT, leftPower/3, true);

                }

                resources.feedBack.sayFeedBack(sensor.sensorName, sensor.getValue(sensorEnum));
            }

            drive.stop();
        }
    }
    private boolean checkCondition(double sensorValue, double conditionValue, PineappleEnum.condition condition){
        switch (condition) {
            case EQUAL:
                if(sensorValue == conditionValue){
                    return false;
                }
                break;
            case LESSTHAN:
                if(sensorValue < conditionValue){
                    return false;
                }
                break;
            case GREATERTHAN:
                if(sensorValue > conditionValue){
                    return false;
                }
                break;
        }
        return true;
    }
    public void encoderDrive(double speed, String distance, double wheelSize) {
        drive.encoderDrive(speed, distance, wheelSize);
    }

}
