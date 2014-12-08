package com.example.dongjunhuang.Fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dongjunhuang.Libs.Money_Record;
import com.example.dongjunhuang.moneytracker.R;

/**
 * Created by dongjunhuang on 12/4/14.
 */
public class DetailFragment extends DialogFragment implements View.OnClickListener{
    private Money_Record record;
    private Button bt_enter;
    private EditText text_source;
    private EditText text_mount;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container);
        bt_enter = (Button)view.findViewById(R.id.detail_enter);
        text_source = (EditText)view.findViewById(R.id.detail_editText_source);
        text_mount = (EditText)view.findViewById(R.id.detail_editText_mount);
        bt_enter.setOnClickListener(this);
        Toast.makeText(getActivity(), record.get_time(), Toast.LENGTH_SHORT).show();
        return view;
    }
    //singleton
    public static DetailFragment newInstance(){
        DetailFragment detail = new DetailFragment();
        return detail;
    }

    //pass record
    public void passRecord(Money_Record record){
        this.record = record;
    }

    @Override
    public void onClick(View v) {
    }
}
