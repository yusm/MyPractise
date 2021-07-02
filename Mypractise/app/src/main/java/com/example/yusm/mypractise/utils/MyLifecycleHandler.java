package com.example.yusm.mypractise.utils;
/*
 *
 * Date: 2019/9/19
 * Desc：
 */

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class MyLifecycleHandler implements Application.ActivityLifecycleCallbacks {

    private static MyLifecycleHandler INSTANCE;

    public static MyLifecycleHandler getInstance(){
        if(INSTANCE==null)
            INSTANCE = new MyLifecycleHandler();
        return INSTANCE;
    }

    private MyLifecycleHandler(){}

    private static int resumed;
    private static int paused;
    private static int started;
    private static int stopped;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ++resumed;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public static boolean isApplicationVisible(){
        return started>stopped;
    }

    public static boolean isApplicationInForeground(){
        return resumed>paused;
    }
}
