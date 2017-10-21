package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Controllers;

import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleController;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleResources;

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
