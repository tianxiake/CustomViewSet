package com.snail.customviewset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class TouchEventTestActivity extends AppCompatActivity {

    private static final String LYJ_TAG = "LYJ_TouchEventActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(LYJ_TAG, "onTouchEvent Action=" + event.getAction());
        return super.onTouchEvent(event);
    }
}
