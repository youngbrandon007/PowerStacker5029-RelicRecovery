package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.CameraCalibration;
import com.vuforia.Image;
import com.vuforia.Matrix34F;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Tool;
import com.vuforia.Vec3F;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.R;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.Arrays;

import static org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum.JewelState.BLUE_RED;
import static org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum.JewelState.RED_BLUE;


/**
 * Created by ftcpi on 9/16/2017.
 */
@Autonomous(name = "VuforiaTestJewel", group = "Linear Opmode")

public class VuforiaTest extends LinearOpMode {
    float[] blueLowHSV = {240,90,34};
    float[] blueHighHSV= {186,33,95};
    public final static Scalar blueLow = new Scalar(stringArrtoIntArr(hsvToRgb(blueLowHSV))[0], stringArrtoIntArr(hsvToRgb(blueLowHSV))[1] , stringArrtoIntArr(hsvToRgb(blueLowHSV))[2]);
    public final static Scalar blueHigh = new Scalar(stringArrtoIntArr(hsvToRgb(blueHighHSV))[0], stringArrtoIntArr(hsvToRgb(blueHighHSV))[1] , stringArrtoIntArr(hsvToRgb(blueHighHSV))[2]);
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
        VuforiaTrackableDefaultListener track = (VuforiaTrackableDefaultListener) relicTrackables.get(0).getListener();
        relicTrackables.activate();
        waitForStart();
//        PineappleEnum.JewelState state = PineappleRelicRecoveryVuforia.getJewelConfig(getImageFromFrame(locale.getFrameQueue().take(), PIXEL_FORMAT.RGB565),track, locale.getCameraCalibration());
        Image img = getImageFromFrame(locale.getFrameQueue().take(), PIXEL_FORMAT.RGB565);

        OpenGLMatrix pose = track.getRawPose();
        Matrix34F rawPose = new Matrix34F();

        float[] poseData = Arrays.copyOfRange(pose.transposed().getData(), 0, 12);
        rawPose.setData(poseData);

        Bitmap bm = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);
        bm.copyPixelsFromBuffer(img.getPixels());
        PineappleRelicRecoveryVuforia.SaveImage(bm, "orginialImage ");
        Mat crop = bitmapToMat(bm, CvType.CV_8UC3);

        float[][] corners = new float[4][2];
        CameraCalibration camCal = locale.getCameraCalibration();
        corners[0] = Tool.projectPoint(camCal, rawPose, new Vec3F(92, -35, 0)).getData();//UL
        corners[1] = Tool.projectPoint(camCal, rawPose, new Vec3F(340, -35, 0)).getData();//UR
        corners[2] = Tool.projectPoint(camCal, rawPose, new Vec3F(340, -118, 0)).getData();//LR
        corners[3] = Tool.projectPoint(camCal, rawPose, new Vec3F(92, -118, 0)).getData();//LL
        float x = Math.min(Math.min(corners[1][0], corners[3][0]), Math.min(corners[0][0], corners[2][0]));
        float y = Math.min(Math.min(corners[1][1], corners[3][1]), Math.min(corners[0][1], corners[2][1]));
        float width = Math.max(Math.abs(corners[0][0] - corners[2][0]), Math.abs(corners[1][0] - corners[3][0]));
        float height = Math.max(Math.abs(corners[0][1] - corners[2][1]), Math.abs(corners[1][1] - corners[3][1]));


        //make sure our bounding box doesn't go outside of the image
        //OpenCV doesn't like that...
        x = Math.max(x, 0);
        y = Math.max(y, 0);
        width = (x + width > crop.cols()) ? crop.cols() - x : width;
        height = (y + height > crop.rows()) ? crop.rows() - y : height;

        //cropping bounding box out of camera image
        final Mat cropped = new Mat(crop, new Rect((int) x, (int) y, (int) width, (int) height));
        PineappleRelicRecoveryVuforia.SaveImage(matToBitmap(cropped), "crop");
        Imgproc.cvtColor(cropped, cropped, Imgproc.COLOR_RGB2HSV_FULL);
        Mat mask = new Mat();
        Core.inRange(cropped, blueLow, blueHigh, mask);
        PineappleRelicRecoveryVuforia.SaveImage(matToBitmap(mask), "mask");
        Moments mmnts = Imgproc.moments(mask, true);
        Log.i("CentroidX", "" + ((mmnts.get_m10() / mmnts.get_m00())));
        Log.i("CentroidY", "" + ((mmnts.get_m01() / mmnts.get_m00())));
        if ((mmnts.get_m01()/mmnts.get_m00())< cropped.rows()/2) {
            telemetry.addData("Order", "RED BLUE");
        } else {
            telemetry.addData("Order", "BLUE RED");
        }
//        if (state == PineappleEnum.JewelState.NON_NON) {
//            telemetry.addData("Thing ", "NON");
//        } else if (state == PineappleEnum.JewelState.BLUE_RED) {
//            telemetry.addData("Thing ", "BLUERED");
//        } else if (state == PineappleEnum.JewelState.RED_BLUE) {
//            telemetry.addData("Thing ", "REDBLUE");
//        } else {
//            telemetry.addData("Thing ", "UNDI");
//        }
        telemetry.update();
        Thread.sleep(10000);

    }

    @Nullable
    public static Image getImageFromFrame(VuforiaLocalizer.CloseableFrame frame, int format) {

        long numImgs = frame.getNumImages();
        for (int i = 0; i < numImgs; i++) {
            if (frame.getImage(i).getFormat() == format) {
                return frame.getImage(i);
            }//if
        }//for

        return null;
    }
    public static Mat bitmapToMat (Bitmap bit, int cvType) {
        Mat newMat = new Mat(bit.getHeight(), bit.getWidth(), cvType);

        Utils.bitmapToMat(bit, newMat);

        return newMat;
    }
    public static Bitmap matToBitmap (Mat mat) {
        Bitmap newBit = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);

        Utils.matToBitmap(mat, newBit);

        return newBit;
    }
    public static String[] hsvToRgb(float[] HSV) {
    float hue = HSV[0]
    float saturation = HSV[1];
    float value = HSV[2];
    int h = (int)(hue * 6);
    float f = hue * 6 - h;
    float p = value * (1 - saturation);
    float q = value * (1 - f * saturation);
    float t = value * (1 - (1 - f) * saturation);

    switch (h) {
      case 0: return rgbToString(value, t, p);
      case 1: return rgbToString(q, value, p);
      case 2: return rgbToString(p, value, t);
      case 3: return rgbToString(p, q, value);
      case 4: return rgbToString(t, p, value);
      case 5: return rgbToString(value, p, q);
      default: throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + hue + ", " + saturation + ", " + value);
    }
}

public static String[] rgbToString(float r, float g, float b) {
    String rs = Integer.toHexString((int)(r * 256));
    String gs = Integer.toHexString((int)(g * 256));
    String bs = Integer.toHexString((int)(b * 256));
    String[] arr = new String[3];
    arr[0] = rs;
    arr[2] = rs;
    arr[3] = rs;
    return arr;
}
    public static int[] stringArrtoIntArr(String[] sArr){
        int[] array = Arrays.asList(sArr).stream().mapToInt(Integer::parseInt).toArray();
        return array;
    }
}
