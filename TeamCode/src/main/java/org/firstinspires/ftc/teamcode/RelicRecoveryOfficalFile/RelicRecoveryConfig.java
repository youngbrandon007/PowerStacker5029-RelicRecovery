package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleConfigLinearOpMode;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleGyroSensor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleServo;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia.PineappleRelicRecoveryVuforia;

/**
 * Created by young on 9/14/2017.
 */

abstract public class RelicRecoveryConfig extends PineappleConfigLinearOpMode {

    public PineappleMotor driveFrontRight;
    public PineappleMotor driveFrontLeft;
    public PineappleMotor driveBackRight;
    public PineappleMotor driveBackLeft;

    public PineappleMotor relicLinearLift;
    public PineappleServo relicGrabberServo;

    public PineappleRelicRecoveryVuforia vuforia;

    public PineappleGyroSensor gyroSensor;

    @Override
    public void config(LinearOpMode linearOpMode) {
        robotHandler = new PineappleRobot(linearOpMode);

        driveFrontRight = robotHandler.motorHandler.newDriveMotor("FR", 1, true, true, PineappleEnum.MotorLoc.RIGHT, PineappleEnum.MotorType.NEV40);
        driveFrontLeft = robotHandler.motorHandler.newDriveMotor("FL", 1, true, true, PineappleEnum.MotorLoc.LEFT, PineappleEnum.MotorType.NEV40);
        driveBackRight = robotHandler.motorHandler.newDriveMotor("BR", 1, true, true, PineappleEnum.MotorLoc.RIGHT, PineappleEnum.MotorType.NEV40);
        driveBackLeft = robotHandler.motorHandler.newDriveMotor("BL", 1, true, true, PineappleEnum.MotorLoc.LEFT, PineappleEnum.MotorType.NEV40);
//
//        relicLinearLift = robotHandler.motorHandler.newMotor("reliclift", -.2, .2,0,1, true, true, PineappleEnum.MotorType.NEV40);
//        relicGrabberServo = robotHandler.servoHandler.newLimitServo("Grabber");
//
//        gyroSensor = robotHandler.sensorHandler.newGyroSensor("gyro");

        robotHandler.addCustomVuforia(vuforia);

        vuforia.addRelicRecoveryTrackables();



        vuforia.startRelicRecoveryTracking();


    }

}
