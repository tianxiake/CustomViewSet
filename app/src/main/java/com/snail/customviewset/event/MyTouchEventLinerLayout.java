package com.snail.customviewset.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author yongjie on 2018/6/19.
 */
public class MyTouchEventLinerLayout extends LinearLayout {

    private static final String LYJ_TAG = "LYJ_MyLinerLayout";

    public MyTouchEventLinerLayout(Context context) {
        super(context);
    }

    public MyTouchEventLinerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchEventLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(LYJ_TAG, "dispatchTouchEvent. Action=" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(LYJ_TAG, "onInterceptTouchEvent. Action=" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(LYJ_TAG, "onTouchEvent. Action=" + event.getAction());
        boolean result = super.onTouchEvent(event);
        Log.d(LYJ_TAG, "onTouchEvent result=" + result);
        return result;
    }
}
