package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia;

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

/**
 * Created by young on 9/13/2017.
 */

public class PineappleRelicRecoveryVuforia extends PineappleVuforia{

    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    public PineappleRelicRecoveryVuforia(PineappleResources r, int maxItemCount, VuforiaLocalizer.CameraDirection direction, VuforiaLocalizer.Parameters.CameraMonitorFeedback feedback, String vuforiaLicenseKey) {
        super(r, maxItemCount, direction, feedback, vuforiaLicenseKey);

    }

    public void addRelicRecoveryTrackables(){
        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); //
    }

    public void startRelicRecoveryTracking(){
        relicTrackables.activate();
    }

    public PineappleEnum.VuMarkLocation getTrackingRelic(){
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
            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
            String strPose = format(pose);
            resources.feedBack.sayFeedBackWithOutUpdate("Pose", strPose);

            if (strPose.toLowerCase().equals("center")){
                loc = PineappleEnum.VuMarkLocation.CENTER;
            }
            if (strPose.toLowerCase().equals("left")){
                loc = PineappleEnum.VuMarkLocation.LEFT;
            }
            if (strPose.toLowerCase().equals("right")){
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
        }
        else {
            resources.feedBack.sayFeedBackWithOutUpdate("VuMark", "not visible");
        }

        resources.feedBack.update();
        return loc;
    }
}
