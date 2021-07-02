package com.example.yusm.mypractise.service;
/*
 *
 * Date: 2019/8/1
 * Desc：
 */

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestThreadService extends IntentService{

    private static final String TAG = TestThreadService.class.getSimpleName();

    public TestThreadService() {
        super(TAG);
        Log.i(TAG,"TestThreadService,"+Thread.currentThread());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate,"+Thread.currentThread());
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG,"onStart,"+Thread.currentThread());
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand,"+Thread.currentThread());
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy,"+Thread.currentThread());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind,"+Thread.currentThread());
        return new MyBinder();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG,"onHandleIntent,"+Thread.currentThread());
    }

    class MyBinder extends Binder{

    }
}
