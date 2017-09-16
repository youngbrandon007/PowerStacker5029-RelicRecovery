package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.vuforia.CameraCalibration;
import com.vuforia.Image;
import com.vuforia.Matrix34F;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Tool;
import com.vuforia.Vec2F;
import com.vuforia.Vec3F;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleResources;

import java.util.Arrays;

import static org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobotConstants.blueHigh;
import static org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobotConstants.blueLow;

/**
 * Created by young on 9/13/2017.
 */

public class PineappleRelicRecoveryVuforia extends PineappleVuforia {

    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    public PineappleRelicRecoveryVuforia(PineappleResources r, int maxItemCount, VuforiaLocalizer.CameraDirection direction, VuforiaLocalizer.Parameters.CameraMonitorFeedback feedback, String vuforiaLicenseKey) {
        super(r, maxItemCount, direction, feedback, vuforiaLicenseKey);

    }

    public void addRelicRecoveryTrackables() {
        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); //
    }

    public void startRelicRecoveryTracking() {
        relicTrackables.activate();
    }

    public PineappleEnum.VuMarkLocation getTrackingRelic() {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

        PineappleEnum.VuMarkLocation loc = PineappleEnum.VuMarkLocation.UNKNOWN;

        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this Condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
            resources.telemetry.addData("VuMark", "%s visible", vuMark);

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
            String strPose = format(pose);
            resources.feedBack.sayFeedBackWithOutUpdate("Pose", strPose);

            if (strPose.toLowerCase().equals("center")) {
                loc = PineappleEnum.VuMarkLocation.CENTER;
            }
            if (strPose.toLowerCase().equals("left")) {
                loc = PineappleEnum.VuMarkLocation.LEFT;
            }
            if (strPose.toLowerCase().equals("right")) {
                loc = PineappleEnum.VuMarkLocation.RIGHT;
            }
                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
            if (pose != null) {
                VectorF trans = pose.getTranslation();
                Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                // Extract the X, Y, and Z components of the offset of the target relative to the robot
                double tX = trans.get(0);
                double tY = trans.get(1);
                double tZ = trans.get(2);

                // Extract the rotational components of the target relative to the robot
                double rX = rot.firstAngle;
                double rY = rot.secondAngle;
                double rZ = rot.thirdAngle;
            }
        } else {
            resources.feedBack.sayFeedBackWithOutUpdate("VuMark", "not visible");
        }

        resources.feedBack.update();
        return loc;
    }

    public PineappleEnum.JewelState getJewelConfig() throws InterruptedException {
        return getJewelState(getImageFromFrame(vuforia.getFrameQueue().take(), PIXEL_FORMAT.RGB565), vuforia.getCameraCalibration());
    }
    private PineappleEnum.JewelState getJewelState(Image img, CameraCalibration camCal) {
        OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getRawPose();
        if (pose !=null && img != null&&img.getPixels() !=null) {
            Matrix34F rawPose = new Matrix34F() ;
            float[] poseData = Arrays.copyOfRange(pose.transposed().getData(), 0, 12);
            rawPose.setData(poseData);
            float[][] corners = new float[4][2];
            corners[0] = Tool.projectPoint(camCal, rawPose, new Vec3F(92,-22,0)).getData();//TL
            corners[1] = Tool.projectPoint(camCal, rawPose, new Vec3F(340,-22,0)).getData();//TR
            corners[2] = Tool.projectPoint(camCal, rawPose, new Vec3F(340,-118,0)).getData();//BR
            corners[3] = Tool.projectPoint(camCal, rawPose, new Vec3F(92,-118,0)).getData();//BL
            Bitmap bm = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565)
            bm.copyPixelsFromBuffer(img.getPixels());
            Mat crop = new Mat(bm.getHeight(), bm.getWidth(), CvType.CV_8UC3);
            Utils.bitmapToMat(bm, crop);
            float x = Math.min(Math.min(corners[1][0], corners[3][0]), Math.min(corners[0][0], corners[2][0]));
            float y = Math.min(Math.min(corners[1][1], corners[3][1]), Math.min(corners[0][1], corners[2][1]));
            float width = Math.max(Math.abs(corners[0][0]-corners[2][0]), Math.abs(corners[1][0]-corners[3][0]));
            float height = Math.max(Math.abs(corners[0][1]-corners[2][1]), Math.abs(corners[1][1]-corners[3][1]));
            x = Math.max(x, 0);
            y = Math.max(y, 0);
            width = (x+width>crop.cols())?crop.cols() - x:width;
            height = (x+height>crop.rows())?crop.rows() - x:height;
            Mat cropped = new Mat(crop, new Rect((int) x, (int) y, (int) width, (int) height));
            Imgproc.cvtColor(cropped, cropped, Imgproc.COLOR_RGB2HSV_FULL);
            Mat mask = new Mat();
            Core.inRange(cropped, blueLow, blueHigh, mask);
            Moments mmnts = Imgproc.moments(mask, true);
            if ((mmnts.get_m01()/mmnts.get_m00())< cropped.rows()/2) {
                return PineappleEnum.JewelState.RED_BLUE;
            } else {
                return PineappleEnum.JewelState.BLUE_RED;
            }

        }
        return null;
    }
    public Image getImageFromFrame(VuforiaLocalizer.CloseableFrame frame, int pixelFormat){
        long numbImgs = frame.getNumImages();
        for (int i=0;i<numbImgs;i++){
            if (frame.getImage(i).getFormat()==pixelFormat) {
                return frame.getImage(i);
            }
        }
        return null;
    }
}
