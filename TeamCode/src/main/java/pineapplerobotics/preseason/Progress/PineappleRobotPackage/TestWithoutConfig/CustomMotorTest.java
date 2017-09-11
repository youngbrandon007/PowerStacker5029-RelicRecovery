package pineapplerobotics.preseason.Progress.PineappleRobotPackage.TestWithoutConfig;

import com.qualcomm.hardware.motors.NeveRest40Gearmotor;
import com.qualcomm.hardware.motors.NeveRest60Gearmotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.configuration.MotorConfigurationType;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleMotor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleRobot;

/**
 * Created by Brandon on 6/26/2017.
 */

@TeleOp(name = "PineappleRobotTest", group = "Linear Opmode")

public class CustomMotorTest extends LinearOpMode{
    PineappleRobot robot;

    PineappleMotor testMotor;
    NeveRest60Gearmotor neveRest60Gearmotor;


    @Override
    public void runOpMode() throws InterruptedException {

        robot = new PineappleRobot(this);

        testMotor = robot.motorHandler.newMotor("test", 1, true , true, PineappleEnum.MotorType.NEV40);

        robot.mapRobot();



        waitForStart();
        while (opModeIsActive()){
            testMotor.update(gamepad1.left_stick_x);


        }
    }
}
