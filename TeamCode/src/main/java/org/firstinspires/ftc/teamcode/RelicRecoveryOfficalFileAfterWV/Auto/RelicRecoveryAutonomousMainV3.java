package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.Auto;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.RelicRecoveryResources.RelicRecoveryConfigV3;

/**
 * Created by Brandon on 12/5/2017.
 */

public class RelicRecoveryAutonomousMainV3 extends RelicRecoveryConfigV3 {


    autoCount count;

    @Override
    public void runOpMode() throws InterruptedException {
        count = new autoCount(telemetry);
        count.setCount("a");
        count.sayCount();
        telemetry.update();
        //load robot
        config(this);
        //load everything
        aLoadSwitchBoard();


        //wait for start
        waitForStart();
        count.setCount("b");

        while (opModeIsActive()){
            switch (colorPosition){
                case REDFRONT:
                    switch (count.getCount()){
                        case "b":
                            //scan
                            break;
                        case "c":
                            //jewels
                            break;
                        case "d":
                            //drive off platform
                            break;
                        case "e":
                            //turn
                            break;
                        case "f":
                            //line up to wall
                            break;
                        case "g":
                            //insert glyph
                            break;
                        case "h":
                            //collect glyph
                            break;
                        case "i":
                            //drive back using encoders
                            break;
                    }
                    break;
                case REDBACK:

                    break;
                case BLUEFRONT:

                    break;
                case BLUEBACK:

                    break;
            }

            count.sayCount();
            telemetry.update();
        }
    }



}

class autoCount{

    private Telemetry telemetry;
    private String count = "a";

    autoCount(Telemetry telemetry){
        this.telemetry = telemetry;
    }

    void setCount(String set){
        count = set;
    }

    void sayCount(){
        telemetry.addData("Count", count);
    }

    String getCount(){
        return count;
    }
}