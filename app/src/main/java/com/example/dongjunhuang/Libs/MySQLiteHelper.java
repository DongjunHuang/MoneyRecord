package com.example.dongjunhuang.Libs;

/**
 * Created by dongjunhuang on 10/9/14.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{
    //general information
    public static final String TABLE_NAME = "MONEY_TABLE";
    public static final String DATABASE_NAME = "MONEY_DB";
    public static final int DATABASE_VERSION = 1;

    //columns
    public static final String MONEY_ID = "MONEY_ID";
    public static final String MONEY_SOURCE = "MONEY_SOURCE";
    public static final String MONEY_TYPE = "MONEY_TYPE";
    public static final String MONEY_QUOTA = "MONEY_QUOTA";
    public static final String MONEY_TIME = "MONEY_TIME";
    public static final String MONEY_SEND = "MONEY_SEND";


    public static final String CTEATE_TABLE = "create table " + TABLE_NAME +
            "("  + MONEY_ID + " integer primary key autoincrement, " +
            MONEY_SOURCE + " varchar(100), " +
            MONEY_TYPE + " varchar(100), " +
            MONEY_SEND + " varchar(100), " +
            MONEY_QUOTA + " double not null, " +
            MONEY_TIME + " varchar(100) not null);";

    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CTEATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
}
