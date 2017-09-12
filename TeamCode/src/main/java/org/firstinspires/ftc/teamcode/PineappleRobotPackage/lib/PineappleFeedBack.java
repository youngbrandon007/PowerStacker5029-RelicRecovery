package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by ftcpi on 6/29/2017.
 */

public class PineappleFeedBack {

    public boolean giveFeedBack = true;

    public Telemetry telemetry;

    public PineappleFeedBack(Telemetry t){
        telemetry = t;
    }

    public void sayFeedBack(String objectName, double amount){
        if(giveFeedBack){
            telemetry.addData(objectName, amount);
            telemetry.update();
        }
    }

    public void sayFeedBackWithOutUpdate(String objectName, double amount){
        if(giveFeedBack){
            telemetry.addData(objectName, amount);
        }
    }
    public void sayFeedBackWithOutUpdate(String objectName, String amount){
        if(giveFeedBack){
            telemetry.addData(objectName, amount);
        }
    }
    public void update(){
        telemetry.update();
    }
}

