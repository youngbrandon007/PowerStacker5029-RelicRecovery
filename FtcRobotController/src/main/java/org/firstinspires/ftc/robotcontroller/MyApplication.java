package org.firstinspires.ftc.robotcontroller;

import android.app.Application;
import android.content.Context;

/**
 * Created by ftcpi on 11/20/2017.
 */

public class MyApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}