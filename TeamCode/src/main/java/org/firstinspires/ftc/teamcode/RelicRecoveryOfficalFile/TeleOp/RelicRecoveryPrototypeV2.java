package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfigPrototypeV2;

/**
 * Created by ftcpi on 12/10/2017.
 */
@TeleOp(name = "ProTele", group = "Linear Opmode")

public class RelicRecoveryPrototypeV2 extends RelicRecoveryConfigPrototypeV2 {
    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        flip.setPosition(FLIPDOWN);

        waitForStart();




        while(opModeIsActive()) {
            driveLeft.setPower(gamepad1.left_stick_y);
            driveRight.setPower(-gamepad1.right_stick_y);
            collector.setPower((gamepad1.right_bumper) ? 1 : (gamepad1.left_bumper) ? -1 : 0);
            lift.setPower((gamepad1.right_trigger > 0.05) ? .5 : (gamepad1.left_trigger > 0.05) ? -.5 : 0);

            flip.setPosition((gamepad1.x) ? FLIPDOWN : (gamepad1.y) ? FLIPFLAT : (gamepad1.b) ? FLIPUP : flip.servoObject.getPosition());

        }
    }
}
