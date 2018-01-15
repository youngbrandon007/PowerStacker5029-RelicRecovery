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
            robotHandler.drive.mecanum.updateMecanum(gamepad1,1.0);

            motorCollect.setPower((gamepad1.right_bumper) ? 1 : (gamepad1.left_bumper) ? -1 : 0);
            motorLift.setPower((gamepad1.dpad_up) ? -.3 : (gamepad1.dpad_down) ? .3 : 0);


            if(gamepad1.b){
                servoFlipL.setPosition(Constants.flip.leftUp);
                servoFlipR.setPosition(Constants.flip.rightUp);
                bClicked = true;
            }else if(bClicked = true){
                servoFlipL.setPosition(Constants.flip.leftFlat);
                servoFlipR.setPosition(Constants.flip.rightFlat);
                bClicked = false;
            }else if(gamepad1.a){
                servoFlipL.setPosition(Constants.flip.leftDown);
                servoFlipR.setPosition(Constants.flip.rightDown);
            }else if(gamepad1.dpad_up){
                servoFlipL.setPosition(Constants.flip.leftFlat);
                servoFlipR.setPosition(Constants.flip.rightFlat);
            }
        }
    }
}
