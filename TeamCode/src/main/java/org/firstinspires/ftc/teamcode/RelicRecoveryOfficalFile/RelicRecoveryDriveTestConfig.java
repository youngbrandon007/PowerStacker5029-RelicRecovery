package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleConfigLinearOpMode;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobotConstants;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleGyroSensor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleServo;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia.PineappleRelicRecoveryVuforia;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Created by young on 9/14/2017.
 */

abstract public class RelicRecoveryDriveTestConfig extends PineappleConfigLinearOpMode {

    public PineappleMotor driveFrontRight;
    public PineappleMotor driveFrontLeft;
    public PineappleMotor driveBackRight;
    public PineappleMotor driveBackLeft;



    @Override
    public void config(LinearOpMode linearOpMode) {
        robotHandler = new PineappleRobot(linearOpMode);

        driveFrontRight = robotHandler.motorHandler.newDriveMotor("FR", 1, true, true, PineappleEnum.MotorLoc.RIGHTFRONT, PineappleEnum.MotorType.NEV40);
        driveFrontLeft = robotHandler.motorHandler.newDriveMotor("FL", 1, true, true, PineappleEnum.MotorLoc.LEFTFRONT, PineappleEnum.MotorType.NEV40);
        driveBackRight = robotHandler.motorHandler.newDriveMotor("BR", 1, true, true, PineappleEnum.MotorLoc.RIGHTBACK, PineappleEnum.MotorType.NEV40);
        driveBackLeft = robotHandler.motorHandler.newDriveMotor("BL", 1, true, true, PineappleEnum.MotorLoc.LEFTBACK, PineappleEnum.MotorType.NEV40);
        driveBackLeft.motorObject.setDirection(DcMotorSimple.Direction.REVERSE);
        driveFrontLeft.motorObject.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    public void setMovement(double angle, double speed, double rotation, double scale) {
        // Shift angle by 45 degrees, since our drive train is x-shaped and not cross-shaped
        angle += PI / 4;

        // Cut rotation in half because we don't want to spin THAT fast
        rotation *= 0.5;

        // Normalize magnitudes so that "straight forward" has a magnitude of 1
        speed *= sqrt(2);

        double sinDir = sin(angle);
        double cosDir = cos(angle);

        // None of this stuff should happen if the speed is 0.
        if (speed == 0.0 && rotation == 0.0) {
            stopMovement();
            return;
        }

        // Rotation is scaled down by 50% so that it doesn't completely cancel out any motors
        double multipliers[] = new double[4];
        multipliers[0] = (speed * sinDir) + rotation;
        multipliers[1] = (speed * cosDir) + rotation;
        multipliers[2] = (speed * -cosDir) + rotation;
        multipliers[3] = (speed * -sinDir) + rotation;

        double largest = abs(multipliers[0]);
        for (int i = 1; i < 4; i++) {
            if (abs(multipliers[i]) > largest)
                largest = abs(multipliers[i]);
        }

        // Only normalize multipliers if largest exceeds 1.0
        if (largest > 1.0) {
            for (int i = 0; i < 4; i++) {
                multipliers[i] = multipliers[i] / largest;
            }
        }

        // Scale if needed, 0.0 < scale < 1.0;
//        for (int i = 0; i < 4; i++) {
//            multipliers[i] = multipliers[i] * scale;
//        }

        // TODO Fix wiring. Motors 2 and 4 are plugged into the wrong motor ports.
        driveFrontLeft.update(multipliers[0] * scale);
        driveBackRight.update(multipliers[1] * scale);
        driveBackLeft.update(multipliers[2] * scale);
        driveFrontRight.update(multipliers[3] * scale);
    }
    public void stopMovement() {
        driveFrontLeft.update(0);
        driveBackRight.update(0);
        driveBackLeft.update(0);
        driveFrontRight.update(0);
    }
    public static double mecDirectionFromJoystick(Gamepad pad) {
        return Math.atan2(-pad.left_stick_y, pad.left_stick_x);
    }

    /**
     * Get the translation speed value from the joystick. If the joysticks are moved close enough
     * to the center, the method will return 0 (meaning no movement).
     *
     * @param pad Gamepad to take control values from.
     * @return Speed ranging from 0:1
     */
    public static double mecSpeedFromJoystick(Gamepad pad) {
        // If the joystick is close enough to the middle, return a 0 (no movement)
        if (abs(pad.left_stick_x) < PineappleRobotConstants.MINIMUM_JOYSTICK_THRESHOLD
                && abs(pad.left_stick_y) < PineappleRobotConstants.MINIMUM_JOYSTICK_THRESHOLD) {
            return 0.0;
        } else {
            return sqrt((pad.left_stick_y * pad.left_stick_y)
                    + (pad.left_stick_x * pad.left_stick_x));
        }
    }

    /**
     * Get the spin speed value from the joystick. If the joystick is moved close enough to the
     * center, the method will return 0 (meaning no spin).
     *
     * @param pad Gamepad to take control values from.
     * @return Speed ranging from -1:1
     */
    public static double mecSpinFromJoystick(Gamepad pad) {
        return (abs(pad.right_stick_x) > PineappleRobotConstants.MINIMUM_JOYSTICK_THRESHOLD)
                ? pad.right_stick_x : 0.0;
    }
}
