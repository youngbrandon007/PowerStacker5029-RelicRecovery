package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleControllers;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LegacyModule;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleController;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleResources;

/**
 * Created by ftcpi on 8/7/2017.
 */

public class PineappleMotorController extends PineappleController {
    public DcMotorController dcMotorController;
    private PineappleResources resources;

    public PineappleMotorController(String name, PineappleResources pineappleResources) {
        resources = pineappleResources;
        makeController(name, pineappleResources);
    }

    @Override
    public void makeController(String name, PineappleResources pineappleResources) {
        controllerName = name;
        dcMotorController = resources.hardwareMap.dcMotorController.get(controllerName);
    }


}
