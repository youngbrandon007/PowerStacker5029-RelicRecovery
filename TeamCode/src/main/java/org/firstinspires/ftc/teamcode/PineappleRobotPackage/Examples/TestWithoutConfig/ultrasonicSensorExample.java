package org.firstinspires.ftc.teamcode.PineappleRobotPackage.Examples.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleUltrasonicSensor;

/**
 * Created by young on 8/7/2017.
 */

@TeleOp(name = "PineappleRobotUltrasonicSensorTest", group = "Linear Opmode")



public class ultrasonicSensorExample extends LinearOpMode {
    PineappleRobot robot;

    PineappleUltrasonicSensor ultrasonicSensor;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new PineappleRobot(this);

        ultrasonicSensor = robot.sensorHandler.newUltrasonicSensor("us");

        robot.mapRobot();

        waitForStart();
        while(opModeIsActive()) {
            robot.sayFeedBack(ultrasonicSensor.sensorName, ultrasonicSensor.getValue(PineappleEnum.PineappleSensorEnum.US));
            robot.updateFeedBack();
        }
    }
}
