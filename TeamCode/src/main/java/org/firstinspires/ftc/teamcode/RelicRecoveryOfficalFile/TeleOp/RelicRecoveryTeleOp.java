package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfig;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfigV2;


/**
 * Created by young on 9/14/2017.
 */
@TeleOp(name = "RelicRecoveryTeleOp", group = "Linear Opmode")
@Disabled
public class RelicRecoveryTeleOp extends RelicRecoveryConfigV2 {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {


            // Controller A

            // Drive
            robotHandler.drive.mecanum.updateMecanum(gamepad1, 1);

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

            // Cardinal Diretion Toggle

            
            // Controller B

            robotHandler.drive.mecanum.updateMecanum(gamepad2, .5);

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