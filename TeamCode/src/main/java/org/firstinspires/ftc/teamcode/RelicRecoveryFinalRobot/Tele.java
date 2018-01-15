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

        while (opModeIsActive()){
            robotHandler.drive.mecanum.updateMecanum(gamepad1,1.0);

            motorCollect.setPower((gamepad1.right_bumper) ? 1 : (gamepad1.left_bumper) ? -1 : 0);
            motorLift.setPower((gamepad1.dpad_up) ? .3 : (gamepad1.dpad_down) ? -.3 : 0);

            servoFlipL.setPosition((gamepad1.a) ? servoLeftUp : servoLeftDown);
            servoFlipR.setPosition((gamepad1.a) ? servoRightUp : servoRightDown);
        }
    }
}
