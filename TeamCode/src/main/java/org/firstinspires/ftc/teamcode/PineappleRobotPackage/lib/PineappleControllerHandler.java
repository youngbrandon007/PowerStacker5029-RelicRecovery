package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleControllers.PineappleDeviceInterfaceModule;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleControllers.PineappleLegacyModule;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleControllers.PineappleMotorController;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleControllers.PineappleServoController;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleColorSensor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleGyroSensor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleOpticalDistanceSensor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleTouchSensor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleUltrasonicSensor;

/**
 * Created by young on 8/7/2017.
 */

public class PineappleControllerHandler {

    private PineappleResources resources;

    public PineappleControllerHandler(PineappleResources pineappleResources){
        resources = pineappleResources;
    }

    public PineappleDeviceInterfaceModule newDeviceInterfaceModule(String name){
        PineappleDeviceInterfaceModule controller = new PineappleDeviceInterfaceModule(name, resources);
        return controller;
    }
    public PineappleMotorController newMotorController(String name){
        PineappleMotorController controller = new PineappleMotorController(name, resources);
        return controller;
    }
    public PineappleLegacyModule newLegacyModule(String name){
        PineappleLegacyModule controller = new PineappleLegacyModule(name, resources);
        return controller;
    }
    public PineappleServoController newServoController(String name){
        PineappleServoController controller = new PineappleServoController(name, resources);
        return controller;
    }

}
