package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.Auto;


import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.RelicRecoveryResources.RelicRecoveryConfigV3;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Brandon on 12/5/2017.
 */

public class RelicRecoveryAutonomousMainV3 extends RelicRecoveryConfigV3 {

    HashMap<Double, encoderPosition> encoderTracking = new HashMap<>();

    public enum Auto{
        SCAN, JEWELS, DRIVEOFFPLATFROM, TURN, LINEUPTOCRYPTO, PUTGLYPH, PREPARECOLLECT,COLLECT,PREPAREDRIVEBACKTOCRYPTO,DRIVEBACKTOCRYPTO
    }

    ElapsedTime time = new ElapsedTime();
    ElapsedTime encoderTrackingTime = new ElapsedTime();

    Auto auto = Auto.SCAN;

    @Override
    public void runOpMode() throws InterruptedException {
        time.reset();
        encoderTrackingTime.reset();
        telemetry.update();
        //load robot
        config(this);
        //load everything
        loadSwitchBoard();

        //wait for start
        telemetry.addLine("Waiting for Start");
        telemetry.addData("Time(milliseconds)",time.milliseconds());
        telemetry.update();
        waitForStart();



        //testing
        auto = Auto.PREPARECOLLECT;

        while (opModeIsActive()) {
            telemetry.addData("Time(milliseconds)",time.milliseconds());
            telemetry.addData("Running",auto);
            switch (colorPosition) {
                case REDFRONT:
                    switch (auto) {
                        case SCAN:
                            //TODO scan
                            break;
                        case JEWELS:
                            //TODO jewels
                            break;
                        case DRIVEOFFPLATFROM:
                            //drive off platform
                            //TODO add encoder drive at the end
                            if (driveOffPlatform(.5))
                                auto = Auto.TURN;
                            break;
                        case TURN:
                            //TODO turn
                            break;
                        case LINEUPTOCRYPTO:
                            //TODO line up to wall
                            break;
                        case PUTGLYPH:
                            //TODO insert glyph
                            break;
                        case PREPARECOLLECT:
                            //only runs once
                            encoderTrackingTime.reset();
                            auto = Auto.COLLECT;
                        case COLLECT:
                            //TODO drive to glyph and collect two


                            //speed inputs, encoders are alreading in function
                            saveValues(0.0,0.0,0.0,0.0);
                            break;
                        case PREPAREDRIVEBACKTOCRYPTO:
                            //only runs once
                            //TODO stop encoders and
                            auto = Auto.DRIVEBACKTOCRYPTO;
                        case DRIVEBACKTOCRYPTO:
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

    public boolean driveOffPlatform(double motorSpeed) {
        robotHandler.drive.mecanum.update(motorSpeed, -motorSpeed);
        double robotTilt = getTilt();
        if (robotTilt > 3) {
            driveOff = 1;
        } else if (robotTilt < 1 && driveOff == 1) {
            driveOff = 2;
        } else if (driveOff == 3) {
            //TODO encoder drive then return true
        }
        telemetry.addData("Drive Off", driveOff);
        return false;
    }

    //Gets encoder position closest to the inputed time
    public encoderPosition getValue(double time){
        Set<Double> keys = encoderTracking.keySet();
        Double distance = 100000000.0;
        Double out = 0.0;
        for (Double each : keys)
        {
            double difference = Math.abs(time - each);
            if (difference < distance){
                distance = difference;
                out = each;
            }
        }
        return encoderTracking.get(out);
    }

    public void saveValues(double fr, double fl, double br, double bl){
        encoderTracking.put(encoderTrackingTime.milliseconds(), new encoderPosition(driveFrontRight.motorObject.getCurrentPosition(),fr,driveFrontLeft.motorObject.getCurrentPosition(),fl,driveBackRight.motorObject.getCurrentPosition(),br,driveBackLeft.motorObject.getCurrentPosition(),br));
    }

    //get Sensors

    public double getTilt() {
        return 0.0;
    }

    public double getHeading() {
        return 0.0;
    }
}




class encoderPosition {

    int FR = 0;
    int FL = 0;
    int BR = 0;
    int BL = 0;
    double speedFR = 0.0;
    double speedFL = 0.0;
    double speedBR = 0.0;
    double speedBL = 0.0;

    encoderPosition(int fr, double speedfr, int fl, double speedfl, int br, double speedbr, int bl, double speedbl) {
        FR = fr;
        FL = fl;
        BR = br;
        BL = bl;
        speedFR = speedfr;
        speedFL = speedfl;
        speedBR = speedbr;
        speedBL = speedbl;
    }
}

