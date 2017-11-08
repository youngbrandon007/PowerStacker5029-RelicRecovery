package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.AutomousBranches.Red;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.RelicRecoveryAbstractAutonomous;

/**
 * Created by young on 9/15/2017.
 */

public class RedFrontAuto extends RelicRecoveryAbstractAutonomous {

    @Override
    public void runOpMode() throws InterruptedException {
        config(linearOpMode);

        linearOpMode.waitForStart();


        //jewel

        robotHandler.sayFeedBack("It IS", "WORKING");
        robotHandler.updateFeedBack();
/*
        String dis = "24in";

        switch (boxLoc) {
            case CENTER:
                dis = "24in";
                break;
            case RIGHT:
                dis = "16.37in";
                break;
            case LEFT:
                dis = "31.63in";
                break;
        }

        robotHandler.auto.encoderDrive(.2, dis, 4);


        robotHandler.auto.turnUntil(gyroSensor, PineappleEnum.PineappleSensorEnum.GSHEADING, PineappleEnum.Condition.EQUAL,270, .2,-.2);
*/

        //Align for Block insert




        //Back up to glyph

        while(opModeIsActive()) {
            robotHandler.drive.mecanum.setPower(-.5, -.5);

            //run collector

            Thread.sleep(1000);

            robotHandler.drive.mecanum.setPower(-.1, -.1);

            Thread.sleep(2000);

            robotHandler.drive.stop();

            Thread.sleep(1000);

            robotHandler.drive.mecanum.setPower(.4, .4);

            Thread.sleep(750);

            //Search for Vuforia

            //Align using vuforia
        }

    }
}

