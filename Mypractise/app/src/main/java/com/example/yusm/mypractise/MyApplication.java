package com.example.yusm.mypractise;
/*
 *
 * Date: 2019/7/5
 * Desc：
 */

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.yusm.mypractise.greendao.DaoMaster;
import com.example.yusm.mypractise.greendao.DaoSession;
import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication extends Application{

    public static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        //ARouter初始化，在多module有跳转需求时解耦使用即可
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
        
        initGreenDao();

        CrashReport.initCrashReport(this,"30822e7c12",true);
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"homeddao.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        try {
//            HookHelper.hookAMS();
//            HookHelper.hookHandler();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
