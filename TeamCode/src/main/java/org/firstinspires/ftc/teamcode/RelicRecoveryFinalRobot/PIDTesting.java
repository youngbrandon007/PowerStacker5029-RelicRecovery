package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.AnalogInput;

import java.text.DecimalFormat;

/**
 * Created by ftcpi on 2/7/2018.
 */
@Autonomous(name = "PID")
public class PIDTesting extends Config{
    @Override
    public void runOpMode() throws InterruptedException {

        DecimalFormat decimalFormat = new DecimalFormat("#.########");
        config(this);
        AnalogInput PPot = hardwareMap.analogInput.get("P");
        AnalogInput IPot = hardwareMap.analogInput.get("I");
        AnalogInput DPot = hardwareMap.analogInput.get("D");

        double P = Double.parseDouble(decimalFormat.format(PPot.getVoltage()/20));
        double I = Double.parseDouble(decimalFormat.format(IPot.getVoltage()/50));

        double D = Double.parseDouble(decimalFormat.format(DPot.getVoltage()/50));
        while (!opModeIsActive()) {
            telemetry.addData("P:", P);
            telemetry.addData("I:", I*1000);
            telemetry.addData("D:", D);
            PPot.getVoltage();
//            P = Double.parseDouble(decimalFormat.format(PPot.getVoltage()/20));
            P = Constants.PID.P;
//            I = Double.parseDouble(decimalFormat.format(IPot.getVoltage()/100));
//            D = Double.parseDouble(decimalFormat.format(DPot.getVoltage()/20));
            I = Constants.PID.I;
            D = Constants.PID.D;
            telemetry.update();
        }
        telemetry.addData("PID", "Tuning Finished Press play");
        telemetry.update();
        waitForStart();
        robotHandler.auto.gyroTurnPID(90, P, I, D, navx_device);

    }
}
