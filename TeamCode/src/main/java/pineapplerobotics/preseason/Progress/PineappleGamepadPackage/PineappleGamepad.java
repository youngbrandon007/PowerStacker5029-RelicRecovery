package pineapplerobotics.preseason.Progress.PineappleGamepadPackage;

import android.view.KeyEvent;
import android.view.MotionEvent;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Brandon on 7/12/2017.
 */

public class PineappleGamepad extends Gamepad{

    public PineappleAdvencedGamepad advanced;

    public PineappleGamepad(Gamepad g){
        advanced = new PineappleAdvencedGamepad();
    }

    public void update(android.view.KeyEvent event) {
        /*
        int key = event.getKeyCode();
        if      (key == KeyEvent.KEYCODE_DPAD_UP) dpad_up = pressed(event);
        else if (key == KeyEvent.KEYCODE_DPAD_DOWN) dpad_down = pressed(event);
        else if (key == KeyEvent.KEYCODE_DPAD_RIGHT) dpad_right = pressed(event);
        else if (key == KeyEvent.KEYCODE_DPAD_LEFT) dpad_left = pressed(event);
        else if (key == KeyEvent.KEYCODE_BUTTON_A) a = pressed(event);
        else if (key == KeyEvent.KEYCODE_BUTTON_B) b = pressed(event);
        else if (key == KeyEvent.KEYCODE_BUTTON_X) x = pressed(event);
        else if (key == KeyEvent.KEYCODE_BUTTON_Y) y = pressed(event);
        else if (key == KeyEvent.KEYCODE_BUTTON_MODE) guide = pressed(event);
        else if (key == KeyEvent.KEYCODE_BUTTON_START) start = pressed(event);
        else if (key == KeyEvent.KEYCODE_BUTTON_SELECT) back = pressed(event);
        else if (key == KeyEvent.KEYCODE_BUTTON_R1) right_bumper = pressed(event);
        else if (key == KeyEvent.KEYCODE_BUTTON_L1) left_bumper = pressed(event);
        else if (key == KeyEvent.KEYCODE_BUTTON_THUMBL) left_stick_button = pressed(event);
        else if (key == KeyEvent.KEYCODE_BUTTON_THUMBR) right_stick_button = pressed(event);
        */
    }

    public void update(android.view.MotionEvent event) {
        /*
        left_stick_x = cleanMotionValues(event.getAxisValue(MotionEvent.AXIS_X));
        left_stick_y = cleanMotionValues(event.getAxisValue(MotionEvent.AXIS_Y));
        right_stick_x = cleanMotionValues(event.getAxisValue(MotionEvent.AXIS_Z));
        right_stick_y = cleanMotionValues(event.getAxisValue(MotionEvent.AXIS_RZ));
        left_trigger = event.getAxisValue(MotionEvent.AXIS_LTRIGGER);
        right_trigger = event.getAxisValue(MotionEvent.AXIS_RTRIGGER);
        dpad_down = event.getAxisValue(MotionEvent.AXIS_HAT_Y) > dpadThreshold;
        dpad_up = event.getAxisValue(MotionEvent.AXIS_HAT_Y) < -dpadThreshold;
        dpad_right = event.getAxisValue(MotionEvent.AXIS_HAT_X) > dpadThreshold;
        dpad_left = event.getAxisValue(MotionEvent.AXIS_HAT_X) < -dpadThreshold;
        */
    }
}
