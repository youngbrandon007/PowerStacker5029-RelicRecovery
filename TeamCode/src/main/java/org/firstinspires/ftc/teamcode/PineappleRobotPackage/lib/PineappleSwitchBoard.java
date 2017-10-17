package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleControllers.PineappleDeviceInterfaceModule;

/**
 * Created by Brandon on 10/17/2017.
 */

public class PineappleSwitchBoard {

    private PineappleResources resources;

    public PineappleSwitchBoard(PineappleResources r) {
        resources = r;
    }


    public boolean loadDigital(String name){
        DigitalChannel dig = resources.hardwareMap.digitalChannel.get(name);
        return dig.getState();
    }

    public double loadAnalog(String name){
        AnalogInput ana = resources.hardwareMap.analogInput.get(name);
        return ana.getVoltage();
    }
}
