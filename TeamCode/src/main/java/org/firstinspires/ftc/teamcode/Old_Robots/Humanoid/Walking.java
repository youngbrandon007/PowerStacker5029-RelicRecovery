package org.firstinspires.ftc.teamcode.Old_Robots.Humanoid;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Brandon on 8/26/2017.
 */
@TeleOp(name = "Humanoid", group = "Linear Opmode")
@Disabled
public class Walking extends LinearOpMode{
    HConfigLinearOpMode config = new HConfigLinearOpMode();


    @Override
    public void runOpMode() throws InterruptedException {
        config.config(this);

        waitForStart();



        while(opModeIsActive()){
//            if (gamepad1.a){
//                config.setHandFullPos();
//            }else{
//                config.setHandRestingPos();
//            }
            if (gamepad1.dpad_left){
//                config.Head.setPosition(0.7);
            } else if (gamepad1.dpad_right){
//                config.Head.setPosition(0.3);
            }else if (gamepad1.dpad_up){
                config.Sholder.update(-0.15);
            }else if (gamepad1.dpad_down){
                config.Sholder.update(0.15);
            } else {
//                config.Head.setPosition(0.5);
                config.Sholder.update(0);

            }
            config.robot.drive.tank.setPower(-gamepad1.right_stick_y, gamepad1.left_stick_y);
//            config.Elbow.update((gamepad1.right_stick_y >= 0.1) ? config.Elbow.update(0.05) : (gamepad1.right_stick_y <= -0.1) ? config.Elbow.update(-0.05) : config.Elbow.update(0));

        }
    }
}
