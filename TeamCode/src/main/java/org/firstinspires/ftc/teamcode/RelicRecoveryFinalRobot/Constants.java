package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobotConstants;

import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.ciphers.BIRDBROWN;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.ciphers.BIRDGREY;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.ciphers.FROGBROWN;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.ciphers.FROGGREY;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.ciphers.SNAKEBROWN;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.ciphers.SNAKEGREY;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.glyph.BROWN;
import static org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot.Constants.auto.autoGlyph.glyph.GREY;

/**
 * Created by Brandon on 1/8/2018.
 */

public class Constants {
    public static class PID {
        public static final double P = 0.01441838;
        public static final double I = 0.0000488888;
        public static final double D = 0.02094917;
    }
    public static class alignment {
        public static final double ALIGNLEFTDOWN = 0.42;
        public static final double ALIGNLEFTUP = 0.7;
        public static final double ALIGNRIGHTDOWN = 0.63;
        public static final double ALIGNRIGHTUP = 0.0;
    }
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
        public static final double rightDown = 0.77;
        public static final double leftDown = 0.21;

        public static final double rightFlat = 0.67;
        public static final double leftFlat = 0.32;

        public static final double rightUp = 0.2;
        public static final double leftUp = 0.81;
    }

    public static class relic {
        public static final double grabClose = 0.15;
        public static final double grabIn = 0;
        public static final double grabOpen = 0.7;
        public static final double turnStraight = 0.7;
        public static final double turnDown = 0.1;
        public static final double turnFold = 1;

    }

    public static class auto {

        public static class autoGlyph {
            public enum glyph {
                GREY, BROWN, NONE
            }
            public enum column {
                LEFT, CENTER, RIGHT, NONE
            }
            //START IN TOP LEFT
            public static class ciphers {
                public static final autoGlyph.glyph[][] FROGGREY = {
                        {GREY, BROWN, GREY},
                        {BROWN, GREY, BROWN},
                        {GREY, BROWN, GREY},
                        {BROWN, GREY, BROWN}
                };
                public static final autoGlyph.glyph[][] FROGBROWN = {
                        {BROWN, GREY, BROWN},
                        {GREY, BROWN, GREY},
                        {BROWN, GREY, BROWN},
                        {GREY, BROWN, GREY}
                };
                public static final autoGlyph.glyph[][] BIRDGREY = {
                        {GREY, BROWN, GREY},
                        {BROWN, GREY, BROWN},
                        {BROWN, GREY, BROWN},
                        {GREY, BROWN, GREY}
                };
                public static final autoGlyph.glyph[][] BIRDBROWN = {
                        {BROWN, GREY, BROWN},
                        {GREY, BROWN, GREY},
                        {GREY, BROWN, GREY},
                        {BROWN, GREY, BROWN}
                };
                public static final autoGlyph.glyph[][] SNAKEGREY = {
                        {GREY, GREY, BROWN},
                        {GREY, BROWN, BROWN},
                        {BROWN, BROWN, GREY},
                        {BROWN, GREY, GREY}
                };
                public static final autoGlyph.glyph[][] SNAKEBROWN = {
                        {BROWN, BROWN, GREY},
                        {BROWN, GREY, GREY},
                        {GREY, GREY, BROWN},
                        {GREY, BROWN, BROWN}
                };

            }

            public static final autoGlyph.glyph[][][] CIPHERS = {FROGBROWN, FROGGREY,BIRDBROWN,BIRDGREY,SNAKEBROWN,SNAKEGREY };

        }

        public static class aligning {
            public static final double leftUp = .5;
            public static final double leftDown = .4;
            public static final double rightUp = .5;
            public static final double rightDown = .6;


            public static final double[][][] AlignArmPosition =
                    {
                            {{alignment.ALIGNRIGHTUP, alignment.ALIGNRIGHTUP, alignment.ALIGNRIGHTDOWN  },{alignment.ALIGNLEFTDOWN, alignment.ALIGNLEFTDOWN, alignment.ALIGNLEFTUP}},
                            {{alignment.ALIGNRIGHTUP, alignment.ALIGNRIGHTUP, alignment.ALIGNRIGHTUP  },{alignment.ALIGNLEFTDOWN, alignment.ALIGNLEFTDOWN,alignment.ALIGNLEFTDOWN}}
                    };
            public static final double[][] AlignDrivingDirection =
                    {
                            {0,0,180},
                            {0, 0,0}
                    };
            public static final boolean[][][] AlignSwitchClicked =
                    {
                            {{false, false, true}, {true, true, false}},
                            {{false, false, false}, {true, true, true}}
                    };
            public static final double[][] AlignDrivingOffPlatformEncoder =
                    {
                            {24 , 20, 18},
                            {0,0,0}
                    };
            public static final double[] AlignDriveOffPlatformDirection =
                    {270, 90};
            public static final double[] AlignTurnAngle =
                    {90, 90};

            public static final double CollectDistToPit = 15.0;
            public static final int collectDriveIntoPitTime = 4000;
            public static final double GlyphDistanceToCrypto = 10.0;
            public static final double CryptoDistanceDriveSideWays = 10.0;
            public static final double columnStraffDistance = 6;
            public static final double alignStraffDistance = 2;
        }

        public static class jewel {
            public enum jewelState {
                BLUE_RED, RED_BLUE, NON_NON
            }
            public enum jewelHitSide {
                RIGHT, LEFT, NONE
            }
            public static final double JEWELDOWN = 0.93;
            public static final double JEWELUP = 0.15;
            public static final double JEWELHITRIGHT = 0;
            public static final double JEWELHITLEFT = 1;
            public static final double JEWELHITCENTER = 0.5;

            public static final int JEWELDOWNMILI = 2000;
            public static final int JEWELUPMILI = 2500;
            public static final int JEWELHITMILI = 3000;

        }
    }

}
