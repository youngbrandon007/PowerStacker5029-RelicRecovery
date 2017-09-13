package org.firstinspires.ftc.teamcode.PineappleRobotPackage.Examples.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleGyroSensor;

/**
 * Created by young on 8/7/2017.
 */
@TeleOp(name = "PineappleRobotGyroSensortest", group = "Linear Opmode")



public class GyroExample  extends LinearOpMode {
    PineappleRobot robot;

    PineappleGyroSensor gyro;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new PineappleRobot(this);

        gyro = robot.sensorHandler.newGyroSensor("g");

        waitForStart();

        while(opModeIsActive()) {
            robot.sayFeedBack("Heading", gyro.getValue(PineappleEnum.PineappleSensorEnum.GSHEADING));
            robot.sayFeedBack("Status", gyro.GSStatus());

            robot.sayFeedBack("X", gyro.getValue(PineappleEnum.PineappleSensorEnum.GSX));
            robot.sayFeedBack("Y", gyro.getValue(PineappleEnum.PineappleSensorEnum.GSY));
            robot.sayFeedBack("Z", gyro.getValue(PineappleEnum.PineappleSensorEnum.GSZ));
            robot.updateFeedBack();
        }
    }
}
