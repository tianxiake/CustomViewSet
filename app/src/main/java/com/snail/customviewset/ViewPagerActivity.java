package com.snail.customviewset;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.snail.customviewset.view.MyScrollView;
import com.snail.customviewset.view.MyViewPager;

import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private static String LYJ_TAG = "LYJ_MainActivity";
    private int[] drawables = new int[]{
            R.drawable.drawable_two,
            R.drawable.drawable_three,
            R.drawable.drawable_four
    };

    private MyViewPager myViewPager;
    private RadioGroup radioGroup;
    private MyScrollView myScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_my_view_pager);

        myViewPager = findViewById(R.id.mvp_layout);
        radioGroup = findViewById(R.id.rg_container);
        View inflate = LayoutInflater.from(this).inflate(R.layout.test, null);

        for (int i = 0; i < drawables.length; i++) {
            if (i == 1) {
                myViewPager.addView(inflate);
                continue;
            }
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

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> mAllIntentApps = getPackageManager().queryIntentActivities(mainIntent, 0);
        for (ResolveInfo mAllIntentApp : mAllIntentApps) {
            Log.d("mAllIntentApp", "packageName:" + mAllIntentApp.activityInfo.packageName + ",activityName=" + mAllIntentApp.activityInfo.name);
        }
    }
}
