package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Controllers;

import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleController;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleResources;

/**
 * Created by ftcpi on 8/7/2017.
 */

public class PineappleServoController extends PineappleController {
    public ServoController servoController;

    private PineappleResources resources;

    public PineappleServoController(String name, PineappleResources pineappleResources) {
        resources = pineappleResources;
        makeController(name, pineappleResources);
    }

    @Override
    public void makeController(String name, PineappleResources pineappleResources) {
        controllerName = name;
        servoController = resources.hardwareMap.servoController.get(controllerName);

    }



}
