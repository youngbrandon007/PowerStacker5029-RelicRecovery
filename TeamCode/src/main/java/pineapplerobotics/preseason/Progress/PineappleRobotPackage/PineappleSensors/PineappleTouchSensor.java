package pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleResources;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleRobot;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensor;

/**
 * Created by ftcpi on 8/7/2017.
 */

public class PineappleTouchSensor extends PineappleSensor {
    public TouchSensor  touchSensor;
    private PineappleResources resources;
    public PineappleTouchSensor (String name, PineappleResources pineappleResources){
        resources = pineappleResources;
        makeSensor(name, pineappleResources);
    }
    @Override
    public void makeSensor(String name, PineappleResources pineappleResources) {
        sensorName = name;
        touchSensor = resources.hardwareMap.touchSensor.get(sensorName);
    }

    @Override
    public double getValue(PineappleEnum.PineappleSensorEnum sensor) {
        if (touchSensor.isPressed()) {
            return 1;
        } else{
            return 0;
        }

    }
}
