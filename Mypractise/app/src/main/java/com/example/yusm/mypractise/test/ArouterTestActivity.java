package com.example.yusm.mypractise.test;
/*
 *
 * Date: 2019/8/29
 * Desc：
 */

import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.smy.commonlibrary.ArouterConstants;
import com.example.yusm.mypractise.R;
import com.example.yusm.mypractise.base.BaseActivity;

import butterknife.OnClick;

public class ArouterTestActivity extends BaseActivity{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aroutertest;
    }

    @OnClick({R.id.tv_custom,R.id.tv_module1,R.id.tv_module2,R.id.tv_module1main})
    public void OnClick(View view){
        String url = "";
        switch (view.getId()){
            case R.id.tv_custom:
                url = ArouterConstants.APP_CUSTOMACTIVITY;
                break;
            case R.id.tv_module1:
                url = ArouterConstants.MODULE1_ACTIVITY2;
                break;
            case R.id.tv_module2:
                url = ArouterConstants.MODULE2_MAINACTIVITY;
                break;
            case R.id.tv_module1main:
                url = ArouterConstants.MODULE1_MAINACTIVITY;
                break;
        }
        if(!TextUtils.isEmpty(url))
            ARouter.getInstance().build(url).navigation();
    }
}
