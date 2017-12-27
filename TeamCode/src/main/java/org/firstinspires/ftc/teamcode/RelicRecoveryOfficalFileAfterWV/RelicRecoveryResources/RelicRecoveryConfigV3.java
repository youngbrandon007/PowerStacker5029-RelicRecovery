package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.RelicRecoveryResources;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleConfigLinearOpMode;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;

/**
 * Created by Brandon on 12/5/2017.
 */

public abstract class RelicRecoveryConfigV3 extends PineappleConfigLinearOpMode{
    /*
    TODO Add sensors-gyro, lever buttons,
     */

    //Robot
    public PineappleMotor driveFrontRight;
    public PineappleMotor driveFrontLeft;
    public PineappleMotor driveBackRight;
    public PineappleMotor driveBackLeft;


    //switch Board
    public double delay = 0;
    public RelicRecoveryEnums.AutoColor color = RelicRecoveryEnums.AutoColor.BLUE;
    public RelicRecoveryEnums.StartingPosition position = RelicRecoveryEnums.StartingPosition.FRONT;
    public RelicRecoveryEnums.ColorPosition colorPosition = RelicRecoveryEnums.ColorPosition.BLUEFRONT;
    public boolean moreGlyph = false;
    public boolean glyphsEnabled = true;
    public boolean delayEnabled = true;
    public boolean pidEnabled = false;
    public boolean jewelsEnabled = true;
    private boolean usingGyro = false;


    @Override
    public void config(LinearOpMode linearOpMode) {
        robotHandler = new PineappleRobot(linearOpMode);

        driveFrontRight = robotHandler.motorHandler.newDriveMotor("FR", 1, false, false, PineappleEnum.MotorLoc.RIGHTFRONT, PineappleEnum.MotorType.NEV40);
        driveFrontLeft = robotHandler.motorHandler.newDriveMotor("FL", 1, false, false, PineappleEnum.MotorLoc.LEFTFRONT, PineappleEnum.MotorType.NEV40);
        driveBackRight = robotHandler.motorHandler.newDriveMotor("BR", 1, false, false, PineappleEnum.MotorLoc.RIGHTBACK, PineappleEnum.MotorType.NEV40);
        driveBackLeft = robotHandler.motorHandler.newDriveMotor("BL", 1, false, false, PineappleEnum.MotorLoc.LEFTBACK, PineappleEnum.MotorType.NEV40);


    }

    public void loadSwitchBoard() {
        delay = robotHandler.switchBoard.loadAnalog("delay") * 2;
        color = (robotHandler.switchBoard.loadDigital("color")) ? RelicRecoveryEnums.AutoColor.BLUE : RelicRecoveryEnums.AutoColor.RED;
        position = (robotHandler.switchBoard.loadDigital("position")) ? RelicRecoveryEnums.StartingPosition.FRONT : RelicRecoveryEnums.StartingPosition.BACK;
        jewelsEnabled = robotHandler.switchBoard.loadDigital("jewel");
        pidEnabled = robotHandler.switchBoard.loadDigital("pid");
        glyphsEnabled = robotHandler.switchBoard.loadDigital("glyph");
        delayEnabled = robotHandler.switchBoard.loadDigital("delayEnabled");
        delay = Math.round(delay * 2) / 2.0;

        switch (color){

            case RED:
                switch (position){

                    case FRONT:
                        colorPosition = RelicRecoveryEnums.ColorPosition.REDFRONT;
                        break;
                    case BACK:
                        colorPosition = RelicRecoveryEnums.ColorPosition.REDBACK;
                        break;
                }
                break;
            case BLUE:
                switch (position){

                    case FRONT:
                        colorPosition = RelicRecoveryEnums.ColorPosition.BLUEFRONT;
                        break;
                    case BACK:
                        colorPosition = RelicRecoveryEnums.ColorPosition.BLUEBACK;
                        break;
                }
                break;
        }
        telemetry.addData("Delay", delay);
        telemetry.addData("DelayEnabled", delayEnabled);
        telemetry.addData("Color", color);
        telemetry.addData("Jewel", jewelsEnabled);
        telemetry.addData("PID", pidEnabled);
        telemetry.addData("Glyphs", glyphsEnabled);
        telemetry.addData("Position", position);
    }
}
