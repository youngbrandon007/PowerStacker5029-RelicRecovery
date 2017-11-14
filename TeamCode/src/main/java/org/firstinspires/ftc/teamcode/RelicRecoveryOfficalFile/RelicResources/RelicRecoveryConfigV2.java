package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleConfigLinearOpMode;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleServo;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Sensors.PineappleGyroSensor;

/**
 * Created by young on 9/14/2017.
 */

abstract public class RelicRecoveryConfigV2 extends PineappleConfigLinearOpMode {

    public PineappleMotor driveFrontRight;
    public PineappleMotor driveFrontLeft;
    public PineappleMotor driveBackRight;
    public PineappleMotor driveBackLeft;
    public PineappleMotor conveyRight;
    public PineappleMotor conveyLeft;

//    public PineappleServo phoneTurn;
//    public PineappleServo jewelRotation;
//    public PineappleServo jewelLever;
    public PineappleServo collector;


    @Override
    public void config(LinearOpMode linearOpMode) {
        robotHandler = new PineappleRobot(linearOpMode);


        driveFrontRight = robotHandler.motorHandler.newDriveMotor("FR", 1, false, false, PineappleEnum.MotorLoc.RIGHTFRONT, PineappleEnum.MotorType.NEV40);
        driveFrontLeft = robotHandler.motorHandler.newDriveMotor("FL", 1, false, false, PineappleEnum.MotorLoc.LEFTFRONT, PineappleEnum.MotorType.NEV40);
        driveBackRight = robotHandler.motorHandler.newDriveMotor("BR", 1, false, false, PineappleEnum.MotorLoc.RIGHTBACK, PineappleEnum.MotorType.NEV40);
        driveBackLeft = robotHandler.motorHandler.newDriveMotor("BL", 1, false, false, PineappleEnum.MotorLoc.LEFTBACK, PineappleEnum.MotorType.NEV40);
        conveyLeft = robotHandler.motorHandler.newDriveMotor("CL", 1, false, false, PineappleEnum.MotorLoc.NONE, PineappleEnum.MotorType.NEV40);
        conveyRight = robotHandler.motorHandler.newDriveMotor("CR", 1, false, false, PineappleEnum.MotorLoc.NONE, PineappleEnum.MotorType.NEV40);

//        phoneTurn = robotHandler.servoHandler.newLimitServo("PT", 1);
//        jewelRotation = robotHandler.servoHandler.newLimitServo("JR", 1);
//        jewelLever = robotHandler.servoHandler.newLimitServo("JL", 1);
        collector = robotHandler.servoHandler.newLimitServo("C", 1);
    }

}
