package org.firstinspires.ftc.teamcode.Humanoid;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleConfigLinearOpMode;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;

/**
 * Created by Brandon on 8/26/2017.
 */

public class HConfigLinearOpMode extends PineappleConfigLinearOpMode {

    PineappleRobot robot;

    PineappleMotor LegRightKnee;
    PineappleMotor LegLeftKnee;

    PineappleMotor LegRightWaist;
    PineappleMotor LegLeftWaist;

    Servo LegRightFoot;
    Servo LegLeftFoot;

    final double speed = .1;

    @Override
    public void config(LinearOpMode linearOpMode) {
        robot = new PineappleRobot(linearOpMode);
        LegRightKnee = robot.motorHandler.newMotor("LRK", PineappleEnum.MotorType.NEV40);
        LegLeftKnee = robot.motorHandler.newMotor("LLK", PineappleEnum.MotorType.NEV40);

        LegRightWaist = robot.motorHandler.newMotor("LRW", PineappleEnum.MotorType.NEV40);
        LegLeftWaist = robot.motorHandler.newMotor("LLW", PineappleEnum.MotorType.NEV40);

        LegRightFoot = linearOpMode.hardwareMap.servo.get("LRT");
        LegLeftFoot = linearOpMode.hardwareMap.servo.get("LLT");
    }

    public void setLegs(int RW,double SRW,int RK,double SRK, int LW ,double SLW, int LK,double SLK){
        LegRightWaist.encoderStart(SRW, RW);
        LegRightKnee.encoderStart(SRK,RK);
        LegLeftWaist.encoderStart(SLW,LW);
        LegLeftKnee.encoderStart(SLK,LK);

        while(LegLeftWaist.encodersBusy() || LegLeftKnee.encodersBusy() || LegRightWaist.encodersBusy() || LegRightKnee.encodersBusy()){

        }

        LegRightWaist.encoderStop();
        LegRightKnee.encoderStop();
        LegLeftWaist.encoderStop();
        LegLeftKnee.encoderStop();
    }
}
