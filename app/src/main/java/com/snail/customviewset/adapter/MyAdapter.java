package com.snail.customviewset.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.snail.customviewset.R;
import com.snail.customviewset.bean.Person;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Person> personList;

    public MyAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Person getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private char lastChar;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_layout, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.indicator = convertView.findViewById(R.id.tv_indicator);
            viewHolder.content = convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        Person person = personList.get(position);
        String pinyin = person.getPinyin();

        char currentChar = pinyin.toLowerCase().charAt(0);
        if (currentChar != lastChar) {
            viewHolder.indicator.setVisibility(View.VISIBLE);
            viewHolder.indicator.setText(currentChar + "");
            lastChar = currentChar;
        } else {
            viewHolder.indicator.setVisibility(View.GONE);
        }
        viewHolder.content.setText(person.getName());
        return convertView;
    }

    private class ViewHolder {
        public TextView indicator;
        public TextView content;
    }
}
