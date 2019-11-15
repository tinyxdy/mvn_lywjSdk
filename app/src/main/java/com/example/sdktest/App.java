package com.example.sdktest;

import android.app.Application;

import com.lywj.android.LywjSdkManger;

/**
 * Created by wxq on 2019/9/3.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LywjSdkManger.getInstance().init(this, "xxxxxxxxxxxx", "xxxxxxxxxxxxx");
        LywjSdkManger.getInstance().setDebug(true);
        LywjSdkManger.getInstance().setFloatingClass(MainActivity.class);
    }
}
