package com.snail.customviewset.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class FastIndexView extends View {

    private static final String TAG = "FastIndexView";
    private Paint paint;
    private int width;
    private int height;
    private float singleCharHeight;
    private OnFastIndexSelectedListener onFastIndexSelectedListener;
    private String[] letters = new String[]{
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    public FastIndexView(Context context) {
        this(context, null);
    }

    public FastIndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FastIndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        width = getMeasuredWidth();
        singleCharHeight = height / letters.length;
        paint.setTextSize(singleCharHeight - singleCharHeight / 4f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < letters.length; i++) {
            String letter = letters[i];
            float x = width / 2 - paint.measureText(letter) / 2;
            Rect rect = new Rect();
            paint.getTextBounds(letter, 0, letter.length(), rect);
            float y = i * singleCharHeight + singleCharHeight / 2 + rect.height() / 2;
            canvas.drawText(letters[i], x, y, paint);
        }
    }

    private int lastIndex = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "down");
                float downY = event.getY();
                int downIndex = (int) (downY / singleCharHeight);
                if (downIndex < letters.length && downIndex != lastIndex) {
                    if (downIndex * singleCharHeight < downY && downY < (downIndex + 1) * singleCharHeight) {
                        if (onFastIndexSelectedListener != null) {
                            onFastIndexSelectedListener.onSelected(downIndex, letters[downIndex]);
                        }
                        Log.d(TAG, "char:" + letters[downIndex]);
                        lastIndex = downIndex;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();
                int moveIndex = (int) (moveY / singleCharHeight);
                if (moveIndex < letters.length && moveIndex != lastIndex) {
                    if (moveIndex * singleCharHeight < moveY && moveY < (moveIndex + 1) * singleCharHeight) {
                        if (onFastIndexSelectedListener != null) {
                            onFastIndexSelectedListener.onSelected(moveIndex, letters[moveIndex]);
                        }
                        Log.d(TAG, "char:" + letters[moveIndex]);
                        lastIndex = moveIndex;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                lastIndex = -1;
                break;
        }
        return true;
    }


    public void setOnFastIndexSelectedListener(OnFastIndexSelectedListener onFastIndexSelectedListener) {
        this.onFastIndexSelectedListener = onFastIndexSelectedListener;
    }

    public interface OnFastIndexSelectedListener {
        void onSelected(int position, String letter);
    }
}
