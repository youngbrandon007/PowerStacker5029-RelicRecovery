package pineapplerobotics.preseason.Progress.PineappleGamepadPackage;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Brandon on 7/12/2017.
 */

public abstract class PineappleLinearOpMode extends LinearOpMode{
    public PineappleGamepad pineappleGamepad1 = new PineappleGamepad(gamepad1);
    public PineappleGamepad pineappleGamepad2 = new PineappleGamepad(gamepad2);

    abstract public void run()throws InterruptedException;

    @Override
    public void runOpMode() throws InterruptedException {
        run();
    }
}
