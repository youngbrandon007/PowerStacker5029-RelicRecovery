package pineapplerobotics.preseason.Progress.PineappleRobotPackage.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleMotor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleRobot;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleTouchSensor;

/**
 * Created by young on 8/7/2017.
 */

@TeleOp(name = "PineappleRobotTouchSensorTest", group = "Linear Opmode")



public class touchSensorExample extends LinearOpMode {
    PineappleRobot robot;

    PineappleTouchSensor touch;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new PineappleRobot(this);

        touch = robot.sensorHandler.newTouchSensor("t");

        robot.mapRobot();

        waitForStart();
        while(opModeIsActive()) {
            robot.sayFeedBack(touch.sensorName, touch.getValue(PineappleEnum.PineappleSensorEnum.TOUCH));
            robot.updateFeedBack();
        }
    }
}
