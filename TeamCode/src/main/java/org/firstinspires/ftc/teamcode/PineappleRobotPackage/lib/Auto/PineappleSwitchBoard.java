package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Auto;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleResources;

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
