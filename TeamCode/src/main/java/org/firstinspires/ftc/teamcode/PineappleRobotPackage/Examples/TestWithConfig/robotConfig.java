package org.firstinspires.ftc.teamcode.PineappleRobotPackage.Examples.TestWithConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleConfig;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;

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
