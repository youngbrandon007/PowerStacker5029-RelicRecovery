package org.firstinspires.ftc.teamcode.PineappleRobotPackage.Examples.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleOpticalDistanceSensor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleTouchSensor;

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

        robot.auto.lineFollow(color, PineappleEnum.PineappleSensorEnum.ODSLIGHTDETECTED,touch, PineappleEnum.PineappleSensorEnum.TOUCH, PineappleEnum.Condition.GREATERTHAN, 90, -.4);

    }
}
