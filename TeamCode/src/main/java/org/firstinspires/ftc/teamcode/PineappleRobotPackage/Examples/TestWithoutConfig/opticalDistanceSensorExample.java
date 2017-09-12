package org.firstinspires.ftc.teamcode.PineappleRobotPackage.Examples.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleOpticalDistanceSensor;

/**
 * Created by young on 8/7/2017.
 */
@TeleOp(name = "PineappleRobotOpticalDistancetest", group = "Linear Opmode")



public class opticalDistanceSensorExample extends LinearOpMode {
    PineappleRobot robot;

    PineappleOpticalDistanceSensor optical;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new PineappleRobot(this);

        optical = robot.sensorHandler.newOpticalDistanceSensor("o");

        robot.mapRobot();

        waitForStart();
        optical.ODSLEDToggle(true);

        while(opModeIsActive()) {
            robot.sayFeedBack("Raw", optical.getValue(PineappleEnum.PineappleSensorEnum.ODSRAW));
            robot.sayFeedBack("LightDetected", optical.getValue(PineappleEnum.PineappleSensorEnum.ODSLIGHTDETECTED));
            robot.sayFeedBack("Raw Max", optical.getValue(PineappleEnum.PineappleSensorEnum.ODSRAWMAX));
            robot.updateFeedBack();
        }
    }
}