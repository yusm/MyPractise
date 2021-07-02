package com.example.yusm.mypractise.hook;
/*
 *
 * Date: 2019/7/5
 * Desc：
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;

import com.example.yusm.mypractise.R;
import com.example.yusm.mypractise.base.BaseActivity;

public class NonRegisterActivcity extends BaseActivity{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aidl_client;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });
    }
}
