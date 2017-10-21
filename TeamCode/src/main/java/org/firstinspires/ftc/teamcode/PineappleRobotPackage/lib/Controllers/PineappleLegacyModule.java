package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Controllers;

import com.qualcomm.robotcore.hardware.LegacyModule;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleController;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleResources;

/**
 * Created by ftcpi on 8/7/2017.
 */

public class PineappleLegacyModule extends PineappleController {
    public LegacyModule legacyModule;
    private PineappleResources resources;

    public PineappleLegacyModule(String name, PineappleResources pineappleResources) {
        resources = pineappleResources;
        makeController(name, pineappleResources);
    }

    @Override
    public void makeController(String name, PineappleResources pineappleResources) {
        controllerName = name;
        legacyModule = resources.hardwareMap.legacyModule.get(controllerName);
    }


}
