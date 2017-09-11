package pineapplerobotics.preseason.Progress.PineappleRobotPackage;

/**
 * Created by ftcpi on 6/29/2017.
 */

public class PineappleEnum {

    public enum MotorLoc {
        RIGHT, LEFT, NONE, RIGHTFRONT, LEFTFRONT, RIGHTBACK, LEFTBACK
    }


    public enum Sensor {
        TOUCH
    }

    public enum PineappleSensorEnum {
        TOUCH, ODSRAWMAX, ODSRAW, ODSLIGHTDETECTED, CSRED, CSGREEN, CSBLUE, CSAVG, CSALPHA, CSARGB, GSX, GSY, GSZ, GSHEADING, GSROTATIONFRACTION, US
    }

    public enum MotorValueType {
        INCH, COUNTS, DEGREES, CM, RADIANS
    }

    public enum MotorType {
        NEV60, NEV40, NEV20, NEV3_7, UNDI
    }

    public enum condition {
        EQUAL, LESSTHAN, GREATERTHAN
    }
}

