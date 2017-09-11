package pineapplerobotics.preseason.Progress.PineappleRobotPackage;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleColorSensor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleGyroSensor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleOpticalDistanceSensor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleTouchSensor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleUltrasonicSensor;

/**
 * Created by young on 8/7/2017.
 */

public class PineappleSensorHandler {

    private PineappleResources resources;

    public PineappleSensorHandler(PineappleResources pineappleResources){
        resources = pineappleResources;
    }

    public PineappleTouchSensor newTouchSensor(String name){
        PineappleTouchSensor sensor = new PineappleTouchSensor(name, resources);
        return sensor;
    }
    public PineappleUltrasonicSensor newUltrasonicSensor(String name){
        PineappleUltrasonicSensor sensor = new PineappleUltrasonicSensor(name, resources);
        return sensor;
    }
    public PineappleOpticalDistanceSensor newOpticalDistanceSensor(String name){
        PineappleOpticalDistanceSensor sensor = new PineappleOpticalDistanceSensor(name, resources);
        return sensor;
    }

    public PineappleColorSensor newColorSensor(String name){
        PineappleColorSensor sensor = new PineappleColorSensor(name, resources);
        return sensor;
    }

    public PineappleGyroSensor newGyroSensor(String name){
        PineappleGyroSensor sensor = new PineappleGyroSensor(name, resources);
        return sensor;
    }
}
