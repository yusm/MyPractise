package com.example.yusm.mypractise.test;
/*
 *
 * Date: 2019/8/29
 * Desc：
 */

import com.example.yusm.mypractise.R;
import com.example.yusm.mypractise.base.BaseActivity;
import com.example.yusm.mypractise.widget.ViewTouch;

import butterknife.BindView;

public class ViewTouchActivity extends BaseActivity{

    @BindView(R.id.touch_a) ViewTouch viewTouchA;
    @BindView(R.id.touch_b) ViewTouch viewTouchB;
    @BindView(R.id.touch_c) ViewTouch viewTouchC;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viewtouch;
    }

    @Override
    protected void initUI() {
        super.initUI();
        viewTouchA.TAG = "A";
        viewTouchB.TAG = "B";
        viewTouchC.TAG = "C";
        viewTouchA.setClickable(true);
        viewTouchB.setClickable(true);
        viewTouchC.setClickable(true);
    }
}
