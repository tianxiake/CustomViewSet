package com.snail.customviewset;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class CustomSimpleRelativeLayout extends ViewGroup {

    public CustomSimpleRelativeLayout(Context context) {
        super(context);
    }

    public CustomSimpleRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSimpleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
