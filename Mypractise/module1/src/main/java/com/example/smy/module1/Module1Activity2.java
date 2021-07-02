package com.example.smy.module1;
/*
 *
 * Date: 2019/8/29
 * Desc：
 */

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.smy.commonlibrary.ArouterConstants;

@Route(path = ArouterConstants.MODULE1_ACTIVITY2)
public class Module1Activity2 extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        findViewById(R.id.tv_custom).setOnClickListener(this);
        findViewById(R.id.tv_module2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String url = "";
        if(v.getId()==R.id.tv_custom)
            url = ArouterConstants.APP_CUSTOMACTIVITY;
        else if(v.getId()==R.id.tv_module2)
            url = ArouterConstants.MODULE2_MAINACTIVITY;
        if(!TextUtils.isEmpty(url))
            ARouter.getInstance().build(url).navigation();
    }
}
