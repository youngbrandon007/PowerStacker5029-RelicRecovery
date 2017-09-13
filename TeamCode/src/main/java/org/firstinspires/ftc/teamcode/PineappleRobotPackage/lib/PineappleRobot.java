package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia.PineappleVuforia;

/**
 * Created by Brandon on 6/26/2017.
 */

public class PineappleRobot{

    public PineappleMotorHandler motorHandler;

    public PineappleDrive drive;

    public PineappleAuto auto;

    public PineappleSensorHandler sensorHandler;

    private PineappleResources resources;

    public PineappleVuforia vuforia;

    public PineappleServoHandler servoHandler;

    public PineappleRobot(LinearOpMode LOM){
        resources = new PineappleResources(LOM);
        motorHandler = new PineappleMotorHandler(resources);
        drive = new PineappleDrive(resources);
        auto = new PineappleAuto(resources, drive);
        sensorHandler = new PineappleSensorHandler(resources);
        servoHandler = new PineappleServoHandler(resources);
    }

    public void addVuforia(int maxTargets, VuforiaLocalizer.CameraDirection direction,  VuforiaLocalizer.Parameters.CameraMonitorFeedback feedback, String vuforiaLicenseKey){
        vuforia = new PineappleVuforia(resources,maxTargets, direction, feedback, vuforiaLicenseKey);
    }


    public void mapRobot(){
        motorHandler.mapMotors();
    }

    public void sayFeedBack(String objectName, double value){
        resources.feedBack.sayFeedBackWithOutUpdate(objectName, value);
    }
    public void sayFeedBack(String objectName, String value){
        resources.feedBack.sayFeedBackWithOutUpdate(objectName, value);
    }
    public void updateFeedBack(){
        resources.feedBack.update();
    }

}


