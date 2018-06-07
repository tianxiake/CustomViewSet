package com.snail.customviewset.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author yongjie on 2018/5/28.
 */
public class MyViewGroup extends ViewGroup {

    private static final String LYJ_TAG = "LYJ_MyViewGroup";

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(LYJ_TAG, "onMeasure");
        int childCount = getChildCount();
        if (childCount > 0) {
            View child = getChildAt(0);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(LYJ_TAG, "onLayout");
        int childCount = getChildCount();
        if (childCount > 0) {
            View child = getChildAt(0);
            child.layout(0, 0, child.getMeasuredWidth()+100, child.getMeasuredHeight()+100);
        }
    }
}
