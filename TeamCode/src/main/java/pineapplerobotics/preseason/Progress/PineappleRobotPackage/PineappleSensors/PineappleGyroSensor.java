package pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleResources;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensor;

/**
 * Created by ftcpi on 8/7/2017.
 */

public class PineappleGyroSensor extends PineappleSensor {
    public GyroSensor gyroSensor;
    private PineappleResources resources;
    public PineappleGyroSensor (String name, PineappleResources pineappleResources){
        resources = pineappleResources;
        makeSensor(name, pineappleResources);
    }
    @Override
    public void makeSensor(String name, PineappleResources pineappleResources) {
        sensorName = name;
        gyroSensor = resources.hardwareMap.gyroSensor.get(sensorName);
    }
    public void GSResetZInt () {
        gyroSensor.resetZAxisIntegrator();
    }
    public void GSCalib(){
        gyroSensor.calibrate();
    }
    public boolean GSIsCalib(){
        return gyroSensor.isCalibrating();
    }
    public String GSStatus(){
        return gyroSensor.status();
    }
    public double getValue(PineappleEnum.PineappleSensorEnum pineappleSensorEnum) {
        switch (pineappleSensorEnum) {
            case GSX:
                return gyroSensor.rawX();
            case GSHEADING:
                return gyroSensor.getHeading();
            case GSROTATIONFRACTION:
                return gyroSensor.getRotationFraction();
            case GSY:
                return gyroSensor.rawY();
            case GSZ:
                return gyroSensor.rawZ();
            default:
                return 0;
        }
    }
}
