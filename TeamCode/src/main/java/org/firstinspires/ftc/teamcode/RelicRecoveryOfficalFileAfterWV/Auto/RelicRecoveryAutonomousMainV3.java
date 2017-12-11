package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.Auto;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.RelicRecoveryResources.RelicRecoveryConfigV3;

/**
 * Created by Brandon on 12/5/2017.
 */

public class RelicRecoveryAutonomousMainV3 extends RelicRecoveryConfigV3 {

    String count = "a";

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);
        //load everything
        aLoadSwitchBoard();


        telemetry.update();
        //wait for start
        waitForStart();

        while (opModeIsActive()){
            switch (colorPosition){
                case REDFRONT:
                    switch (count){
                        case "b":
                            //Jewels
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