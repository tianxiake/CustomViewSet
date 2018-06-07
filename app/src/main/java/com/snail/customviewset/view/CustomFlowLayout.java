package com.snail.customviewset.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author yongjie on 2018/6/4.
 */
public class CustomFlowLayout extends ViewGroup {


    private static final String LYJ_TAG = "LYJ_CustomFlowLayout";

    public CustomFlowLayout(Context context) {
        super(context);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写逻辑：
     * 1>获取父view的建议大小
     * 2>测量子view的大小，要考虑换行的问题
     * 3>设置测量的结果
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //定义测量结果
        int measureWidth = 0;
        int measureHeight = 0;

        //获取父view期望的子view的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取父view期望的子view的大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int lineWidth = 0;
        int lineHeight = 0;
        int childWidth = 0;
        int childHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            //此处已经测量了子view，后面的onLayout中就可以直接用了
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (childWidth + lineWidth > widthSize) {
                //换行
                measureWidth = Math.max(measureWidth, lineWidth);
                //这个高度加的是上一行的高度
                measureHeight += lineHeight;
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                //不换行
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }

            if (i == childCount - 1) {
                measureWidth = Math.max(measureWidth, lineWidth);
                measureHeight += lineHeight;
            }

        }


        Log.v(LYJ_TAG, "measureWidth=" + measureWidth + ",measureHeight=" + measureHeight);
        //设置测量结果
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : measureWidth,
                heightMode == MeasureSpec.EXACTLY ? heightSize : measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //定义子view的四个坐标
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;

        int lineLeft = 0;
        int lineHeight = 0;
        //行高的累加值
        int height = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (childWidth + lineLeft > getMeasuredWidth()) {
                //换行
                height += lineHeight;
                lineLeft = childWidth;
                left = lp.leftMargin;
                top = height + lp.topMargin;
                right = left + child.getMeasuredWidth();
                bottom = top + child.getMeasuredHeight();
            } else {
                //不换行
                left = lineLeft + lp.leftMargin;
                top = height + lp.topMargin;
                right = left + child.getMeasuredWidth();
                bottom = top + child.getMeasuredHeight();
                lineLeft += childWidth;
                lineHeight = Math.max(childHeight, lineHeight);
            }

            Log.v(LYJ_TAG, "index=" + i + ",left=" + left + ",top=" + top + ",right=" + right + ",bottom=" + bottom);
            child.layout(left, top, right, bottom);
        }

    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        if (onItemClickListener != null) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                final int position = i;
                View child = getChildAt(i);
                child.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onClick(v, position);
                    }
                });
            }
        }

    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return super.checkLayoutParams(p);
    }

    public interface OnItemClickListener {

        /**
         * 点击事件
         *
         * @param view
         */
        void onClick(View view, int position);
    }
}
