package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.Auto;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.RelicRecoveryResources.RelicRecoveryConfigV3;

/**
 * Created by Brandon on 12/5/2017.
 */

public class RelicRecoveryAutonomousMain extends RelicRecoveryConfigV3 {

    public double delay = 0;
    public RelicRecoveryEnums.AutoColor color = RelicRecoveryEnums.AutoColor.BLUE;
    public RelicRecoveryEnums.StartingPosition position = RelicRecoveryEnums.StartingPosition.FRONT;
    public boolean moreGlyph = false;
    public boolean glyphsEnabled = true;
    public boolean delayEnabled = true;
    public boolean pidEnabled = false;
    public boolean jewelsEnabled = true;
    private boolean usingGyro = false;

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);
        //load everything
        loadSwitchBoard();


        telemetry.update();
        //wait for start
        waitForStart();

        while (opModeIsActive()){
            
        }

    }

    private void loadSwitchBoard() {
        delay = robotHandler.switchBoard.loadAnalog("delay") * 2;
        color = (robotHandler.switchBoard.loadDigital("color")) ? RelicRecoveryEnums.AutoColor.BLUE : RelicRecoveryEnums.AutoColor.RED;
        position = (robotHandler.switchBoard.loadDigital("position")) ? RelicRecoveryEnums.StartingPosition.FRONT : RelicRecoveryEnums.StartingPosition.BACK;
        jewelsEnabled = robotHandler.switchBoard.loadDigital("jewel");
        pidEnabled = robotHandler.switchBoard.loadDigital("pid");
        glyphsEnabled = robotHandler.switchBoard.loadDigital("glyph");
        delayEnabled = robotHandler.switchBoard.loadDigital("delayEnabled");
        delay = Math.round(delay * 2) / 2.0;
        telemetry.addData("Delay", delay);
        telemetry.addData("DelayEnabled", delayEnabled);
        telemetry.addData("Color", color);
        telemetry.addData("Jewel", jewelsEnabled);
        telemetry.addData("PID", pidEnabled);
        telemetry.addData("Glyphs", glyphsEnabled);
        telemetry.addData("Position", position);
    }

}
