package com.snail.customviewset.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyViewPager extends ViewGroup {

    private static String LYJ_TAG = "LYJ_MyViewPager";
    private GestureDetector gestureDetector;
    private Scroller scroller;
    private OnPageChangeListener onPageChangeListener;
    private int downX = 0;
    private int downY = 0;
    private int dx;
    private int dy;

    public MyViewPager(Context context) {
        this(context, null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        Log.d(LYJ_TAG, "init ScrollX=" + getScaleX());
    }

    private void init() {
        scroller = new Scroller(getContext());
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.d(LYJ_TAG, "GestureDetector distanceX=" + distanceX + ",distanceY=" + distanceY);
                scrollBy((int) distanceX, 0);
                Log.d(LYJ_TAG, "GestureDetector ScrollX=" + getScaleX());
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();
                dx = moveX - downX;
                dy = moveY - downY;
                if (Math.abs(dx) > Math.abs(dy)) {
                    result = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);
        }
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
                Log.d(LYJ_TAG, "onTouchEvent scrollX=" + scrollX + ",pageIndex=" + pageIndex + ",offset=" + offset);
                if (offset > getWidth() / 2) {
                    //下一页
                    pageIndex++;
                }
                if (pageIndex > getChildCount() - 1) {
                    pageIndex = getChildCount() - 1;
                }
                getCurrentPage(pageIndex);
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageChange(pageIndex);
                }
                break;
        }
        return true;
    }

    public void getCurrentPage(int pageIndex) {
//        scrollTo(pageIndex * getWidth(), 0);
        int dx = pageIndex * getWidth() - getScrollX();
        scroller.startScroll(getScrollX(), 0, dx, 0);
        invalidate();
    }

    public void setOnPageChangeListener(MyViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            int currX = scroller.getCurrX();
            scrollTo(currX, 0);
            invalidate();
        }
    }

    public interface OnPageChangeListener {
        void onPageChange(int pageIndex);
    }
}
