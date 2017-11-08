package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfig;

/**
 * Created by young on 9/14/2017.
 */
@TeleOp(name = "RelicRecoveryTeleOp", group = "Linear Opmode")
@Disabled
public class RelicRecoveryTeleOp extends RelicRecoveryConfig {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {



//            robotHandler.drive.setPower(gamepad1.left_stick_x, gamepad1.right_stick_x);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }

    }
}
