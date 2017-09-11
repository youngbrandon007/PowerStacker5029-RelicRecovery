package pineapplerobotics.preseason.Progress.PineappleRobotPackage.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleMotor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleRobot;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleTouchSensor;

/**
 * Created by young on 8/7/2017.
 */
@TeleOp(name = "PineappleRobotEncoderDriveTest", group = "Linear Opmode")


public class encoderDriveTest extends LinearOpMode {
    PineappleRobot robot;

    PineappleMotor left;
    PineappleMotor right;
    PineappleTouchSensor touch;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new PineappleRobot(this);

        left = robot.motorHandler.newDriveMotor("left", 1, true , true, PineappleEnum.MotorLoc.LEFT, PineappleEnum.MotorType.NEV40);
        right = robot.motorHandler.newDriveMotor("right", 1, true , true, PineappleEnum.MotorLoc.RIGHT, PineappleEnum.MotorType.NEV40);

        touch = robot.sensorHandler.newTouchSensor("touch");

        robot.mapRobot();

        right.motorObject.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        robot.auto.encoderDrive(0.5, 4, PineappleEnum.MotorValueType.INCH,4);
        robot.drive.setDirectPower(-1, -1);
        Thread.sleep(1000);
    }
}
