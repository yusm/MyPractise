package com.example.yusm.mypractise.dagger;
/*
 *
 * Date: 2019/7/23
 * Desc：
 */

import android.widget.TextView;

import com.example.yusm.mypractise.R;
import com.example.yusm.mypractise.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DaggerTestActivity extends BaseActivity{
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.add) TextView add;

    @Inject
    Watch watch;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aidl_client;
    }

    @Override
    protected void initUI() {
        super.initUI();
        add.setText("click~");
        DaggerActivityComponent.builder().watchModule(new WatchModule()).build().inject(this);
    }

    @OnClick(R.id.add)
    public void add(){
        content.setText(watch.work());
    }
}
