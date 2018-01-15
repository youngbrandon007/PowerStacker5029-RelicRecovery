package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleConfigLinearOpMode;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleServo;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;

/**
 * Created by Brandon on 1/8/2018.
 */

public abstract class Config extends PineappleConfigLinearOpMode{

    //DRIVE MOTORS
    public PineappleMotor driveFrontRight;
    public PineappleMotor driveFrontLeft;
    public PineappleMotor driveBackRight;
    public PineappleMotor driveBackLeft;

    //MOTORS
    public PineappleMotor motorLift;
    public PineappleMotor motorCollect;

    //SERVOS
    public PineappleServo servoFlipR;
    public PineappleServo servoFlipL;
    //SEROVPOSITIONS

    //SWITCH BOARD
    public RelicRecoveryEnums.AutoColor switchColor = RelicRecoveryEnums.AutoColor.RED;
    public RelicRecoveryEnums.StartingPosition switchPosition = RelicRecoveryEnums.StartingPosition.FRONT;
    public RelicRecoveryEnums.ColorPosition switchColorPosition = RelicRecoveryEnums.ColorPosition.REDFRONT;
    public boolean switchDelayEnabled = true;
    public double slideDelay = 0.0;
    //Still need detecting
    public boolean switchJewels = true;
    public boolean switchGlyphs = true;
    public boolean switchGlyphWhite = true;
    public boolean switchMoreGlyphs = false;
    public boolean switchPID = true;


    @Override
    public void config(LinearOpMode linearOpMode) {
        robotHandler = new PineappleRobot(linearOpMode);

        driveFrontRight = robotHandler.motorHandler.newDriveMotor("FR", 1, false, false, PineappleEnum.MotorLoc.RIGHTFRONT, PineappleEnum.MotorType.NEV40);
        driveFrontLeft = robotHandler.motorHandler.newDriveMotor("FL", 1, false, false, PineappleEnum.MotorLoc.LEFTFRONT, PineappleEnum.MotorType.NEV40);
        driveBackRight = robotHandler.motorHandler.newDriveMotor("BR", 1, false, false, PineappleEnum.MotorLoc.RIGHTBACK, PineappleEnum.MotorType.NEV40);
        driveBackLeft = robotHandler.motorHandler.newDriveMotor("BL", 1, false, false, PineappleEnum.MotorLoc.LEFTBACK, PineappleEnum.MotorType.NEV40);

        motorLift = robotHandler.motorHandler.newMotor("ML");
        motorCollect = robotHandler.motorHandler.newMotor("MC");

        servoFlipL = robotHandler.servoHandler.newLimitServo( "SL", 202.5, Constants.flip.rightDown);
        servoFlipR = robotHandler.servoHandler.newLimitServo("SR",202.5, Constants.flip.leftDown);
    }

    public void loadSwitchBoard(){
//        switchColor = (robotHandler.switchBoard.loadDigital("color")) ? RelicRecoveryEnums.AutoColor.BLUE : RelicRecoveryEnums.AutoColor.RED;
//        switchPosition = (robotHandler.switchBoard.loadDigital("position")) ? RelicRecoveryEnums.StartingPosition.FRONT : RelicRecoveryEnums.StartingPosition.BACK;
//        switchColorPosition = (switchColor == RelicRecoveryEnums.AutoColor.RED) ? (switchPosition == RelicRecoveryEnums.StartingPosition.FRONT) ? RelicRecoveryEnums.ColorPosition.REDFRONT : RelicRecoveryEnums.ColorPosition.REDBACK : (switchPosition == RelicRecoveryEnums.StartingPosition.FRONT) ? RelicRecoveryEnums.ColorPosition.BLUEFRONT : RelicRecoveryEnums.ColorPosition.BLUEBACK;
//        switchDelayEnabled = robotHandler.switchBoard.loadDigital("delayEnabled");
//        slideDelay = (switchDelayEnabled) ? robotHandler.switchBoard.loadAnalog("delay") : 0.0;
    }

    public void displaySwitchBorad(){
        String autonomousDescription = switchColor + " " + switchPosition + " @" + slideDelay + "s " + FontFormating.getMark(switchDelayEnabled) + "     PID" + FontFormating.getMark(switchPID);
        String autonomousSettings = "_o̲_o̲_" + FontFormating.getMark(switchJewels) + " " + FontFormating.getBox(switchGlyphWhite) + FontFormating.getMark(switchGlyphs) + " +" + FontFormating.getBox(switchGlyphWhite) + FontFormating.getMark(switchMoreGlyphs);
        telemetry.addLine(autonomousDescription);
        telemetry.addLine(autonomousSettings);
    }
}
