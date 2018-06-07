package com.snail.customviewset;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.snail.customviewset.view.CustomFlowLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomFlowLayout flowLayout = findViewById(R.id.cfl_layout);
        flowLayout.setOnItemClickListener(new CustomFlowLayout.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, "position=" + position, Toast.LENGTH_LONG).show();
            }
        });


//        TextView textView = new TextView(this);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM);
//        textView.setLayoutParams(layoutParams);
    }
}
