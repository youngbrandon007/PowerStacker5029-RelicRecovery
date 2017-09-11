package pineapplerobotics.preseason.Progress.PineappleRobotPackage.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleRobot;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleColorSensor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleTouchSensor;

/**
 * Created by young on 8/7/2017.
 */
@TeleOp(name = "PineappleRobotColorSensorTest", group = "Linear Opmode")



public class ColorSensorExample  extends LinearOpMode {
    PineappleRobot robot;

    PineappleColorSensor color;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new PineappleRobot(this);

        color = robot.sensorHandler.newColorSensor("c");

        robot.mapRobot();

        waitForStart();
        while(opModeIsActive()) {
            robot.sayFeedBack("alpha", color.getValue(PineappleEnum.PineappleSensorEnum.CSALPHA));
            robot.sayFeedBack("red", color.getValue(PineappleEnum.PineappleSensorEnum.CSRED));
            robot.sayFeedBack("green", color.getValue(PineappleEnum.PineappleSensorEnum.CSBLUE));
            robot.sayFeedBack("blue", color.getValue(PineappleEnum.PineappleSensorEnum.CSGREEN));
            robot.sayFeedBack("argb", color.getValue(PineappleEnum.PineappleSensorEnum.CSARGB));
            robot.updateFeedBack();
        }
    }
}