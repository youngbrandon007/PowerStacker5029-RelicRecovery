package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.Auto;

import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicRecoveryConfig;

/**
 * Created by young on 9/15/2017.
 */

abstract public class RelicRecoveryAbstractAutonomous extends RelicRecoveryConfig {
    public double time;
    public boolean moreGlyph = true;
    public boolean gyroEnabled = true;
    public  boolean pidEnabled = false;
    public boolean encoderEnabled = true;
    public boolean jewelsEnabled = true;
    public boolean vuforiaAlign = true;
    public boolean colorAlign = false;

    public void AutoData(double time, boolean moreGlyph, boolean gyroEnabled,boolean pidEnabled,boolean encoderEnabled,boolean jewelsEnabled,boolean vuforiaAlign,boolean colorAlign){
        this.time = time;
        this.moreGlyph = moreGlyph;
        this.gyroEnabled = gyroEnabled;
        this.pidEnabled = pidEnabled;
        this.encoderEnabled = encoderEnabled;
        this.jewelsEnabled = jewelsEnabled;
        this.vuforiaAlign = vuforiaAlign;
        this.colorAlign = colorAlign;
    }
}
