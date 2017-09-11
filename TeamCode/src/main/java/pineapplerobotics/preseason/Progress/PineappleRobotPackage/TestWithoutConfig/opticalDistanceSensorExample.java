package pineapplerobotics.preseason.Progress.PineappleRobotPackage.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleRobot;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleOpticalDistanceSensor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleTouchSensor;

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