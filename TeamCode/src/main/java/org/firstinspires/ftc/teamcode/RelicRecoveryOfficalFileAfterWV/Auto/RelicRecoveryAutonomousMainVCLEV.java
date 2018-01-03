package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.Auto;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.RelicRecoveryResources.RelicRecoveryConfigV2Cleve;

/**
 * Created by ftcpi on 1/3/2018.
 */

public class RelicRecoveryAutonomousMainVCLEV extends RelicRecoveryConfigV2Cleve{

    enum Auto{
        JEWELDOWN, JEWELTURN, JEWELUP, DRIVEOFFPLAT, PDRIVEFORWARD, DRIVEFORWARD, TURNTOCRYPTO, DRIVEFORWARDTOCRYPTO, ALIGNTOCRYPTO;
    }

    Auto auto = Auto.JEWELDOWN;

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        telemetry.addLine("Wait For Start");
        telemetry.update();
        waitForStart();

        auto = Auto.PDRIVEFORWARD;

        double startingPos = 0
        while (opModeIsActive()){
            switch (auto){
                case JEWELDOWN:
                    break;
                case JEWELTURN:
                    break;
                case JEWELUP:
                    break;
                case DRIVEOFFPLAT:
                    break;
                case PDRIVEFORWARD:
                    startingPos = driveFrontRight.getEncoderPosition();
                    robotHandler.drive.mecanum.setPower(.5,-.5);
                    break;
                case DRIVEFORWARD:
                    if(driveFrontRight.getEncoderPosition() > 100)
                    break;
                case TURNTOCRYPTO:
                    break;
                case DRIVEFORWARDTOCRYPTO:
                    break;
                case ALIGNTOCRYPTO:
                    break;
            }
        }
    }
}
