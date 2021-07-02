package com.example.yusm.mypractise;
/*
 *
 * Created by iPanel@iPanel.cn
 * Date: 2019/5/22
 * Desc：
 */

import android.Manifest;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Debug;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import com.example.yusm.mypractise.base.BaseActivity;
import com.example.yusm.mypractise.utils.LogUtils;
import com.yanzhenjie.permission.AndPermission;

import butterknife.BindView;
import butterknife.OnClick;

public class AnimationActivity extends BaseActivity{
    @BindView(R.id.iv_content) ImageView mImageView;
    @BindView(R.id.oval) View oval;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animation;
    }

    @Override
    protected void initUI() {
        super.initUI();


        Animation animation = AnimationUtils.loadAnimation(this,R.anim.tran_left);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        layoutAnimationController.setDelay(0.3f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_REVERSE);
        ((ViewGroup)getRootView()).setLayoutAnimation(layoutAnimationController);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Debug.startMethodTracing("AnimationActivity");
        requestPermission();
    }

    @Override
    protected void onResume() {
        LogUtils.i(TAG," onResume ");
        super.onResume();
//        requestPermission();
    }


//    @TargetApi(Build.VERSION_CODES.M)
//    private void requestPermission(){
//        LogUtils.i(TAG," in requestPermission");
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
//            LogUtils.i(TAG," get location Permission success");
//        }else requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
//    }

    private void requestPermission(){
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.ACCESS_FINE_LOCATION)
                .start();
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        LogUtils.i(TAG," onRequestPermissionsResult ");
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    @OnClick({R.id.iv_content,R.id.oval})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_content:
                PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
                PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
                PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 0, 1);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mImageView,holder1,holder2,holder3);
                animator.setDuration(3000);
                animator.start();
//                ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView,"rotation",360);
//                animator.setInterpolator(new AnticipateOvershootInterpolator());
//                animator.setDuration(2000);
//                animator.start();
                break;
            case R.id.oval:
                ObjectAnimator argAnimator = ObjectAnimator.ofInt(oval,"backgroundColor",0xffff0000, 0xff00ff00);
                argAnimator.setEvaluator(new ArgbEvaluator());
                argAnimator.setDuration(3000);
                argAnimator.start();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Debug.stopMethodTracing();
    }
}
