package com.example.dongjunhuang.supportlib;

/**
 * Created by dongjunhuang on 10/9/14.
 */
import com.example.dongjunhuang.moneyrecording.*;
import com.example.dongjunhuang.moneytracker.R;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouhick on 6/25/14.
 */
public class ListAdapter extends BaseAdapter {

    private List<Money_Record> rowItem;
    private Context context;

    public ListAdapter(Context context, List<Money_Record> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
    }


    @Override
    public int getCount() {
        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.fragment_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.image_type);
        TextView text_money = (TextView) convertView.findViewById(R.id.text_money);
        TextView text_source = (TextView) convertView.findViewById(R.id.text_source);
        TextView text_date = (TextView) convertView.findViewById(R.id.text_date);


        Money_Record row_pos = rowItem.get(position);

        // setting the image resource and title
        imgIcon.setImageResource(R.drawable.ic_launcher);
        text_money.setText(Double.toString(row_pos.get_quota()));
        text_source.setText(row_pos.get_source());
        text_date.setText(row_pos.get_time());
        return convertView;
    }


}
