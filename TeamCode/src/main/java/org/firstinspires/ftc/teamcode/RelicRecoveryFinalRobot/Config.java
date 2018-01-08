package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleConfigLinearOpMode;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;

/**
 * Created by Brandon on 1/8/2018.
 */

public abstract class Config extends PineappleConfigLinearOpMode{

    public PineappleMotor driveFrontRight;
    public PineappleMotor driveFrontLeft;
    public PineappleMotor driveBackRight;
    public PineappleMotor driveBackLeft;

    public RelicRecoveryEnums.AutoColor switchColor = RelicRecoveryEnums.AutoColor.RED;
    public RelicRecoveryEnums.StartingPosition switchPosition = RelicRecoveryEnums.StartingPosition.FRONT;
    public RelicRecoveryEnums.ColorPosition switchColorPosition = RelicRecoveryEnums.ColorPosition.REDFRONT;

    @Override
    public void config(LinearOpMode linearOpMode) {
        robotHandler = new PineappleRobot(linearOpMode);

        driveFrontRight = robotHandler.motorHandler.newDriveMotor("FR", 1, false, false, PineappleEnum.MotorLoc.RIGHTFRONT, PineappleEnum.MotorType.NEV40);
        driveFrontLeft = robotHandler.motorHandler.newDriveMotor("FL", 1, false, false, PineappleEnum.MotorLoc.LEFTFRONT, PineappleEnum.MotorType.NEV40);
        driveBackRight = robotHandler.motorHandler.newDriveMotor("BR", 1, false, false, PineappleEnum.MotorLoc.RIGHTBACK, PineappleEnum.MotorType.NEV40);
        driveBackLeft = robotHandler.motorHandler.newDriveMotor("BL", 1, false, false, PineappleEnum.MotorLoc.LEFTBACK, PineappleEnum.MotorType.NEV40);

    }

    public void loadSwitchBoard(){
        switchColor = (robotHandler.switchBoard.loadDigital("color")) ? RelicRecoveryEnums.AutoColor.BLUE : RelicRecoveryEnums.AutoColor.RED;
        switchPosition = (robotHandler.switchBoard.loadDigital("position")) ? RelicRecoveryEnums.StartingPosition.FRONT : RelicRecoveryEnums.StartingPosition.BACK;


        switchColorPosition = (switchColor == RelicRecoveryEnums.AutoColor.RED) ? (switchPosition == RelicRecoveryEnums.StartingPosition.FRONT) ? RelicRecoveryEnums.ColorPosition.REDFRONT : RelicRecoveryEnums.ColorPosition.REDBACK : (switchPosition == RelicRecoveryEnums.StartingPosition.FRONT) ? RelicRecoveryEnums.ColorPosition.BLUEFRONT : RelicRecoveryEnums.ColorPosition.BLUEBACK;
    }

    public void displaySwitchBorad(){
        telemetry.addData("Color", switchColor);
        telemetry.addData("Position", switchPosition);
    }
}
