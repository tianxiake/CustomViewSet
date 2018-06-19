package com.snail.customviewset.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.snail.customviewset.R;

/**
 * @author yongjie on 2018/6/10.
 */
public class WaterFallLayout extends ViewGroup {

    private static final String LYJ_TAG = "LYJ_WaterFallLayout";

    /**
     * 指定的列数
     */
    private int columns = 1;
    /**
     * 列与列之间水平的距离 单位px
     */
    private int horizontalSpace = 0;
    /**
     * 列与列竖直之间的距离 单位px
     */
    private int verticalSpace = 0;

    /**
     * 记录每列的高度值
     */
    private int[] columnHeight;
    /**
     * 每个控件占据的宽度
     */
    private int childWidth;


    public WaterFallLayout(Context context) {
        this(context, null);
    }

    public WaterFallLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterFallLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(LYJ_TAG, "WaterFallLayout init");
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaterFallLayout);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.WaterFallLayout_columns:
                    columns = typedArray.getInteger(index, columns);
                    break;
                case R.styleable.WaterFallLayout_horizontal_space:
                    horizontalSpace = (int) typedArray.getDimension(index, horizontalSpace);
                    break;
                case R.styleable.WaterFallLayout_vertical_space:
                    verticalSpace = (int) typedArray.getDimension(index, verticalSpace);
                    break;
                default:
                    break;
            }
        }
        typedArray.recycle();
        columnHeight = new int[columns];

    }

    public int getChildWidth() {
        return childWidth;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(LYJ_TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //获取建议尺寸值
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measureWidth = 0;
        int measureHeight = 0;

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //计算一个控件该占的宽度
        childWidth = (widthSize - (columns - 1) * horizontalSpace) / columns;
        //计算总宽度
        int childCount = getChildCount();
        if (childCount < columns) {
            //表示子view的数量被指定的列数少，包裹
            measureWidth = childWidth * childCount + (childCount - 1) * horizontalSpace;
        } else {
            measureWidth = widthSize;
        }
        //计算总高度
        clearTop();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight() * childWidth / child.getMeasuredWidth();
            int minColumn = getMinColumn();
            columnHeight[minColumn] += verticalSpace + childHeight;
        }

        measureHeight = getMaxHeight();

        if (widthMode == MeasureSpec.EXACTLY) {
            measureWidth = widthSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeight = heightSize;
        }

        Log.d(LYJ_TAG, "measureWidth=" + measureWidth + ",measureHeight=" + measureHeight);
        //设置默认尺寸值
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        clearTop();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int minColumn = getMinColumn();
            int left = minColumn * (horizontalSpace + childWidth);
            int top = columnHeight[minColumn];
            int right = left + child.getMeasuredWidth();
            int bottom = top + child.getMeasuredHeight();
            columnHeight[minColumn] += verticalSpace + child.getMeasuredHeight();
            Log.d(LYJ_TAG, "left=" + left + ",top=" + top + ",right=" + right + ",bottom=" + bottom);
            child.layout(left, top, right, bottom);
        }
    }

    private int getMinColumn() {
        int minColumn = 0;
        for (int i = 0; i < columnHeight.length; i++) {
            if (columnHeight[i] < columnHeight[minColumn]) {
                minColumn = i;
            }
        }
        return minColumn;
    }

    private void clearTop() {
        for (int i = 0; i < columnHeight.length; i++) {
            columnHeight[i] = 0;
        }
    }


    private int getMaxHeight() {
        int maxIndex = 0;
        for (int i = 0; i < columnHeight.length; i++) {
            if (columnHeight[i] > columnHeight[maxIndex]) {
                maxIndex = i;
            }
        }
        return columnHeight[maxIndex];
    }
}
















