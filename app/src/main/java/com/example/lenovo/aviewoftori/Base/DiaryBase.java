package com.example.lenovo.aviewoftori.Base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by asus on 2018/4/27.
 */

public class DiaryBase extends SQLiteOpenHelper {

    /*数据库固定语句*/
    public static final String CREATE_DIARY = "create table Diary ("
            + "id integer primary key autoincrement, "
            + "content text, "
            + "time text, "
            + "image text)";

    private Context mContext;

    /*建表语句*/
    public DiaryBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        mContext = context;

    }

    /*执行建表语句*/
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_DIARY);

        Toast.makeText(mContext,"create succeeded",Toast.LENGTH_SHORT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
