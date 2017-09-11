package pineapplerobotics.preseason.Progress.PineappleRobotPackage.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleMotor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleRobot;

/**
 * Created by young on 8/2/2017.
 */


@TeleOp(name = "PineappleRobotEncoderTest", group = "Linear Opmode")


public class encoderTest extends LinearOpMode {
    PineappleRobot robot;

    PineappleMotor motor;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new PineappleRobot(this);

        motor = robot.motorHandler.newMotor("motor", 1, true , true, PineappleEnum.MotorType.NEV40);


        robot.mapRobot();

        waitForStart();
        motor.encoderDrive(1, 90, PineappleEnum.MotorValueType.DEGREES, 4);

        sleep(1000);
        telemetry.addData("Encoder", motor.motorObject.getCurrentPosition());
        telemetry.update();
        sleep(1000);
    }
}


