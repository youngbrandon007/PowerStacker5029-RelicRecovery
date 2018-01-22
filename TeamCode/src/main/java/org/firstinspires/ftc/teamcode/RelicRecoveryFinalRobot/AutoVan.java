package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Brandon on 1/8/2018.
 */
@Autonomous(name = "AUTO Van")
@Disabled
public class AutoVan extends Config {

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        waitForStart();
        robotHandler.drive.mecanum.setMecanum(Math.toRadians(270), 0.3, 0, 1);
        Thread.sleep(1500);
        servoFlipL.setPosition(Constants.flip.leftUp);
        servoFlipR.setPosition(Constants.flip.rightUp);
        Thread.sleep(500);
        robotHandler.drive.mecanum.setMecanum(Math.toRadians(270), 0.5, 0, 1);
        Thread.sleep(2000);
        robotHandler.drive.mecanum.setMecanum(Math.toRadians(90), 0.5, 0, 1);

        Thread.sleep(500);

    }

}
