package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.R;

/**
 * Created by ftcpi on 9/16/2017.
 */
@Autonomous(name = "VuforiaTestJewel", group = "Linear Opmode")

public class VuforiaTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.vuforiaLicenseKey = "AdB8VB7/////AAAAGcfBp9I80URFkfBQFUyM+ptmQXBAMGx0svJKz7QE2nm20mBc/zI5sZNHfuP/ziIm+sYnO7fvPqUbFs8QWjRyXVEDmW4mMj+S+l+yaYRkpGZ/pmHyXiDb4aemHx0m70BulMNIce4+NVaCW5S/5BWNNev/AU0P+uWnHYuKNWzD2dPaRuprC4R6b/DgD1zeio1xlssYb9in9mfzn76gChOrE5B0ql6Q9FiHC5cTdacq2lKjm5nlkTiXz1e2jhVK3SddGoqM4FQ3mFks7/A88hFzlPfIIk45K2Lh7GvcVjuIiqNj5mTLaZJVqlsKdTQnKS4trJcc1YV9sjdbmh1agtn1UePy91fDj9uWSBdXvpIowv4B";
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;


        VuforiaLocalizer locale = ClassFactory.createVuforiaLocalizer(params);
        locale.setFrameQueueCapacity(1);
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        VuforiaTrackables relicTrackables;
        relicTrackables = locale.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackableDefaultListener track = (VuforiaTrackableDefaultListener)relicTrackables.get(0).getListener();
        relicTrackables.activate();
        waitForStart();
        PineappleEnum.JewelState state = PineappleRelicRecoveryVuforia.getJewelConfig(getImageFromFrame(locale.getFrameQueue().take(), PIXEL_FORMAT.RGB565),track, locale.getCameraCalibration());

        if (state == PineappleEnum.JewelState.NON_NON) {
            telemetry.addData("Thing ", "NON");
        } else if (state == PineappleEnum.JewelState.BLUE_RED) {
            telemetry.addData("Thing ", "BLUERED");
        } else if (state == PineappleEnum.JewelState.RED_BLUE) {
            telemetry.addData("Thing ", "REDBLUE");
        } else {
            telemetry.addData("Thing ", "UNDI");
        }

        telemetry.update();
        Thread.sleep(10000);
    }
    private Image getImageFromFrame(VuforiaLocalizer.CloseableFrame frame, int pixelFormat){
        long numbImgs = frame.getNumImages();
        for (int i=0;i<numbImgs;i++){
            if (frame.getImage(i).getFormat()==pixelFormat) {
                return frame.getImage(i);
            }
        }
        return null;
    }
}
