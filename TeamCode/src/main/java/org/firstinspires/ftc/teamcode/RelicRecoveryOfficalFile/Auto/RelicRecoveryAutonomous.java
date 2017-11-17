package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.AutoBranches.BlankAuto;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.AutoBranches.Blue.BlueFront;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto.AutoBranches.Red.RedFront;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryConfig;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;

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
        RelicRecoveryEnums.AutoColor color;
        if(robotHandler.switchBoard.loadDigital("color")) {
            color = RelicRecoveryEnums.AutoColor.RED;
        }else{
            color = RelicRecoveryEnums.AutoColor.BLUE;
        }
        RelicRecoveryEnums.StartingPosition position;
        if(robotHandler.switchBoard.loadDigital("position")){
            position = RelicRecoveryEnums.StartingPosition.FRONT;
        }else{
            position = RelicRecoveryEnums.StartingPosition.BACK;
        }

        boolean moreGlyph = true;
        boolean gyroEnabled = true;
        boolean pidEnabled = false;
        boolean encoderEnabled = true;
        boolean jewelsEnabled = true;
        boolean vuforiaAlign = true;
        boolean colorAlign = false;

        RelicRecoveryAbstractAutonomous auto = new BlankAuto();
        switch (position) {
            case FRONT:
                switch (color) {
                    case RED:
                        auto = new RedFront();
                        break;
                    case BLUE:
                        auto = new BlueFront();
                        break;
                }
                break;
            case BACK:
                auto = new BlankAuto();
                break;
        }

        auto.AutoData(delay,moreGlyph,gyroEnabled,pidEnabled,encoderEnabled,jewelsEnabled,vuforiaAlign,colorAlign, this);

        auto.runOpMode();


    }
}
