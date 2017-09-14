package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleConfigLinearOpMode;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleServo;

/**
 * Created by young on 9/14/2017.
 */

abstract class RelicRecoveryConfig extends PineappleConfigLinearOpMode {

    public PineappleMotor driveFrontRight;
    public PineappleMotor driveFrontLeft;

    public PineappleMotor relicLinearLift;
    public PineappleServo relicGrabberServo;

    @Override
    public void config(LinearOpMode linearOpMode) {
        robotHandler = new PineappleRobot(linearOpMode);

        driveFrontRight = robotHandler.motorHandler.newDriveMotor("frontright", 1, true, true, PineappleEnum.MotorLoc.RIGHT, PineappleEnum.MotorType.NEV40);
        driveFrontLeft = robotHandler.motorHandler.newDriveMotor("frontleft", 1, true, true, PineappleEnum.MotorLoc.LEFT, PineappleEnum.MotorType.NEV40);

        relicLinearLift = robotHandler.motorHandler.newMotor("reliclift", 1, true, true, PineappleEnum.MotorType.NEV40);
        relicGrabberServo = robotHandler.servoHandler.newLimitServo("Grabber");
    }
}
