package pineapplerobotics.preseason.Progress.PineappleRobotPackage.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleEnum;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleRobot;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleGyroSensor;
import pineapplerobotics.preseason.Progress.PineappleRobotPackage.PineappleSensors.PineappleOpticalDistanceSensor;

/**
 * Created by young on 8/7/2017.
 */
@TeleOp(name = "PineappleRobotGyroSensortest", group = "Linear Opmode")



public class GyroExample  extends LinearOpMode {
    PineappleRobot robot;

    PineappleGyroSensor gyro;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new PineappleRobot(this);

        gyro = robot.sensorHandler.newGyroSensor("g");

        robot.mapRobot();

        waitForStart();

        while(opModeIsActive()) {
            robot.sayFeedBack("Heading", gyro.getValue(PineappleEnum.PineappleSensorEnum.GSHEADING));
            robot.sayFeedBack("Status", gyro.GSStatus());

            robot.sayFeedBack("X", gyro.getValue(PineappleEnum.PineappleSensorEnum.GSX));
            robot.sayFeedBack("Y", gyro.getValue(PineappleEnum.PineappleSensorEnum.GSY));
            robot.sayFeedBack("Z", gyro.getValue(PineappleEnum.PineappleSensorEnum.GSZ));
            robot.updateFeedBack();
        }
    }
}
