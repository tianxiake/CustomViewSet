package com.snail.customviewset;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.snail.customviewset.view.CustomFlowLayout;
import com.snail.customviewset.view.MyViewPager;

public class MainActivity extends AppCompatActivity {

    private int[] drawables = new int[]{
            R.drawable.drawable_one,
            R.drawable.drawable_two,
            R.drawable.drawable_three,
            R.drawable.drawable_four
    };

    private MyViewPager myViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_my_view_pager);

        myViewPager = findViewById(R.id.mvp_layout);

        for (int i = 0; i < drawables.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(drawables[i]);
            myViewPager.addView(imageView);
        }
    }
}
