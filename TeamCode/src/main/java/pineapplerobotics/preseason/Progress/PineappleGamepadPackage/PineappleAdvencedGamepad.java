package pineapplerobotics.preseason.Progress.PineappleGamepadPackage;

/**
 * Created by Brandon on 7/16/2017.
 */

public class PineappleAdvencedGamepad {

    public float left_stick_x = 0f;

    /**
     * left analog stick vertical axis
     */
    public float left_stick_y = 0f;

    /**
     * right analog stick horizontal axis
     */
    public float right_stick_x = 0f;

    /**
     * right analog stick vertical axis
     */
    public float right_stick_y = 0f;

    /**
     * dpad up
     */
    public boolean dpad_up = false;

    /**
     * dpad down
     */
    public boolean dpad_down = false;

    /**
     * dpad left
     */
    public boolean dpad_left = false;

    /**
     * dpad right
     */
    public boolean dpad_right = false;

    /**
     * button a
     */
    public boolean a = false;

    /**
     * button b
     */
    public boolean b = false;

    /**
     * button x
     */
    public boolean x = false;

    /**
     * button y
     */
    public boolean y = false;

    /**
     * button guide - often the large button in the middle of the controller. The OS may
     * capture this button before it is sent to the app; in which case you'll never
     * receive it.
     */
    public boolean guide = false;

    /**
     * button start
     */
    public boolean start = false;

    /**
     * button back
     */
    public boolean back = false;

    /**
     * button left bumper
     */
    public boolean left_bumper = false;

    /**
     * button right bumper
     */
    public boolean right_bumper = false;

    /**
     * left stick button
     */
    public boolean left_stick_button = false;

    /**
     * right stick button
     */
    public boolean right_stick_button = false;

    /**
     * left trigger
     */
    public float left_trigger = 0f;

    /**
     * right trigger
     */
    public float right_trigger = 0f;

}
