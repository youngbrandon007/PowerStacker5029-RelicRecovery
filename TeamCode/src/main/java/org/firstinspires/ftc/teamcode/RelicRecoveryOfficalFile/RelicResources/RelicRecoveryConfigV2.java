package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources;

import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleConfigLinearOpMode;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleServo;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Sensors.PineappleGyroSensor;

/**
 * Created by young on 9/14/2017.
 */

abstract public class RelicRecoveryConfigV2 extends PineappleConfigLinearOpMode {

    public PineappleMotor driveFrontRight;
    public PineappleMotor driveFrontLeft;
    public PineappleMotor driveBackRight;
    public PineappleMotor driveBackLeft;
    public PineappleMotor conveyRight;
    public PineappleMotor conveyLeft;

    public PineappleServo phoneTurnLeft;
    public PineappleServo jewelRotationLeft;
    public PineappleServo jewelLeverLeft;
    public PineappleServo collector;

    public final int NAVX_DIM_I2C_PORT = 0;
    public AHRS navx_device;
    public final byte NAVX_DEVICE_UPDATE_RATE_HZ = 50;
    public PineappleEnum.AllianceColor allianceColor = PineappleEnum.AllianceColor.BLUE;
//    public PineappleEnum.AllianceColor allianceColor = ((robotHandler.switchBoard.loadDigital("AC") == true) ? PineappleEnum.AllianceColor.RED : PineappleEnum.AllianceColor.BLUE);
    @Override
    public void config(LinearOpMode linearOpMode) {
        robotHandler = new PineappleRobot(linearOpMode);


        driveFrontRight = robotHandler.motorHandler.newDriveMotor("FR", 1, false, false, PineappleEnum.MotorLoc.RIGHTFRONT, PineappleEnum.MotorType.NEV40);
        driveFrontLeft = robotHandler.motorHandler.newDriveMotor("FL", 1, false, false, PineappleEnum.MotorLoc.LEFTFRONT, PineappleEnum.MotorType.NEV40);
        driveBackRight = robotHandler.motorHandler.newDriveMotor("BR", 1, false, false, PineappleEnum.MotorLoc.RIGHTBACK, PineappleEnum.MotorType.NEV40);
        driveBackLeft = robotHandler.motorHandler.newDriveMotor("BL", 1, false, false, PineappleEnum.MotorLoc.LEFTBACK, PineappleEnum.MotorType.NEV40);
        conveyLeft = robotHandler.motorHandler.newDriveMotor("CL", 1, false, false, PineappleEnum.MotorLoc.NONE, PineappleEnum.MotorType.NEV40);
        conveyRight = robotHandler.motorHandler.newDriveMotor("CR", 1, false, false, PineappleEnum.MotorLoc.NONE, PineappleEnum.MotorType.NEV40);

        phoneTurnLeft = robotHandler.servoHandler.newLimitServo("PTL", 1, 0.5);
        jewelRotationLeft = robotHandler.servoHandler.newLimitServo("JRL", 1, RelicRecoveryConstants.JEWELLEFTTURNLEFT);
        jewelLeverLeft = robotHandler.servoHandler.newLimitServo("JLL", 1, RelicRecoveryConstants.JEWELUP);
        collector = robotHandler.servoHandler.newContinuousServo("C", 0.5);

        navx_device = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("dim"),
                NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData,
                NAVX_DEVICE_UPDATE_RATE_HZ);

        boolean calibration_complete = false;
        while ( !calibration_complete ) {
            /* navX-Micro Calibration completes automatically ~15 seconds after it is
            powered on, as long as the device is still.  To handle the case where the
            navX-Micro has not been able to calibrate successfully, hold off using
            the navX-Micro Yaw value until calibration is complete.
             */
            calibration_complete = !navx_device.isCalibrating();
            if (!calibration_complete) {
                telemetry.addData("navX-Micro", "Startup Calibration in Progress");
                telemetry.update();
            }
        }
        navx_device.zeroYaw();
    }
    public void hitJewels(PineappleEnum.JewelState jewelState) throws InterruptedException {
        jewelLeverLeft.setPosition(RelicRecoveryConstants.JEWELDOWN);
        jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNMIDDLE);
        Thread.sleep(1000);
        switch (jewelState) {
            case BLUE_RED:
                if (allianceColor == PineappleEnum.AllianceColor.BLUE){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                } else {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
            case RED_BLUE:
                if (allianceColor == PineappleEnum.AllianceColor.RED){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                } else {
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
            case NON_BLUE:
                if (allianceColor == PineappleEnum.AllianceColor.RED){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                }
                break;
            case NON_RED:
                if (allianceColor == PineappleEnum.AllianceColor.BLUE){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNRIGHT);
                }
                break;
            case BLUE_NON:
                if (allianceColor == PineappleEnum.AllianceColor.RED){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
            case RED_NON:
                if (allianceColor == PineappleEnum.AllianceColor.BLUE){
                    jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
                }
                break;
        }
        Thread.sleep(1500);
        jewelLeverLeft.setPosition(RelicRecoveryConstants.JEWELUP);
        jewelRotationLeft.setPosition(RelicRecoveryConstants.JEWELLEFTTURNLEFT);
        Thread.sleep(3000);
    }

}
