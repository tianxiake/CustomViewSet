package com.snail.customviewset.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.snail.customviewset.R;

/**
 * @author yongjie on 2018/5/27.
 */
public class MyTextView extends AppCompatTextView {

    private static final String LYJ_TAG = "LYJ_MyTextView";

    public MyTextView(Context context) {
        this(context, null);
        Log.v(LYJ_TAG, "MyTextView 一个参数的构造方法");
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.v(LYJ_TAG, "MyTextView 两个参数的构造方法");
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.v(LYJ_TAG, "MyTextView 三个参数的构造方法");
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);

        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            Log.d(LYJ_TAG, "index = " + index);
            switch (index) {
                case R.styleable.MyTextView_text_interval:
                    int integer = typedArray.getInteger(index, 0);
                    Log.d(LYJ_TAG, "integer = " + integer);
                    break;
            }
        }
        typedArray.recycle();
    }
}
