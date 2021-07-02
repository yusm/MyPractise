package com.example.yusm.mypractise.hook;
/*
 *
 * Date: 2019/7/5
 * Desc：
 */

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.yusm.mypractise.utils.FieldUtils;

public class HCallback implements Handler.Callback {

    public static final int LAUNCH_ACTIVITY = 100;

    Handler mHandler;

    public HCallback(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.what == LAUNCH_ACTIVITY){
            Object r = msg.obj;
            try {
                //得到消息中的Intent(启动AnimationActivity的Intent)
                Intent intent = (Intent) FieldUtils.getField(r.getClass(),r,"intent");
                //得到此前保存起来的intent(启动NonRegisterActivity的Intent)
                Intent target = intent.getParcelableExtra(HookHelper.TARGET_INTENT);
                //将启动AnimationActivity的intent替换为启动TargetActivity的Intent
                Log.i("proxy target ",target.getComponent().getClassName()+"  "+target.getComponent().getPackageName());
                intent.setComponent(target.getComponent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mHandler.handleMessage(msg);
        return true;
    }
}
