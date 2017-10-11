package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicRecoveryConfig;

/**
 * Created by Brandon on 10/10/2017.
 */
@TeleOp(name = "RRmecanumTest", group = "Linear Opmode")

public class RelicRecoveryMecanumTest extends RelicRecoveryConfig {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {
            robotHandler.drive.updateMecanum(gamepad1, 1);
//            robotHandler.drive.updateMecanum(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        }
    }
}