package org.firstinspires.ftc.teamcode.Old_Robots.Humanoid;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Brandon on 8/26/2017.
 * Fixed by Vishnu on 2/03/2018
 */
@TeleOp(name = "Humanoid", group = "Linear Opmode")
public class Walking extends LinearOpMode {
    HConfigLinearOpMode config = new HConfigLinearOpMode();


    @Override
    public void runOpMode() throws InterruptedException {
        config.config(this);

        waitForStart();


        while (opModeIsActive()) {
//
//            if (gamepad1.right_bumper){
//                config.rShoulder.update(-0.15);
//            }
//              else if (gamepad1.right_trigger > 0.2){
//                config.rShoulder.update(0.15);
//            }
//              else if (gamepad1.left_bumper){
//                config.lShoulder.update(-0.15);
//            }
//              else if (gamepad1.left_trigger > 0.2){
//                config.lShoulder.update(0.15);
//            }
//              else{
//                config.rShoulder.update(0);
//            }
//
//            config.robot.drive.tank.setPower(gamepad1.right_stick_y, -gamepad1.left_stick_y);
//
//        }
        }
    }
}
