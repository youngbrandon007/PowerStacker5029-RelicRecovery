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

    public PineappleMotor relicLinearLift;
    public PineappleServo relicGrabberServo;

    public PineappleRelicRecoveryVuforia vuforia;

    public PineappleGyroSensor gyroSensor;

    @Override
    public void config(LinearOpMode linearOpMode) {
        robotHandler = new PineappleRobot(linearOpMode);

        driveFrontRight = robotHandler.motorHandler.newDriveMotor("frontright", 1, true, true, PineappleEnum.MotorLoc.RIGHT, PineappleEnum.MotorType.NEV40);
        driveFrontLeft = robotHandler.motorHandler.newDriveMotor("frontleft", 1, true, true, PineappleEnum.MotorLoc.LEFT, PineappleEnum.MotorType.NEV40);

        relicLinearLift = robotHandler.motorHandler.newMotor("reliclift", -.2, .2,0,1, true, true, PineappleEnum.MotorType.NEV40);
        relicGrabberServo = robotHandler.servoHandler.newLimitServo("Grabber");

        gyroSensor = robotHandler.sensorHandler.newGyroSensor("gyro");

        vuforia = new PineappleRelicRecoveryVuforia(4, VuforiaLocalizer.CameraDirection.BACK, VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES, "AdB8VB7/////AAAAGcfBp9I80URFkfBQFUyM+ptmQXBAMGx0svJKz7QE2nm20mBc/zI5sZNHfuP/ziIm+sYnO7fvPqUbFs8QWjRyXVEDmW4mMj+S+l+yaYRkpGZ/pmHyXiDb4aemHx0m70BulMNIce4+NVaCW5S/5BWNNev/AU0P+uWnHYuKNWzD2dPaRuprC4R6b/DgD1zeio1xlssYb9in9mfzn76gChOrE5B0ql6Q9FiHC5cTdacq2lKjm5nlkTiXz1e2jhVK3SddGoqM4FQ3mFks7/A88hFzlPfIIk45K2Lh7GvcVjuIiqNj5mTLaZJVqlsKdTQnKS4trJcc1YV9sjdbmh1agtn1UePy91fDj9uWSBdXvpIowv4B");

        robotHandler.addCustomVuforia(vuforia);

        vuforia.addRelicRecoveryTrackables();



        vuforia.startRelicRecoveryTracking();


    }
}
