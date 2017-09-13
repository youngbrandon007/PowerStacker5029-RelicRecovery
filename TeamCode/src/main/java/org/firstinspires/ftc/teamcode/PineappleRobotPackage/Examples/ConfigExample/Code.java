
package org.firstinspires.ftc.teamcode.PineappleRobotPackage.Examples.ConfigExample;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Brandon on 3/31/2017.
 */

@TeleOp(name = "BlankLinearOpMode", group = "Linear Opmode")
@Disabled


public class Code extends com.qualcomm.robotcore.eventloop.opmode.LinearOpMode {

    //UNTESTED

    Config r = new Config();

    @Override
    public void runOpMode() throws InterruptedException {

        r.config(this);

        waitForStart();

        while (opModeIsActive()) {

            r.testMotor.update(gamepad1.left_stick_x);
        }
    }

}

