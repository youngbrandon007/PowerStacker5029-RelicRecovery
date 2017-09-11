package pineapplerobotics.preseason.Progress.PineappleGamepadPackage.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import pineapplerobotics.preseason.Progress.PineappleGamepadPackage.PineappleLinearOpMode;

/**
 * Created by Brandon on 7/12/2017.
 */

@TeleOp(name = "TestCustomGamepad", group = "Linear Opmode")

public class test extends PineappleLinearOpMode{


    @Override
    public void run() throws InterruptedException {
        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("test a button: ", pineappleGamepad1.a);
        }
    }
}
