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
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dongjunhuang.Interfaces.Communicator;
import com.example.dongjunhuang.moneytracker.R;
import com.example.dongjunhuang.supportlib.*;

import java.util.Date;
import java.text.SimpleDateFormat;

public class dialog_record_money extends DialogFragment {
    private Communicator mCallback;

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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View view= getActivity().getLayoutInflater().inflate(R.layout.money_record_dialog, null);

        /**create listening event objects*/
        final EditText edit_source = (EditText)view.findViewById(R.id.edit_source);
        final EditText edit_quota = (EditText)view.findViewById(R.id.edit_money);
        final RadioGroup group_type = (RadioGroup)view.findViewById(R.id.group_type);
        final RadioGroup group_way = (RadioGroup)view.findViewById(R.id.group_way);
        builder.setView(view).setPositiveButton(R.string.Enter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /**Open database*/
                MySqlOperation database = new MySqlOperation(getActivity());
                database.open();

                /**get corresponding info from view*/
                String source = edit_source.getText().toString();
                double quota = 0;
                try {
                    quota = Double.parseDouble(edit_quota.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                String g_type = ((RadioButton) view.findViewById(group_type.getCheckedRadioButtonId())).getText().toString();
                String g_way = ((RadioButton) view.findViewById(group_way.getCheckedRadioButtonId())).getText().toString();

                /**Set date format*/
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = dateFormat.format(now);
                /** insert data into sqlite*/
                Money_Record record = new Money_Record(source, g_type, quota, g_way, date);
                database.insert(record);
                database.close();
                mCallback.refresh("Starting");
                Toast.makeText(getActivity(), source + " " + quota + " " + g_type + " " + g_way + " " + date, Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }

    //singleton
    public static DialogFragment newInstance(){
        DialogFragment dialog = new dialog_record_money();
        return dialog;
    }

}
