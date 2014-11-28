package com.example.dongjunhuang.moneyrecording;

/**
 * Created by dongjunhuang on 10/8/14.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dongjunhuang.Interfaces.Communicator;
import com.example.dongjunhuang.moneytracker.R;
import com.example.dongjunhuang.supportlib.*;

import java.util.Date;
import java.text.SimpleDateFormat;

public class dialog_record_money extends DialogFragment implements View.OnClickListener {
    private Communicator mCallback;
    private Button bt_enter;
    private Button bt_cancel;
    private EditText edit_source;
    private EditText edit_quota;
    private RadioGroup group_type;
    private RadioGroup group_way;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (Communicator) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.money_record_dialog, container);
        bt_enter = (Button)view.findViewById(R.id.button_enter);
        bt_cancel = (Button)view.findViewById(R.id.button_cancel);
        edit_source = (EditText)view.findViewById(R.id.edit_source);
        edit_quota = (EditText)view.findViewById(R.id.edit_money);
        group_type = (RadioGroup)view.findViewById(R.id.group_type);
        group_way = (RadioGroup)view.findViewById(R.id.group_way);
        bt_enter.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_enter:
                MySqlOperation database = new MySqlOperation(getActivity());
                database.open();
                String source = edit_source.getText().toString();
                double quota = 0;
                try {
                    quota = Double.parseDouble(edit_quota.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                String g_type = ((RadioButton) getDialog().findViewById(group_type.getCheckedRadioButtonId())).getText().toString();
                String g_way = ((RadioButton) getDialog().findViewById(group_way.getCheckedRadioButtonId())).getText().toString();

                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = dateFormat.format(now);
                // insert data into sqlite
                Money_Record record = new Money_Record(source, g_type, quota, g_way, date);
                //database.insert(record);
                database.close();
                //mCallback.refresh("Starting");*/
                Toast.makeText(getActivity(), source + " " + quota + " " + g_type + " " + g_way + " " + date, Toast.LENGTH_SHORT).show();

                break;
            case R.id.button_cancel:

                this.dismiss();
                break;
        }
    }

    //singleton
    public static DialogFragment newInstance(){
        DialogFragment dialog = new dialog_record_money();
        return dialog;
    }

}
