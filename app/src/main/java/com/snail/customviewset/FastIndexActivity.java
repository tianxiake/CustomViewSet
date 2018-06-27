package com.snail.customviewset;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.snail.customviewset.adapter.MyAdapter;
import com.snail.customviewset.bean.Person;
import com.snail.customviewset.util.ToastUtil;
import com.snail.customviewset.view.FastIndexView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FastIndexActivity extends AppCompatActivity {

    private static final String LYJ_TAG = "LYJ_FastIndexActivity";
    private FastIndexView indicator;
    private ListView listView;
    private Context context;
    private String[] names = new String[]{
            "张三", "李四", "王五",
            "杨过", "小龙女", "郭靖",
            "射雕", "英雄", "传",
            "Android", "开发", "艺术", "探索",
            "东邪", "西毒", "南帝", "北丐"
    };
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_index);
        context = this;
        indicator = findViewById(R.id.fiv_indicator);
        listView = findViewById(R.id.lv_content);
        final List<Person> list = new ArrayList<>();
        for (String name : names) {
            Person person = new Person(name);
            list.add(person);
        }
        Collections.sort(list);
        myAdapter = new MyAdapter(context, list);
        listView.setAdapter(myAdapter);

        indicator.setOnFastIndexSelectedListener(new FastIndexView.OnFastIndexSelectedListener() {
            @Override
            public void onSelected(int position, String letter) {
                Log.d(LYJ_TAG, "letter:" + letter);
                ToastUtil.showToast(context, letter);
                char c = letter.toLowerCase().charAt(0);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getPinyin().toLowerCase().charAt(0) == c) {
                        listView.setSelection(i);
                        break;
                    }
                }
            }
        });
    }


}
