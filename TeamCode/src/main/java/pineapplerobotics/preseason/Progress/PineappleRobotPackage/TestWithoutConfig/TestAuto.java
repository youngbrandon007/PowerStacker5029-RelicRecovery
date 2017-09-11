package pineapplerobotics.preseason.Progress.PineappleRobotPackage.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleMotor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleRobot;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleColorSensor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleGyroSensor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleOpticalDistanceSensor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleTouchSensor;

/**
 * Created by Brandon on 8/14/2017.
 */
@TeleOp(name = "PineappleRobotTestAuto", group = "Linear Opmode")

public class TestAuto extends LinearOpMode{

    PineappleRobot robot;

    PineappleMotor left;
    PineappleMotor right;

    PineappleOpticalDistanceSensor color;
    PineappleTouchSensor touch;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new PineappleRobot(this);

        left = robot.motorHandler.newDriveMotor("left", 2, false, false, PineappleEnum.MotorLoc.LEFT, PineappleEnum.MotorType.NEV40);
        right = robot.motorHandler.newDriveMotor("right", 2, false, false, PineappleEnum.MotorLoc.RIGHT, PineappleEnum.MotorType.NEV40);

        color = robot.sensorHandler.newOpticalDistanceSensor("o");
        touch = robot.sensorHandler.newTouchSensor("t");

        robot.mapRobot();

        right.motorObject.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        robot.auto.lineFollow(color, PineappleEnum.PineappleSensorEnum.ODSLIGHTDETECTED,touch, PineappleEnum.PineappleSensorEnum.TOUCH, PineappleEnum.condition.GREATERTHAN, 90, -.4);

    }
}
