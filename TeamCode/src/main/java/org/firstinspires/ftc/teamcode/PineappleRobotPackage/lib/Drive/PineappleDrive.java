package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.PineappleSettings;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleResources;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobotConstants;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleGyroSensor;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.round;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Created by ftcpi on 6/29/2017.
 */

public class PineappleDrive extends PineappleDriveAbstract{

    public PineappleTankDrive tank;
    public PineappleMecanumDrive mecanum;

    public PineappleDrive(PineappleResources res) {
        super(res);
        resources = res;
        tank = new PineappleTankDrive(resources);
        mecanum = new PineappleMecanumDrive(resources);
    }

}