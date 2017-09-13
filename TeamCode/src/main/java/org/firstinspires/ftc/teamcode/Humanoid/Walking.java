package org.firstinspires.ftc.teamcode.Humanoid;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Brandon on 8/26/2017.
 */

public class Walking extends LinearOpMode{
    HConfig config = new HConfig();


    @Override
    public void runOpMode() throws InterruptedException {
        config.config(this);

        waitForStart();

        int count = 1;

        while(true){



            count++;
            if(count == 201){
                count = 1;
            }
        }
    }
}
