package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobotConstants;

/**
 * Created by Brandon on 1/8/2018.
 */

public class Constants {

    public static class drive{
        public static final double wheelSize = 4.0;
        public static final double gearRatio = 2.0/3.0;
        public static final double wheelCir = wheelSize * Math.PI;
        public static final double encoderCountPerInch = 1.0/wheelCir * PineappleRobotConstants.NEV40CPR;

        public static int countsPerInches(double inches){
            double counts = inches * encoderCountPerInch * gearRatio;
            return (int) counts;
        }
    }

    public static class auto{
        public static class RF{

        }
    }
}