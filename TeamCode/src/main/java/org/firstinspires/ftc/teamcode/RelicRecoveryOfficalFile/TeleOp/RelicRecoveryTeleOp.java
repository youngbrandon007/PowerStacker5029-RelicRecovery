package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicRecoveryConfig;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicRecoveryDriveTestConfig;

/**
 * Created by young on 9/14/2017.
 */
@TeleOp(name = "RelicRecoveryTeleOp", group = "Linear Opmode")


public class RelicRecoveryTeleOp extends RelicRecoveryDriveTestConfig {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {


            setMovement(mecDirectionFromJoystick(gamepad1),
                    mecSpeedFromJoystick(gamepad1),
                    mecSpinFromJoystick(gamepad1),
                    1);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }

    }
}
