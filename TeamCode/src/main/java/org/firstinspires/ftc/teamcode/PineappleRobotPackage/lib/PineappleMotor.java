package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Brandon on 6/19/2017.
 */

public class PineappleMotor {

    //Motor Properties

    public PineappleEnum.MotorLoc motorLoc = PineappleEnum.MotorLoc.NONE;

    public double maxPower = 1;
    public double minPower = -1;

    public double defaultPower = 0;
    public double cpr;
    public double scaleBy = 1;
    public PineappleEnum.MotorType motorType = PineappleEnum.MotorType.UNDI;

    public boolean exponetional = false;

    public boolean doDeadArea = false;

    //Dead Area Array

    private final double[] deadAreaArray = {0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.8, 0.9, 1.0};

    //Motor Object

    public DcMotor motorObject;
    public String motorName;


    //Resources

    private PineappleResources resources;


    //Constructor

    public PineappleMotor(PineappleResources res, String name, double powerMin, double powerMax, double powerDefault, double scale, boolean exp, boolean deadArea, PineappleEnum.MotorLoc loc, PineappleEnum.MotorType type) {
        resources = res;
        motorLoc = loc;
        maxPower = powerMax;
        minPower = powerMin;
        defaultPower = powerDefault;
        scaleBy = scale;
        exponetional = exp;
        doDeadArea = deadArea;
        motorName = name;
        motorType = type;
        cpr = motorTypeToCPR(type);
        mapMotor();
    }

    public void mapMotor() {
        motorObject = resources.hardwareMap.dcMotor.get(motorName);
        setupEncoder();
    }

    ///////////////////////////
    //Drive Encoder Functions//
    ///////////////////////////

    public double motorTypeToCPR(PineappleEnum.MotorType type){
        switch (type) {
            case NEV60:
                return PineappleRobotConstants.NEV60CPR;
            case NEV40:
                return PineappleRobotConstants.NEV40CPR;
            case NEV20:
                return PineappleRobotConstants.NEV20CPR;
            case NEV3_7:
                return PineappleRobotConstants.NEV3_7CPR;
            case UNDI:
                return PineappleRobotConstants.TETRIXCPR;
            default:
                return 0;
        }
    }

    public void setupEncoder(){
        motorObject.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        resources.linearOpMode.idle();

        motorObject.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void encoderDrive(double speed, double value, PineappleEnum.MotorValueType motorValueType, double wheelSize) {
        if (motorValueType == PineappleEnum.MotorValueType.COUNTS) {
            encoderDriveCounts(speed, (int) value);
        } else {
            encoderDriveDist(speed, value, wheelSize, motorValueType);

        }
    }
    private void encoderDriveCounts(double speed, int counts){
        int target;
        if(resources.linearOpMode.opModeIsActive()){
            if (isPositive(speed) != isPositive(counts)){counts=-counts;}

            target = motorObject.getCurrentPosition() + counts;

            motorObject.setTargetPosition(target);
            motorObject.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motorObject.setPower(speed);

            while (resources.linearOpMode.opModeIsActive() && motorObject.isBusy()){
                resources.feedBack.sayFeedBack(motorName + " encoder", motorObject.getCurrentPosition());
            }

            motorObject.setPower(0);

            motorObject.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
    public int distToCounts(double value, PineappleEnum.MotorValueType motorValueType, double wheelSize, PineappleEnum.MotorType motorType){
        switch (motorValueType) {
            case INCH:
                return (int)(cpr*(value/(PineappleRobotConstants.PI*wheelSize)));
            case DEGREES:
                return (int)(cpr*(value/360));
            case CM:
                return (int)(cpr*((value*PineappleRobotConstants.CMTOINCH)/(PineappleRobotConstants.PI*wheelSize)));
            case RADIANS:
                return (int)(cpr*(value/(2*PineappleRobotConstants.PI)));
            default:
                return 0;
        }
    }
    private boolean isPositive(double value){
        if(value>=0){
            return true;
        } else {
            return false;
        }
    }
    private void encoderDriveDist(double speed, double inches, double wheelSize, PineappleEnum.MotorValueType motorValueType ){
        int target;
        int counts = distToCounts(inches, motorValueType,wheelSize, motorType);
        if(resources.linearOpMode.opModeIsActive()){
            if (isPositive(speed) != isPositive(counts)){counts=-counts;}
            target = motorObject.getCurrentPosition() + counts;

            motorObject.setTargetPosition(target);
            motorObject.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motorObject.setPower(speed);

            while (resources.linearOpMode.opModeIsActive() && motorObject.isBusy()){
                resources.feedBack.sayFeedBack(motorName + " encoder", motorObject.getCurrentPosition());
            }

            motorObject.setPower(0);

            motorObject.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void encoderStart(double speed, int counts){
        int target;
        if(resources.linearOpMode.opModeIsActive()){
            if (isPositive(speed) != isPositive(counts)){counts=-counts;}
            target = motorObject.getCurrentPosition() + counts;

            motorObject.setTargetPosition(target);
            motorObject.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motorObject.setPower(speed);
        }
    }

    public void encoderStop(){
        motorObject.setPower(0);
        motorObject.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public boolean encodersBusy(){
        return motorObject.isBusy();
    }

    ///////////////////////
    //Set Power Functions//
    ///////////////////////

    double setPower(double power) {
        power = fixValue(power);
        resources.feedBack.sayFeedBack(motorName, power);
        motorObject.setPower(power);
        return power;
    }

    void setDirectPower(double power){
        motorObject.setPower(clip(power));
    }

    public double update(double power) {
        return setPower(power);
    }

    public double update(boolean on) {
        if (on) return setPower(maxPower);
        else return setPower(defaultPower);
    }

    public double update(boolean forward, boolean backward) {
        if (forward) return setPower(maxPower);
        else if (backward) return setPower(minPower);
        else return setPower(defaultPower);
    }

    /////////////////////
    //Private Functions//
    /////////////////////

    private double fixValue(double input) {
        input = scale(input);
        input = deadArea(input);
        input = clip(input);
        return input;
    }

    private double deadArea(double input) {
        if (doDeadArea) {
            boolean pos = true;
            if (input < 0) {
                pos = false;
            }
            input = Math.abs(input);

            double last = 10000;
            double now = 0;
            double output = 0;

            for (int i = 0; i < deadAreaArray.length; i++) {
                now = deadAreaArray[i] - input;
                now = Math.abs(now);
                if (now < last) {
                    last = now;
                    output = deadAreaArray[i];
                }
            }

            if (pos == false) {
                output = -output;
            }

            return output;
        } else {
            return input;
        }
    }

    private double scale(double input) {
        input = input * scaleBy;
        if (exponetional) input = input * input;
        return input;
    }

    private double clip(double input) {
        input = Range.clip(input, minPower, maxPower);
        input = Range.clip(input, -1, 1);
        return input;
    }

}
