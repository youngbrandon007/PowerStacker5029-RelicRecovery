package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib;

/**
 * Created by Brandon on 9/13/2017.
 */

public class PineappleServoHandler {

    private PineappleResources resources;

    public PineappleServoHandler(PineappleResources r) {
        resources = r;
    }

    public PineappleServo newLimitServo(String name) {
        return new PineappleServo(resources, name, PineappleEnum.ServoType.LIMIT);
    }

    public PineappleServo newContinuousServo(String name){
        return new PineappleServo(resources, name, PineappleEnum.ServoType.CONTINUOUS);
    }
}
