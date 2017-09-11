package pineapplerobotics.preseason.Progress.PineappleRobotPackage.TestWithConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleMotor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleConfig;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleRobot;

/**
 * Created by Brandon on 6/26/2017.
 */

public class robotConfig extends PineappleConfig {

    public PineappleMotor testMotor;

    public void config(LinearOpMode linearOpMode){
        robotHandler = new PineappleRobot(linearOpMode);
        testMotor = robotHandler.motorHandler.newMotor("testMotor", PineappleEnum.MotorType.NEV40);
        robotHandler.mapRobot();
    }
}
