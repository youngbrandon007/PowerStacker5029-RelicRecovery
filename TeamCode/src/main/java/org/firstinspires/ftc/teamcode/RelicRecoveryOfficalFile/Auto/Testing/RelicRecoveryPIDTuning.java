package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.Testing;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcontroller.MyApplication;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia.PineappleRelicRecoveryVuforia;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfigV2;

/**
 * Created by ftcpi on 11/16/2017.
 */

public class RelicRecoveryPIDTuning extends RelicRecoveryConfigV2 {
    @Override
    public void runOpMode() throws InterruptedException {
        config(this);
        AnalogInput PPot = hardwareMap.analogInput.get("P");
        //AnalogInput IPot = hardwareMap.analogInput.get("I");
        AnalogInput DPot = hardwareMap.analogInput.get("D");
        double P = PPot.getVoltage();
//        double I = IPot.getVoltage();
        double I = 0;
        double D = DPot.getVoltage();
        while (!isStarted()) {
            telemetry.addData("P:", P);
            telemetry.addData("I:", I);
            telemetry.addData("D:", D);
            PPot.getVoltage();
            P = PPot.getVoltage();
            //I = IPot.getVoltage();
            D = DPot.getVoltage();
        }
        waitForStart();
        robotHandler.auto.gyroTurnPID(90, P, I, D, navx_device, MyApplication.getAppContext(), 10);

    }
}
