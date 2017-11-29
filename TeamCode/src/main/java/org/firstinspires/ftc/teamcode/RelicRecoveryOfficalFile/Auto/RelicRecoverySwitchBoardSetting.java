package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by ftcpi on 11/27/2017.
 */
@Autonomous(name = "SwitchBoardSetting")
public class RelicRecoverySwitchBoardSetting extends RelicRecoveryAbstractAutonomous {
    @Override
    public void runOpMode() throws InterruptedException {
        config(this);
        waitForStart();
        loadSwitchBoardLoop();
    }
}
