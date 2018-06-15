package com.snail.customviewset;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.snail.customviewset.view.CustomFlowLayout;
import com.snail.customviewset.view.MyViewPager;

public class MainActivity extends AppCompatActivity {

    private static String LYJ_TAG = "LYJ_MainActivity";
    private int[] drawables = new int[]{
            R.drawable.drawable_one,
            R.drawable.drawable_two,
            R.drawable.drawable_three,
            R.drawable.drawable_four
    };

    private MyViewPager myViewPager;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_my_view_pager);

        myViewPager = findViewById(R.id.mvp_layout);
        radioGroup = findViewById(R.id.rg_container);

        for (int i = 0; i < drawables.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(drawables[i]);
            myViewPager.addView(imageView);
        }

        int childCount = myViewPager.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            if (i == 0) {
                radioButton.setChecked(true);
            }
            radioGroup.addView(radioButton);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(LYJ_TAG, "checkedId=" + checkedId);
                Log.d(LYJ_TAG, "--------------------------------");
                Thread.dumpStack();
                Log.d(LYJ_TAG, "--------------------------------");
                myViewPager.getCurrentPage(checkedId);
            }
        });

        myViewPager.setOnPageChangeListener(new MyViewPager.OnPageChangeListener() {
            @Override
            public void onPageChange(int pageIndex) {
                radioGroup.check(pageIndex);
            }
        });

    }
}
