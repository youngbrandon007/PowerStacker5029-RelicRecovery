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
        }
    }
}
