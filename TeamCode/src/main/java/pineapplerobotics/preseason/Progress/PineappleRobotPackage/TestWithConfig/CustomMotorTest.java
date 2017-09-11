
package pineapplerobotics.preseason.Progress.PineappleRobotPackage.TestWithConfig;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Brandon on 3/31/2017.
 */

@TeleOp(name = "BlankLinearOpMode", group = "Linear Opmode")
@Disabled


public class CustomMotorTest extends com.qualcomm.robotcore.eventloop.opmode.LinearOpMode {

    robotConfig r = new robotConfig();

    @Override
    public void runOpMode() throws InterruptedException {

        r.config(this);

        waitForStart();

        while (opModeIsActive()) {

            r.testMotor.update(gamepad1.left_stick_x);
        }
    }

}

