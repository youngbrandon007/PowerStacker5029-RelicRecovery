package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Auto;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Drive.PineappleDrive;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleResources;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensor;

/**
 * Created by young on 8/6/2017.
 */

public class PineappleAutoDrive {

    private PineappleResources resources;

    private PineappleDrive drive;

    /**
     * Constructor of the Auto drive class
     * @param res Passes the resources for use in methods
     * @param drive gives direct access to the drive methods for ease of use
     */
    public PineappleAutoDrive(PineappleResources res , PineappleDrive drive){
        resources = res;
        this.drive = drive;
    }

    /**
     * Moves the robot until a sensor is in a certain specified threshold
     * @param sensor The sensor that the robot moves until
     * @param sensorEnum The specified type of sensor
     * @param condition What should happen for the driver to stop
     * @param sensorValue What is the specified value for the robot to stop at
     * @param power What power the robot should move at
     */
    public void driveUntil(PineappleSensor sensor, PineappleEnum.PineappleSensorEnum sensorEnum, PineappleEnum.Condition condition, double sensorValue, double power){
        if(checkCondition(sensor.getValue(sensorEnum), sensorValue, condition)){
            drive.setMotor(PineappleEnum.MotorLoc.RIGHT, power, true);
            drive.setMotor(PineappleEnum.MotorLoc.LEFT, power, true);
            while (checkCondition(sensor.getValue(sensorEnum), sensorValue, condition) && resources.linearOpMode.opModeIsActive()){
                resources.feedBack.sayFeedBack(sensor.sensorName, sensor.getValue(sensorEnum));
            }

            drive.stop();
        }
    }

    /**
     * Moves the robot until a sensor is in a certain specified threshold specific for mechnum drive
     * @param sensor The sensor that the robot moves until
     * @param sensorEnum The specified type of sensor
     * @param condition What should happen for the driver to stop
     * @param sensorValue What is the specified value for the robot to stop at
     * @param power What power the robot should move at
     * @param angle Angle for the Mechnum drive if needed
     */
    public void driveUntilMechnum(PineappleSensor sensor, PineappleEnum.PineappleSensorEnum sensorEnum, PineappleEnum.Condition condition, double sensorValue, double power, double angle){
        if(checkCondition(sensor.getValue(sensorEnum), sensorValue, condition)){
            drive.mecanum.setMecanum(angle, power, 0, 1);
            while (checkCondition(sensor.getValue(sensorEnum), sensorValue, condition) && resources.linearOpMode.opModeIsActive()){
                resources.feedBack.sayFeedBack(sensor.sensorName, sensor.getValue(sensorEnum));
            }

            drive.stop();
        }
    }

    public void lineFollow(PineappleSensor color, PineappleEnum.PineappleSensorEnum colorEnum, PineappleSensor sensor, PineappleEnum.PineappleSensorEnum sensorEnum, PineappleEnum.Condition condition, double sensorValue, double power){
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

    /**
     * Turns the robot until a sensor is triggered
     * @param sensor The sensor to be triggered
     * @param sensorEnum The type of sensor for id
     * @param condition When it should stop
     * @param sensorValue what the value of the sensor is when it stops
     * @param rightPower The power of the right side while turning
     * @param leftPower The power of the left side while turning
     */
    public void turnUntil(PineappleSensor sensor, PineappleEnum.PineappleSensorEnum sensorEnum, PineappleEnum.Condition condition, double sensorValue, double rightPower, double leftPower){
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

    /**
     * @param sensorValue Method for checking if the sensor has reached goal
     * @param conditionValue The Type of specified operator
     * @param condition The type of operator the value is check by
     * @return - whether or not it has reached
     */
    private boolean checkCondition(double sensorValue, double conditionValue, PineappleEnum.Condition condition){
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
}
