package pineapplerobotics.preseason.Progress.PineappleRobotPackage;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Brandon on 6/26/2017.
 */

abstract public class PineappleConfig{
    public PineappleRobot robotHandler;

    abstract public void config(LinearOpMode linearOpMode);
}
