package com.example.yusm.mypractise.base;
/*
 *
 * Created by iPanel@iPanel.cn
 * Date: 2019/2/27
 * Desc：
 */

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        TAG = getClass().getSimpleName();
        Log.i(TAG," oncreate");
        unbinder = ButterKnife.bind(this);
        initUI();
    }

    protected void initUI() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected abstract int getLayoutId();

    public View getRootView(){
        return ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
    }

    public boolean isAppRunningForeground(){
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : runningAppProcesses) {
            if(appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
                if(appProcessInfo.processName.equals(this.getApplicationInfo().processName))
                    return true;
            }
        }
        return false;
    }
}
