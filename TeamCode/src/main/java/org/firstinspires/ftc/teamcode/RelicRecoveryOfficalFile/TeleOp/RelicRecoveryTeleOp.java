package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicRecoveryConfig;

/**
 * Created by young on 9/14/2017.
 */

public class RelicRecoveryTeleOp extends RelicRecoveryConfig {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        waitForStart();
        runtime.reset();


        while (opModeIsActive()){


            if(gamepad1.a){
                relicGrabberServo.setDegrees(90);
            }else{
                relicGrabberServo.setDegrees(0);
            }

            relicLinearLift.update(gamepad1.dpad_up,gamepad1.dpad_down);


            robotHandler.drive.setPower(gamepad1.left_stick_x, gamepad1.right_stick_x);


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }

    }
}
