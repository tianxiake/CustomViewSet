package com.snail.customviewset.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author yongjie on 2018/6/27.
 */
public class ShapeView extends View {

    private Paint paint;
    private Shape currentShape = Shape.CIRCLE;


    private enum Shape {
        CIRCLE,
        RECTANGLE,
        TRIANGLE
    }

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (currentShape) {
            case CIRCLE:
                paint.setColor(Color.RED);
                int center = getWidth() / 2;
                canvas.drawCircle(center, center, center, paint);
                break;
            case RECTANGLE:
                paint.setColor(Color.GREEN);
                canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
                break;
            case TRIANGLE:
                paint.setColor(Color.BLUE);
                Path path = new Path();
                path.moveTo(getWidth() / 2, 0);
                path.lineTo(0, (float) (Math.sqrt(3) * (getWidth() / 2)));
                path.lineTo(getWidth(), (float) (Math.sqrt(3) * (getWidth() / 2)));
                canvas.drawPath(path, paint);
                break;
        }
    }

    public void changeShape() {
        switch (currentShape) {
            case CIRCLE:
                currentShape = Shape.RECTANGLE;
                break;
            case RECTANGLE:
                currentShape = Shape.TRIANGLE;
                break;
            case TRIANGLE:
                currentShape = Shape.CIRCLE;
                break;
        }
        invalidate();
    }


}
