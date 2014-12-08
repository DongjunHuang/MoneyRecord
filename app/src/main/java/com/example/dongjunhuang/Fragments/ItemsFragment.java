package com.example.dongjunhuang.Fragments;

/**
 * Created by dongjunhuang on 10/8/14.
 */
import com.example.dongjunhuang.Interfaces.Communicator;
import com.example.dongjunhuang.Libs.ListAdapter;
import com.example.dongjunhuang.Libs.Money_Record;
import com.example.dongjunhuang.Libs.MySqlOperation;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


import java.util.*;



public class ItemsFragment extends android.support.v4.app.ListFragment {
    private ListAdapter adapter;
    private MySqlOperation database;
    private List<Money_Record> rowItem;
    private Communicator mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Money_Record item = rowItem.get(position);
        mCallback.getData(item);
    }

}

