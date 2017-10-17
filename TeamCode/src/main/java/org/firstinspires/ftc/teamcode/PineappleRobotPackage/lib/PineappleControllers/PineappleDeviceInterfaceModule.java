package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleControllers;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleController;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleResources;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensor;

/**
 * Created by ftcpi on 8/7/2017.
 */

public class PineappleDeviceInterfaceModule extends PineappleController {
    public DeviceInterfaceModule deviceInterfaceModule;
    private PineappleResources resources;

    public PineappleDeviceInterfaceModule(String name, PineappleResources pineappleResources) {
        resources = pineappleResources;
        makeController(name, pineappleResources);
    }

    @Override
    public void makeController(String name, PineappleResources pineappleResources) {
        controllerName = name;
        deviceInterfaceModule = resources.hardwareMap.deviceInterfaceModule.get(controllerName);
    }


}
