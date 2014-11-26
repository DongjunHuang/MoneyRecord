package com.example.dongjunhuang.moneyrecording;

/**
 * Created by dongjunhuang on 10/8/14.
 */
import com.example.dongjunhuang.supportlib.ListAdapter;
import com.example.dongjunhuang.supportlib.Money_Record;
import com.example.dongjunhuang.supportlib.MySqlOperation;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.*;



public class Fragment_List extends ListFragment {
    private ListAdapter adapter;
    private MySqlOperation database;
    private List<Money_Record> rowItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Exec();
        Log.d("Here", "get here");
    }

    public void Exec(){
        new LoadDataInBackground().execute();
    }

    private class LoadDataInBackground extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params){
            /**create database*/
            database = new MySqlOperation(getActivity());
            database.open();
            rowItem = database.getRecords();
            adapter = new ListAdapter(getActivity(), rowItem);

            database.close();
            return "info";
        }
        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            super.onPostExecute("results");
            setListAdapter(adapter);
        }
    }

}

