package com.snail.customviewset.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.snail.customviewset.R;

public class CustomSimpleRelativeLayout extends ViewGroup {
    private static final String LYJ_TAG = "SimpleRelativeLayout";

    public CustomSimpleRelativeLayout(Context context) {
        this(context, null);
    }

    public CustomSimpleRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSimpleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //建议的宽高模式和尺寸
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measureWidth = 0;
        int measureHeight = 0;
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            measureWidth = widthSize;
        } else {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
                measureWidth = Math.max(measureWidth, childWidth);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeight = heightSize;
        } else {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                measureHeight = Math.max(measureHeight, childHeight);
            }
        }

        Log.d(LYJ_TAG, "measureWidth=" + measureWidth + ",measureHeight=" + measureHeight);
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int position = lp.POSITION;
            switch (position) {
                case LayoutParams.POSITION_LEFT:
                    left = 0 + lp.leftMargin;
                    top = 0 + lp.topMargin;
                    break;
                case LayoutParams.POSITION_RIGHT:
                    left = getWidth() - child.getMeasuredWidth() - lp.rightMargin;
                    top = 0 + lp.topMargin;
                    break;
                case LayoutParams.POSITION_CENTER:
                    left = (getWidth() - child.getMeasuredWidth()) / 2 - lp.rightMargin;
                    top = (getHeight() - child.getMeasuredHeight()) / 2 - lp.bottomMargin;
                    break;
                case LayoutParams.POSITION_BOTTOM:
                    left = 0+lp.leftMargin;
                    top = getHeight() - child.getMeasuredHeight() -lp.bottomMargin;
                    break;
                case LayoutParams.POSITION_RIGHT_AND_BOTTOM:
                    left = getWidth() - child.getMeasuredWidth()-lp.rightMargin;
                    top = getHeight() - child.getMeasuredHeight()-lp.bottomMargin;
                    break;
            }

            right = left + child.getMeasuredWidth();
            bottom = top + child.getMeasuredHeight();
            Log.d(LYJ_TAG, "index=" + i + ",left=" + left + ",top=" + top + ",right=" + right + ",bottom=" + bottom);
            child.layout(left, top, right, bottom);
        }

    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomSimpleRelativeLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new CustomSimpleRelativeLayout.LayoutParams(p);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new CustomSimpleRelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int POSITION_LEFT = 1; //左上
        public static final int POSITION_RIGHT = 2;//右上
        public static final int POSITION_CENTER = 3;//居中
        public static final int POSITION_BOTTOM = 4;//左下
        public static final int POSITION_RIGHT_AND_BOTTOM = 5; //右下
        public int POSITION = POSITION_LEFT;


        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.CustomSimpleRelativeLayout);
            POSITION = typedArray.getInt(R.styleable.CustomSimpleRelativeLayout_layout_position, POSITION_LEFT);
            typedArray.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }
    }
}
