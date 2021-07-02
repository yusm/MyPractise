package com.example.yusm.mypractise.widget;
/*
 *
 * Date: 2019/8/29
 * Desc：
 */

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.HashMap;

public class ViewTouch extends LinearLayout{

    private String TAG_PRE = "ViewTouch";
    public String TAG = "A";

    static HashMap<Integer,String> actions = new HashMap<>();

    static {
        actions.put(0,"ACTION_DOWN");
        actions.put(1,"ACTION_UP");
        actions.put(2,"ACTION_MOVE");
        actions.put(3,"ACTION_CANCEL");
        actions.put(4,"ACTION_OUTSIDE");
        actions.put(5,"ACTION_POINTER_DOWN");
        actions.put(6,"ACTION_POINTER_UP");
    }


    public ViewTouch(Context context) {
        super(context);
    }

    public ViewTouch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewTouch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewTouch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG_PRE+TAG,TAG+"....  dispatchTouchEvent    "+actions.get(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG_PRE+TAG,TAG+"----  onInterceptTouchEvent    "+actions.get(ev.getAction()));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG_PRE+TAG,TAG+"++++  onTouchEvent    "+actions.get(event.getAction()));
        return super.onTouchEvent(event);
    }
}
