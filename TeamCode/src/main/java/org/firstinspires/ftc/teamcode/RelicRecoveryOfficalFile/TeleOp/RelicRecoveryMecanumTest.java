package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfig;

/**
 * Created by Brandon on 10/10/2017.
 */
@Autonomous(name = "RRmecanumTest", group = "Linear Opmode")

public class RelicRecoveryMecanumTest extends RelicRecoveryConfig {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        waitForStart();
        runtime.reset();

//        robotHandler.drive.encoderMecanum(90,0.5, "3ft", 4, PineappleEnum.MotorType.NEV40, gyroSensor);




    }
}