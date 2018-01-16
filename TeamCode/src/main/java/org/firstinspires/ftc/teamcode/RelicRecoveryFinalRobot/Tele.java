package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


/**
 * Created by Brandon on 1/8/2018.
 */
@TeleOp(name = "TeleOp")

public class Tele extends Config{
    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        waitForStart();

        boolean bClicked = false;

        while (opModeIsActive()){
            robotHandler.drive.mecanum.updateMecanum(gamepad1,(gamepad1.right_bumper) ? 0.7 : 1.0);

            motorCollect.setPower((gamepad1.right_trigger > 0.10) ? gamepad1.right_trigger : (gamepad1.left_trigger > 0.10) ? -gamepad1.left_trigger : (gamepad2.right_bumper) ? -1 : (gamepad2.left_bumper) ? 1 : 0);
            motorLift.setPower((gamepad2.dpad_up) ? -1 : (gamepad2.dpad_down) ? .5 : 0);

            if(gamepad2.b){
                servoFlipL.setPosition(Constants.flip.leftUp);
                servoFlipR.setPosition(Constants.flip.rightUp);
            }else if(gamepad2.x){
                servoFlipL.setPosition(Constants.flip.leftFlat);
                servoFlipR.setPosition(Constants.flip.rightFlat);
            }else if(gamepad2.a){
                servoFlipL.setPosition(Constants.flip.leftDown);
                servoFlipR.setPosition(Constants.flip.rightDown);
            }

            telemetry.update();


        }
    }
}
