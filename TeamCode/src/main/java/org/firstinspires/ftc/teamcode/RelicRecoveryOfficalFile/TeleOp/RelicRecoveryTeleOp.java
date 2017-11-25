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


            //Collector
            float right_trigger1 = gamepad1.right_trigger;
            float right_trigger2 = gamepad2.right_trigger;
            float left_trigger1 = gamepad1.left_trigger;
            float left_trigger2 = gamepad2.left_trigger;

            if (gamepad1.right_bumper || gamepad2.right_bumper) {
                collector.setPosition(0);
                conveyRight.setPower(1);
                conveyLeft.setPower(-1);
            } else if (gamepad1.left_bumper || gamepad2.left_bumper) {
                collector.setPosition(1);
                conveyRight.setPower(-1);
                conveyLeft.setPower(1);
            } else if (right_trigger1 > 0.05 && right_trigger1 > right_trigger2) {
                collector.setPosition((1 - right_trigger1) / 2);
                conveyRight.setPower(right_trigger1);
                conveyLeft.setPower(-right_trigger1);
            } else if (right_trigger2 > 0.05) {
                collector.setPosition((1 - right_trigger2) / 2);
                conveyRight.setPower(right_trigger2);
                conveyLeft.setPower(-right_trigger2);
            } else if (left_trigger1 > 0.05 && left_trigger1 > left_trigger2) {
                collector.setPosition((left_trigger1 / 2) + .5);
                conveyRight.setPower(-left_trigger1);
                conveyLeft.setPower(left_trigger1);
            } else if (left_trigger2 > 0.05) {
                collector.setPosition((left_trigger2 / 2) + .5);
                conveyRight.setPower(-left_trigger2);
                conveyLeft.setPower(left_trigger2);
            } else if (gamepad1.x) {
                collector.setPosition(0);//just collector
                conveyRight.setPower(0);
                conveyLeft.setPower(0);
            } else {
                collector.setPosition(.5);
                conveyRight.setPower(0);
                conveyLeft.setPower(0);
            }

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

            //DRIVE
            robotHandler.drive.mecanum.updateMecanumMultiGamepad(gamepad1, directionA, gamepad2, directionB, 1, .5);


        }


    }
}