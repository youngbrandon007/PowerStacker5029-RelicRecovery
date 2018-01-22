package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobotConstants;

import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.glyph.BROWN;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.glyph.GREY;

/**
 * Created by Brandon on 1/8/2018.
 */

public class Constants {

    public static class drive {
        public static final double wheelSize = 4.0;
        public static final double gearRatio = 2.0 / 3.0;
        public static final double wheelCir = wheelSize * Math.PI;
        public static final double encoderCountPerInch = 1.0 / wheelCir * PineappleRobotConstants.NEV40CPR;

        public static int countsPerInches(double inches) {
            double counts = inches * encoderCountPerInch * gearRatio;
            return (int) counts;
        }
    }

    public static class flip {
        public static final double rightDown = 0.85;
        public static final double leftDown = 0.13;

        public static final double rightFlat = 0.7;
        public static final double leftFlat = 0.28;

        public static final double rightUp = 0.15;
        public static final double leftUp = 0.83;
    }

    public static class relic {
        public static final double grabClose = 0.1;
        public static final double grabOpen = 0.5;
    }

    public static class auto {
        public static class RF {

        }

        public static class autoGlyph {
            public enum glyph {
                GREY, BROWN, NONE
            }

            //START IN TOP LEFT
            public static class ciphers {
                public static final autoGlyph.glyph[][] FROGBROWN = {
                        {GREY, BROWN, GREY},
                        {BROWN, GREY, BROWN},
                        {GREY, BROWN, GREY},
                        {BROWN, GREY, BROWN}
                };
                public static final autoGlyph.glyph[][] FROGGREY = {
                        {BROWN, GREY, BROWN},
                        {GREY, BROWN, GREY},
                        {BROWN, GREY, BROWN},
                        {GREY, BROWN, GREY}
                };
                public static final autoGlyph.glyph[][] BIRDBROWN = {
                        {GREY, BROWN, GREY},
                        {BROWN, GREY, BROWN},
                        {BROWN, GREY, BROWN},
                        {GREY, BROWN, GREY}
                };
                public static final autoGlyph.glyph[][] BIRDGREY = {
                        {BROWN, GREY, BROWN},
                        {GREY, BROWN, GREY},
                        {GREY, BROWN, GREY},
                        {BROWN, GREY, BROWN}
                };
                public static final autoGlyph.glyph[][] SNAKEBROWN = {
                        {BROWN, GREY, GREY},
                        {BROWN, BROWN, GREY},
                        {GREY, BROWN, BROWN},
                        {GREY, GREY, BROWN}
                };
                public static final autoGlyph.glyph[][] SNAKEGREY = {
                        {GREY, BROWN, BROWN},
                        {GREY, GREY, BROWN},
                        {BROWN, GREY, GREY},
                        {BROWN, BROWN, GREY}
                };
            }
        }
    }

}
