package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Brandon on 1/8/2018.
 */
@Autonomous(name = "AUTO")
public class Auto extends Config {

    enum InitEnum{
        GYRO, VUFORIA, LOOKFORIMAGE, FINDKEYCOLUMN, FINDJEWEL, LOOP
    }

    enum AutoEnum{
        WAIT,
        JEWELDOWN, JEWELPROCESS, JEWELHIT, JEWELUP, JEWELRESET,
        ALIGNDRIVEOFFPLATFORM, ALIGNBACKINTOPLATFORM, ALIGNDRIVETOCENTER, ALIGNSTRAFFTOCENTERORTURN,
        KEYCOLUMNSET,
        GLYPHARMDOWN, GLYPHSTRAFFTOCOLUMN, GLYPHDRIVETOCRYPTO, GLYPHSTRAFFTOALIGN, GLYPHBOTHARMSDOWN, GLYPHPLACE, GLYPHPLACERESET,
        COLLECTDRIVEBACKFROMCRYPTO, COLLECTSTRAFFTOCENTER, COLLECTSTARTTRACKING, COLLECTGOTOPIT, COLLECTGLYPHS, COLLECTRETRACESTEPS, COLLECTPROCESSFORPLACING
    }

    ElapsedTime wait = new ElapsedTime();

    AutoEnum auto = AutoEnum.JEWELDOWN;
    InitEnum init = InitEnum.GYRO;

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        //INIT LOOP
        while(!opModeIsActive() && !isStopRequested()){
            telemetry.addData("AUTO",init);
            //Switch Board Loading
            loadSwitchBoard();
            displaySwitchBorad();
            switch(init){
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
                case LOOP:
                    break;
            }
            telemetry.update();
        }

        waitForStart();
        wait.reset();

        //MAIN LOOP
        while (opModeIsActive()){
            telemetry.addData("AUTO", auto);
            switch(auto){
                case WAIT:
                    //if (!switchDelayEnabled || wait.seconds())
                    break;
                case JEWELDOWN:
                    //Check for Jewels enabled
                    if (!switchJewels) auto = AutoEnum.ALIGNDRIVEOFFPLATFORM;
                    break;
                case JEWELPROCESS:
                    break;
                case JEWELHIT:
                    break;
                case JEWELUP:
                    break;
                case JEWELRESET:
                    break;
                case ALIGNDRIVEOFFPLATFORM:
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
            telemetry.update();
        }
    }
    //JEWEL FUNCTIONS HERE



    //ALIGN FUNCTIONS HERE



    //GLYPH FUNCTIONS HERE



    //COLLECT FUNCTIONS HERE



    //GENERAL FUNCTIONS HERE


    //gyro Functions
    //Has all turning abilities PID and not return amount robot needs to turn
    double targetHeading = 0.0;
    public double updateTurning(){
        return 0.0;
    }

    public boolean isRobotCorrectDirection(){
        return true;
    }

    public double getHeading(){
        return 0.0;
    }
}
