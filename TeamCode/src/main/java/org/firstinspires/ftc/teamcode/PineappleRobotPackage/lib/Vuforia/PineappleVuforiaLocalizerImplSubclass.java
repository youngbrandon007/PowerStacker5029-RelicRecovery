package org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.Vuforia;

import com.qualcomm.robotcore.util.RobotLog;
import com.vuforia.Frame;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.State;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.internal.vuforia.VuforiaLocalizerImpl;

/**
 * Created by ftcpi on 9/15/2017.
 */

public class PineappleVuforiaLocalizerImplSubclass extends VuforiaLocalizerImpl {
    public Image rgb;

    class CloseableFrame extends Frame {
        public CloseableFrame(Frame other) {
            super(other);
        }

        public void close() {
            super.delete();
        }
    }

    public class VuforiaCallbackSubclass extends VuforiaLocalizerImpl.VuforiaCallback {
        @Override
        public synchronized void Vuforia_onUpdate(State state) {
            super.Vuforia_onUpdate(state);
            CloseableFrame frame = new CloseableFrame(state.getFrame());
            RobotLog.vv(TAG, "received Vuforia frame#=%d", frame.getIndex());
            long numb = frame.getNumImages();
            for (int i = 0; i < numb; i++) {
                if (frame.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
                    rgb = frame.getImage(i);
                }
            }
            frame.close();
        }
    }

    public PineappleVuforiaLocalizerImplSubclass(Parameters parameters) {
        super(parameters);
        stopAR();
        clearGLSurface();
        this.vuforiaCallback = new VuforiaCallbackSubclass();
        startAR();
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
    }

    private void clearGLSurface() {
        if (this.glSurfaceParent != null) {
            appUtil.synchronousRunOnUiThread(new Runnable() {
                @Override
                public void run() {
                    glSurfaceParent.removeAllViews();
                    glSurfaceParent.getOverlay().clear();
                    glSurface = null;
                }
            });
        }
    }
}
