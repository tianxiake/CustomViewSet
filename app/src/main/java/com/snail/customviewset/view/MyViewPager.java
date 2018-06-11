package com.snail.customviewset.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class MyViewPager extends ViewGroup {
    public MyViewPager(Context context) {
        this(context, null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        //一个view摆放多个视图，让其不可见，配置手势让其可见
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }
}
