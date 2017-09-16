package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.AutomousBranches.BlankAuto;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.AutomousBranches.BlueAuto;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.AutomousBranches.RedAuto;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicRecoveryConfig;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicRecoveryEnums;

/**
 * Created by young on 9/14/2017.
 */

public class RelicRecoveryAutonomous extends RelicRecoveryConfig {

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        //Load Switch Board





        RelicRecoveryEnums.AutoColor color = RelicRecoveryEnums.AutoColor.BLUE;

        RelicRecoveryAbstractAutonomous auto;

        switch (color) {
            case RED:
                auto = new RedAuto();
                break;
            case BLUE:
                auto = new BlueAuto();
                break;
            default:
                auto = new BlankAuto();
        }

        auto.runOpMode();
    }
}
