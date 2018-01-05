package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.RelicRecoveryAbstractAutonomous;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.RelicRecoveryResources.RelicRecoveryConfigV2Cleve;

/**
 * Created by ftcpi on 11/27/2017.
 */
@Autonomous(name = "SwitchBoardSettingCleve")
public class RelicRecoverySwitchBoardSettingCleve extends RelicRecoveryConfigV2Cleve {
    @Override
    public void runOpMode() throws InterruptedException {
        config(this);
        waitForStart();
        loadSwitchBoardLoop();
    }
}
