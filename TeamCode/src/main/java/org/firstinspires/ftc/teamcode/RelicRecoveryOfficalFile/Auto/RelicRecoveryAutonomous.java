package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.AutomousBranches.BlankAuto;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.AutomousBranches.Blue.BlueFrontAuto;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.AutomousBranches.Red.RedFrontAuto;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicRecoveryConfig;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicRecoveryEnums;

/**
 * Created by young on 9/14/2017.
 */

@Autonomous(name = "RelicRecoveryAuto", group = "Linear Opmode")

public class RelicRecoveryAutonomous extends RelicRecoveryConfig {

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        //Load Switch Board


        double delay = 0;
        RelicRecoveryEnums.AutoColor color = RelicRecoveryEnums.AutoColor.BLUE;
        RelicRecoveryEnums.StartingPosition position = RelicRecoveryEnums.StartingPosition.FRONT;
        boolean moreGlyph = true;
        boolean gyroEnabled = true;
        boolean pidEnabled = false;
        boolean encoderEnabled = true;
        boolean jewelsEnabled = true;
        boolean vuforiaAlign = true;
        boolean colorAlign = false;

        RelicRecoveryAbstractAutonomous auto;
        switch (position) {
            case FRONT:
                switch (color) {
                    case RED:
                        auto = new RedFrontAuto();
                        break;
                    case BLUE:
                        auto = new BlueFrontAuto();
                        break;
                    default:
                        auto = new BlankAuto();
                }
                break;
            case BACK:
                auto = new BlankAuto();
                break;
            default:
                auto = new BlankAuto();
        }

        auto.AutoData(delay,moreGlyph,gyroEnabled,pidEnabled,encoderEnabled,jewelsEnabled,vuforiaAlign,colorAlign, this);

        auto.runOpMode();


//         /*while (opModeIsActive()) {
////            switch(vuforia.getJewelState()){
////
////                case BLUE_RED:
////                    telemetry.addData("Order", "BLUE_RED");
////                    break;
////                case RED_BLUE:
////                    telemetry.addData("Order", "RED_BLUE");
////                    break;
////                case NON_NON:
////                    telemetry.addData("Order", "NON_NON");
////                    break;
////                default:
////                    telemetry.addData("Order", "adfadfa");
////                    break;
////            }
//             if (vuforia.getJewelState() == PineappleEnum.JewelState.NON_NON) {
//                 telemetry.addData("Thing ", "NON");
//             }
//             if (vuforia.getJewelState() == PineappleEnum.JewelState.BLUE_RED) {
//                 telemetry.addData("Thing ", "BLUERED");
//             }
//             if (vuforia.getJewelState() == PineappleEnum.JewelState.RED_BLUE) {
//                 telemetry.addData("Thing ", "REDBLUE");
//             }
//             telemetry.update();
//
//        }*/
    }
}
