package pineapplerobotics.preseason.Progress.PineappleRobotPackage;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;

/**
 * Created by young on 8/6/2017.
 */

public abstract class PineappleSensor {

    public String sensorName;
    
    public abstract void makeSensor(String name, PineappleResources pineappleResources);

    public abstract double getValue(PineappleEnum.PineappleSensorEnum pineappleSensorEnum);

}
