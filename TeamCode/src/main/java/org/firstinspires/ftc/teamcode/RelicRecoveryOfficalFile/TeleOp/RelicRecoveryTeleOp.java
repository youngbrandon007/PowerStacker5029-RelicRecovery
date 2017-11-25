package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfig;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfigV2;


/**
 * Created by A Pineapple on 999/999/2021.
 */
@TeleOp(name = "Tele Op", group = "Linear Opmode")
public class RelicRecoveryTeleOp extends RelicRecoveryConfigV2 {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        waitForStart();
        runtime.reset();

        double directionA = 0;
        double directionB = 0;


        while (opModeIsActive()) {


            // Controller A
            if (gamepad1.dpad_up) {
                directionA = 0;
            }
            if (gamepad1.dpad_right) {
                directionA = -90;
            }
            if (gamepad1.dpad_down) {
                directionA = 180;
            }
            if (gamepad1.dpad_left) {
                directionA = 90;
            }

            // Drive
            robotHandler.drive.mecanum.updateMecanumDirection(gamepad1, 1, directionA);

            if (gamepad1.left_bumper || gamepad1.left_trigger > .25) {
                collector.setPosition(1);
                conveyRight.setPower(-1);
                conveyLeft.setPower(1);
            }

            else if (gamepad1.right_bumper || gamepad1.right_trigger > .25) {
                collector.setPosition(0);
                conveyRight.setPower(1);
                conveyLeft.setPower(-1);
            }

            else {
                collector.setPosition(0.5);
                conveyRight.setPower(0);
                conveyLeft.setPower(0);
            }

            // Cardinal Direction Toggle A



            // Controller B
            if (gamepad2.dpad_up) {
                directionB = 0;
            }
            if (gamepad2.dpad_right) {
                directionB = -90;
            }
            if (gamepad2.dpad_down) {
                directionB = 180;
            }
            if (gamepad2.dpad_left) {
                directionB = 90;
            }

            robotHandler.drive.mecanum.updateMecanumDirection(gamepad2, .5, directionB);

            // Triggers/Bumpers: Conveyor
            if (gamepad2.left_bumper || gamepad2.left_trigger > .25) {
                collector.setPosition(1);
                conveyRight.setPower(-1);
                conveyLeft.setPower(1);
            }

            else if (gamepad2.right_bumper || gamepad2.right_trigger > .25) {
                collector.setPosition(0);
                conveyRight.setPower(1);
                conveyLeft.setPower(-1);
            }

            else {
                collector.setPosition(0.5);
                conveyRight.setPower(0);
                conveyLeft.setPower(0);
            }

        }


    }
}