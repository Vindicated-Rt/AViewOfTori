package com.example.lenovo.aviewoftori.Base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by asus on 2018/4/27.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    /*数据库固定语句*/
    private static final String CREATE_DIARY = "create table Diary ("
            + "id integer primary key autoincrement, "
            + "content text, "
            + "time text, "
            + "image text)";

    private static final String CREATE_MEMO = "create table Memo ("
            + "id integer primary key autoincrement, "
            + "content text, "
            + "time text, "
            + "image text, "
            + "alarm text)";

    private Context mContext;

    /*建表语句*/
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        mContext = context;

    }

    /*执行建表语句*/
    @SuppressLint("ShowToast")
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_DIARY);

        db.execSQL(CREATE_MEMO);

        Toast.makeText(mContext, "create succeeded", Toast.LENGTH_SHORT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists Diary");

        db.execSQL("drop table if exists Memo");

        onCreate(db);
    }
}
