package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicRecoveryConfig;

/**
 * Created by Brandon on 10/10/2017.
 */
@TeleOp(name = "RRmecanum", group = "Linear Opmode")

public class
RelicRecoveryMecanumTeleOP extends RelicRecoveryConfig {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        waitForStart();
        runtime.reset();

        while(opModeIsActive()){
            robotHandler.drive.updateMecanum(gamepad1, 1);
        }



    }
}