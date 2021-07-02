package com.example.yusm.mypractise.widget;
/*
 *
 * Created by iPanel@iPanel.cn
 * Date: 2019/5/23
 * Desc：
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.yusm.mypractise.R;

public class DragView extends AppCompatImageView {

    private String TAG = DragView.class.getSimpleName();

    Bitmap mBitmap;         //图片
    RectF mBitmapRectF;      //图片所在区域
    Matrix mBitmapMatrix;   //控制图片的matrix

    boolean canDrag = false;
    PointF lastPoint = new PointF(0,0);

    Paint mDeafultPaint;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //调整图片大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = 960/2;
        options.outHeight = 800/2;

        mBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.poly,options);
        mBitmapRectF = new RectF(0,0,mBitmap.getWidth(),mBitmap.getHeight());
        mBitmapMatrix = new Matrix();

        mDeafultPaint = new Paint();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                //判断是否是第一个手指，并且在图片区域内
                if(event.getPointerId(event.getActionIndex())==0&&mBitmapRectF.contains(event.getX(),event.getY())){
                    canDrag = true;
                    lastPoint.set(event.getX(),event.getY());
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                //判断是否是第一个手指
                if(event.getPointerId(event.getActionIndex())==0){
                    canDrag = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(canDrag){
                    //注意 getX 和 getY
                    int index = event.findPointerIndex(0);
                    mBitmapMatrix.postTranslate(event.getX(index)-lastPoint.x,event.getY()-lastPoint.y);
                    lastPoint.set(event.getX(index),event.getY(index));

                    mBitmapRectF = new RectF(0,0,mBitmap.getWidth(),mBitmap.getHeight());
                    mBitmapMatrix.mapRect(mBitmapRectF);

                    invalidate();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap,mBitmapMatrix,mDeafultPaint);
        super.onDraw(canvas);
    }
}
