package com.snail.customviewset;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.snail.customviewset.util.ToastUtil;
import com.snail.customviewset.view.FastIndexView;

public class FastIndexActivity extends AppCompatActivity {

    private static final String LYJ_TAG = "LYJ_FastIndexActivity";
    private FastIndexView indicator;
    private ListView listView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_index);
        context = this;
        indicator = findViewById(R.id.fiv_indicator);
        listView = findViewById(R.id.lv_content);

        indicator.setOnFastIndexSelectedListener(new FastIndexView.OnFastIndexSelectedListener() {
            @Override
            public void onSelected(int position, String letter) {
                Log.d(LYJ_TAG, "letter:" + letter);
                ToastUtil.showToast(context, letter);
            }
        });
    }
}
