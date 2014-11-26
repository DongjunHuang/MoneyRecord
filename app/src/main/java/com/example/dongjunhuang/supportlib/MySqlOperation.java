package com.example.dongjunhuang.supportlib;

/**
 * Created by dongjunhuang on 10/9/14.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhouhick on 6/15/14.
 */
public class MySqlOperation {
    //Database field
    private SQLiteDatabase database;
    private MySQLiteHelper dbhelper;
    private String[] allCol = {MySQLiteHelper.MONEY_ID,
            MySQLiteHelper.MONEY_SOURCE,
            MySQLiteHelper.MONEY_SEND,
            MySQLiteHelper.MONEY_TYPE,
            MySQLiteHelper.MONEY_QUOTA,
            MySQLiteHelper.MONEY_TIME};

    //constructor
    public MySqlOperation(Context context){
        dbhelper = new MySQLiteHelper(context);

    }

    /**open and close*/
    public void close(){
        dbhelper.close();
    }
    public void open(){database = dbhelper.getWritableDatabase();}

    //insert a row into the database
    public void insert(Money_Record record){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.MONEY_SEND, record.get_way());
        values.put(MySQLiteHelper.MONEY_QUOTA, record.get_quota());
        values.put(MySQLiteHelper.MONEY_SOURCE, record.get_source());
        values.put(MySQLiteHelper.MONEY_TYPE, record.get_type());
        values.put(MySQLiteHelper.MONEY_TIME, record.get_time());
        database.insert(MySQLiteHelper.TABLE_NAME, null, values);
    }

    //get all rows from the database
    public List<Money_Record> getRecords(){
        List<Money_Record> records = new ArrayList<Money_Record>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, allCol, null, null, null, null, null);
        cursor.moveToLast();
        while(!cursor.isBeforeFirst()){
            Money_Record record = new Money_Record();
            record.set_id(cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.MONEY_ID)));
            record.set_source(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MONEY_SOURCE)));
            record.set_way(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MONEY_SEND)));
            record.set_type(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MONEY_TYPE)));
            record.set_quota(cursor.getDouble(cursor.getColumnIndexOrThrow(MySQLiteHelper.MONEY_QUOTA)));
            record.set_time(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MONEY_TIME)));
            records.add(record);
            cursor.moveToPrevious();
        }
        Log.d("size", Integer.toString(records.size()));
        return records;

    }
}

