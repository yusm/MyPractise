package com.example.yusm.mypractise.hook;
/*
 *
 * Date: 2019/7/5
 * Desc：
 */

import android.content.Intent;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IActivityManagerProxy implements InvocationHandler{

    private Object mActivityManager;

    public IActivityManagerProxy(Object mActivityManager) {
        this.mActivityManager = mActivityManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("startActivity".equals(method.getName())){
            Intent intent = null;
            int index = 0;
            for(int i=0;i<args.length;i++){
                if(args[i] instanceof Intent){
                    index = i;
                    break;
                }
            }
            intent = (Intent) args[index];
            Log.i("proxy",intent.getComponent().getClassName()+"  "+intent.getComponent().getPackageName());
            Intent subIntent = new Intent();
            String packageName = "com.example.yusm.mypractise";
            subIntent.setClassName(packageName,packageName+".AnimationActivity");
            subIntent.putExtra(HookHelper.TARGET_INTENT,intent);
            args[index] = subIntent;
        }
        return method.invoke(mActivityManager,args);
    }
}
