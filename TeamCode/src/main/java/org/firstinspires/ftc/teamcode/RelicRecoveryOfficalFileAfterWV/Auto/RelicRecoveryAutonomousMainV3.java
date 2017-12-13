package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.Auto;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.RelicRecoveryResources.RelicRecoveryConfigV3;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Brandon on 12/5/2017.
 */

public class RelicRecoveryAutonomousMainV3 extends RelicRecoveryConfigV3 {

    HashMap<Double, encoderPosition> encoderTracking = new HashMap<>();

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.update();

        //load robot
        config(this);
        //load everything
        aLoadSwitchBoard();

        //wait for start
        telemetry.addLine("Waiting for Start");
        telemetry.update();
        waitForStart();



        ElapsedTime time = new ElapsedTime();

        while (opModeIsActive()){
            switch (colorPosition){
                case REDFRONT:
                    switch ("a"){
                        case "b":
                            //TODO scan

                            break;
                        case "c":
                            //TODO jewels

                            break;
                        case "d":
                            //drive off platform
                            //TODO add encoder drive at the end
                            if(driveOffPlatform(.5))
                            break;
                        case "e":
                            //TODO turn

                            break;
                        case "f":
                            //TODO line up to wall

                            break;
                        case "g":
                            //TODO insert glyph

                            break;
                        case "h":
                            //only runs once
                            //TODO get ready to collect glyph and track encoders

                            time.reset();
                            break;
                        case "i":
                            //TODO drive to glyph and collect two


                            encoderTracking.put(time.milliseconds(),new encoderPosition(0,0,0,0));
                            break;
                        case "j":
                            //only runs once
                            //TODO stop encoders and


                            break;
                        case "k":
                            //TODO drive back using encoders

                            break;
                        default:
                            break;
                    }
                    break;
                case REDBACK:

                    break;
                case BLUEFRONT:

                    break;
                case BLUEBACK:

                    break;
            }
            
            telemetry.update();
        }
    }

    private int driveOff = 0;
    public boolean driveOffPlatform(double motorSpeed){
        robotHandler.drive.mecanum.update(motorSpeed, -motorSpeed);
        double robotTilt = getTilt();
        if(robotTilt > 3){
            driveOff = 1;
        }else if(robotTilt < 1 && driveOff == 1){
            driveOff = 2;
        }else if(driveOff == 3){
            //TODO encoder drive then return true
        }
        telemetry.addData("Drive Off", driveOff);
        return false;
    }

    public double getTilt(){
        return 0.0;
    }

    public double getHeading(){
        return 0.0;
    }
}

class encoderPosition{

    int FR = 0;
    int FL = 0;
    int BR = 0;
    int BL = 0;

    encoderPosition(int fr, int fl, int br, int bl){
        FR = fr;
        FL = fl;
        BR = br;
        BL = bl;
    }
}

