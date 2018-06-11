package com.snail.customviewset.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class MyViewPager extends ViewGroup {

    private static String LYJ_TAG = "LYJ_MyViewPager";
    private GestureDetector gestureDetector;

    public MyViewPager(Context context) {
        this(context, null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.d(LYJ_TAG, "distanceX=" + distanceX + ",distanceY=" + distanceY);
                scrollBy((int) distanceX, 0);
                return true;
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        //一个view摆放多个视图，让其不可见，配置手势让其可见
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(LYJ_TAG, "event=" + event.getAction());
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int pageIndex = scrollX / getWidth();
                int offset = scrollX % getWidth();
                Log.d(LYJ_TAG, "scrollX=" + scrollX + ",pageIndex=" + pageIndex + ",offset=" + offset);
                if (offset > getWidth() / 2) {
                    //下一页
                    pageIndex++;
                }
                if (pageIndex > getChildCount() - 1) {
                    pageIndex = getChildCount() - 1;
                }
                getCurrentPage(pageIndex);
                break;
        }
        return true;
    }

    public void getCurrentPage(int pageIndex) {
        scrollTo(pageIndex * getWidth(), 0);
    }
}
