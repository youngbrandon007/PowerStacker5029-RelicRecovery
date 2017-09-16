package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.AutomousBranches.Blue;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.RelicRecoveryAbstractAutonomous;

/**
 * Created by young on 9/15/2017.
 */

public class BlueFrontAuto extends RelicRecoveryAbstractAutonomous{

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        while (opModeIsActive()) {
            switch(vuforia.getJewelState()){

                case BLUE_RED:
                    telemetry.addData("Order", "BLUE_RED");
                    break;
                case RED_BLUE:
                    telemetry.addData("Order", "BLUE_RED");
                    break;
                default:
                    telemetry.addData("Order", "BLUE_RED");
                    break;
            }

            telemetry.update();
        }
    }
}
