package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.Testing;

import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

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

        waitForStart();
        robotHandler.auto.gyroTurnPID(90,3, 0, 0, navx_device);

    }
}
