package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Brandon on 1/8/2018.
 */
@Autonomous(name = "AUTO")
public class Auto extends Config {

    enum InitEnum{
        MAININIT, GYRO, VUFORIA, LOOKFORIMAGE, FINDKEYCOLUMN, FINDJEWEL
    }

    enum AutoEnum{
        JEWELDOWN, JEWELPROCESS, JEWELHIT, JEWELUP, JEWELRESET,
        ALIGNDRIVEOFFPLATFOR, ALIGNBACKINTOPLATFORM, ALIGNDRIVETOCENTER, ALIGNSTRAFFTOCENTERORTURN,
        KEYCOLUMNSET,
        GLYPHARMDOWN, GLYPHSTRAFFTOCOLUMN, GLYPHDRIVETOCRYPTO, GLYPHSTRAFFTOALIGN, GLYPHBOTHARMSDOWN, GLYPHPLACE, GLYPHPLACERESET,
        COLLECTDRIVEBACKFROMCRYPTO, COLLECTSTRAFFTOCENTER, COLLECTSTARTTRACKING, COLLECTGOTOPIT, COLLECTGLYPHS, COLLECTRETRACESTEPS, COLLECTPROCESSFORPLACING
    }



    @Override
    public void runOpMode() throws InterruptedException {


        InitEnum init = InitEnum.MAININIT;

        while(!opModeIsActive() && !isStopRequested()){
            switch(init){
                case MAININIT:
                    config(this);
                    break;
                case GYRO:
                    break;
                case VUFORIA:
                    break;
                case LOOKFORIMAGE:
                    break;
                case FINDKEYCOLUMN:
                    break;
                case FINDJEWEL:
                    break;
            }
        }
        waitForStart();

        AutoEnum auto = AutoEnum.JEWELDOWN;

        while (opModeIsActive()){
            switch(auto){
                case JEWELDOWN:
                    break;
                case JEWELPROCESS:
                    break;
                case JEWELHIT:
                    break;
                case JEWELUP:
                    break;
                case JEWELRESET:
                    break;
                case ALIGNDRIVEOFFPLATFOR:
                    break;
                case ALIGNBACKINTOPLATFORM:
                    break;
                case ALIGNDRIVETOCENTER:
                    break;
                case ALIGNSTRAFFTOCENTERORTURN:
                    break;
                case KEYCOLUMNSET:
                    break;
                case GLYPHARMDOWN:
                    break;
                case GLYPHSTRAFFTOCOLUMN:
                    break;
                case GLYPHDRIVETOCRYPTO:
                    break;
                case GLYPHSTRAFFTOALIGN:
                    break;
                case GLYPHBOTHARMSDOWN:
                    break;
                case GLYPHPLACE:
                    break;
                case GLYPHPLACERESET:
                    break;
                case COLLECTDRIVEBACKFROMCRYPTO:
                    break;
                case COLLECTSTRAFFTOCENTER:
                    break;
                case COLLECTSTARTTRACKING:
                    break;
                case COLLECTGOTOPIT:
                    break;
                case COLLECTGLYPHS:
                    break;
                case COLLECTRETRACESTEPS:
                    break;
                case COLLECTPROCESSFORPLACING:
                    break;
            }
        }
    }
}
