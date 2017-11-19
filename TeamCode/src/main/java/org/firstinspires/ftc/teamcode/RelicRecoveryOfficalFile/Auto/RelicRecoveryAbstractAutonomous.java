package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfig;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfigV2;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryVuforia;

/**
 * Created by young on 9/15/2017.
 */

abstract public class RelicRecoveryAbstractAutonomous extends RelicRecoveryConfigV2 {
    public double time;
    public boolean moreGlyph = true;
    public boolean gyroEnabled = true;
    public boolean pidEnabled = false;
    public boolean encoderEnabled = true;
    public boolean jewelsEnabled = true;
    public boolean vuforiaAlign = true;
    public boolean colorAlign = false;

    public LinearOpMode linearOpMode;

    public void AutoData(double time, boolean moreGlyph, boolean gyroEnabled, boolean pidEnabled, boolean encoderEnabled, boolean jewelsEnabled, boolean vuforiaAlign, boolean colorAlign, RelicRecoveryConfigV2 config) {
        this.time = time;
        this.moreGlyph = moreGlyph;
        this.gyroEnabled = gyroEnabled;
        this.pidEnabled = pidEnabled;
        this.encoderEnabled = encoderEnabled;
        this.jewelsEnabled = jewelsEnabled;
        this.vuforiaAlign = vuforiaAlign;
        this.colorAlign = colorAlign;
        this.linearOpMode = config;
        this.telemetry = linearOpMode.telemetry;
        this.hardwareMap = linearOpMode.hardwareMap;

    }

    public void alignToCrypto(VuforiaTrackableDefaultListener listener, VectorF vector) throws InterruptedException {

        boolean go = true;

        while (go && opModeIsActive()) {

            double gyroAngle = navx_device.getYaw();

            telemetry.addData("Gyro Angle", gyroAngle);
            telemetry.update();
            double rotation;

            if (gyroAngle > 1 && gyroAngle < 180) {
                //Put Gyro here
                rotation = .1;
                robotHandler.drive.tank.setPower(rotation, rotation);
            } else if (gyroAngle < 359 && gyroAngle > 179) {
                //and here
                rotation = -.1;
                robotHandler.drive.tank.setPower(rotation, rotation);
                Thread.sleep(100);
                robotHandler.drive.stop();
                Thread.sleep(50);

            } else {

                if (null != listener.getPose()) {

                    double robotAngle = RelicRecoveryVuforia.getRobotAngle(listener) - 45;
                    robotAngle += (robotAngle < 0) ? 360 : 0;

                    double distance = RelicRecoveryVuforia.getDistance(listener, vector);
                    double moveAngle = RelicRecoveryVuforia.getMoveAngle(listener, vector) + 90;
                    moveAngle -= (moveAngle > 360) ? 360 : 0;


                    robotHandler.sayFeedBack("Angle°", robotAngle);
                    robotHandler.sayFeedBack("Distance", distance);
                    robotHandler.sayFeedBack("Drive°", moveAngle);
                    robotHandler.sayFeedBack("Gyro Angle", gyroAngle);
                    robotHandler.updateFeedBack();
                    //Robot Rotation First

                }
            }
        }
        robotHandler.drive.stop();

    }
}


