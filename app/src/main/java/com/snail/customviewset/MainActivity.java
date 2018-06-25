package com.snail.customviewset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author liuyongjie
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private String[] contents = new String[]{
            "ViewPagerActivity",
            "TouchEventTestActivity",
            "FastIndexViewActivity"
    };
    /**
     * 这个数组和上面的数组具有一一对应的关系
     */
    private Class[] clazz = new Class[]{
            ViewPagerActivity.class,
            TouchEventTestActivity.class,
            FastIndexActivity.class
    };

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv_entry);
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, contents);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(clazz[position]);
            }
        });
    }

    public void startActivity(Class<Activity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}
