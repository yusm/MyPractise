package com.example.yusm.mypractise.hook;
/*
 *
 * Date: 2019/7/5
 * Desc：
 */

import android.os.Build;
import android.os.Handler;
import android.util.Log;

import com.example.yusm.mypractise.utils.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class HookHelper {

    public static final String TARGET_INTENT = "target_intent";

    public static void hookAMS() throws Exception {
        Object defaultSingleton = null;

        Log.i("hook",Build.VERSION.SDK_INT+" ");
        if(Build.VERSION.SDK_INT >= 26){
            Class<?> activityManagerClazz = Class.forName("android.app.ActivityManager");
            //获取activityManager中的IActivityManagerSingleton字段
            defaultSingleton = FieldUtils.getField(activityManagerClazz,null,"IActivityManagerSingleton");
        }else{
            Class<?> activityManegerNativeClazz = Class.forName("android.app.ActivityManagerNative");
            //获取ActivityManagerNative中的gDefault字段
            defaultSingleton = FieldUtils.getField(activityManegerNativeClazz,null,"gDefault");
        }

        Class<?> singletonClazz = Class.forName("android.util.Singleton");
        Field mInstanceField = FieldUtils.getField(singletonClazz,"mInstance");
        //获取IActivityManager
        Object iActivityManager = mInstanceField.get(defaultSingleton);
        Class<?> iActivityManagerClazz = Class.forName("android.app.IActivityManager");
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class<?>[]{iActivityManagerClazz},new IActivityManagerProxy(iActivityManager));

        mInstanceField.set(defaultSingleton,proxy);
    }

    public static void hookHandler() throws Exception {
        Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");
        Object currentActivityThread = FieldUtils.getField(activityThreadClazz,null,"sCurrentActivityThread");
        Field mHField = FieldUtils.getField(activityThreadClazz,"mH");
        Handler mH = (Handler) mHField.get(currentActivityThread);
        FieldUtils.setField(Handler.class,mH,"mCallback",new HCallback(mH));
    }
}
