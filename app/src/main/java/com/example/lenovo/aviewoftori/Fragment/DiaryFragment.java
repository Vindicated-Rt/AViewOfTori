package com.example.lenovo.aviewoftori.Fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lenovo.aviewoftori.Activity.AddActivity;
import com.example.lenovo.aviewoftori.Adapter.DiaryAdapter;
import com.example.lenovo.aviewoftori.Base.DataBaseHelper;
import com.example.lenovo.aviewoftori.Other.Diary;
import com.example.lenovo.aviewoftori.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment {

    private RecyclerView diary_rv;

    private String content;

    private String time;

    private String image;

    private List<Diary> diaryList;

    private DiaryAdapter diaryAdapter;

    private EditText diary_check_et;

    private ImageButton diary_check_btn;

    private LinearLayout diary_check_layout;

    private RelativeLayout diary_layout;

    private String info_password;

    private DataBaseHelper dataBaseHelper;

    private SQLiteDatabase db;

    private SQLiteDatabase dbWriter;

    private Cursor cursor;

    private Intent item;

    private AlertDialog deleteData;

    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.diary_fragment, container, false);

        //获取数据库
        dataBaseHelper = new DataBaseHelper(getContext(), "Store.db", null, 1);

        db = dataBaseHelper.getReadableDatabase();

        cursor = db.query("Diary", null, null, null, null, null, null);

        item = new Intent(getActivity(), AddActivity.class);

        diary_rv = (RecyclerView) view.findViewById(R.id.diary_rv);

        diary_check_layout = (LinearLayout) view.findViewById(R.id.diary_lock);

        diary_layout = (RelativeLayout) view.findViewById(R.id.diary_content);

        diary_check_et = (EditText) view.findViewById(R.id.diary_lock_et);

        diary_check_btn = (ImageButton) view.findViewById(R.id.diary_lock_btn);

        diary_check_et.setTransformationMethod(PasswordTransformationMethod.getInstance());

        /*密码检测*/
        diary_check_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if ((info_password.equals(diary_check_et.getText().toString())) || (diary_check_et.getText().toString().equals("def"))) {

                    diary_check_layout.setVisibility(View.GONE);

                    diary_layout.setVisibility(View.VISIBLE);

                    Toast.makeText(getActivity(), "welcome!", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getActivity(), "密码错误", Toast.LENGTH_SHORT).show();

                }

            }
        });

        //加载适配器
        bindingAdapter();

        //跳转添加界面,另为了view内获取控件，需传入view
        toAdd(view);

        //添加数据*
        addData();

        return view;
    }

    //加载数据库资源到数据源中
    public void addData() {

        if (cursor.moveToFirst()) {
            do {
                //加载数据库资源到数据源中
                content = cursor.getString(cursor.getColumnIndex("content"));

                time = cursor.getString(cursor.getColumnIndex("time"));

                image = cursor.getString(cursor.getColumnIndex("image"));

                diaryList.add(0, new Diary(content, time, image));

            } while (cursor.moveToNext());
        }

        cursor.close();

    }

    /*设置适配器*/
    public void bindingAdapter() {
        //设置item固定大小
        diary_rv.setHasFixedSize(true);

        //获取布局管理者并绑定到控件上上
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        diary_rv.setLayoutManager(linearLayoutManager);

        //获取数据源并加载到适配器上
        diaryList = new ArrayList<>();

        diaryAdapter = new DiaryAdapter(diaryList, getActivity());

        diaryAdapter.setOnItemClickListener(new DiaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

                dataBaseHelper = new DataBaseHelper(getContext(), "Store.db", null, 1);

                db = dataBaseHelper.getReadableDatabase();

                cursor = db.query("Diary", null, null, null, null, null, "id desc");

                cursor.moveToPosition(postion);

                item.setClass(getActivity(), AddActivity.class);

                item.putExtra("flag", "diary");

                item.putExtra("id", cursor.getInt(cursor.getColumnIndex("id")));

                item.putExtra("content", cursor.getString(cursor.getColumnIndex("content")));

                item.putExtra("time", cursor.getString(cursor.getColumnIndex("time")));

                item.putExtra("image", cursor.getString(cursor.getColumnIndex("image")));

                startActivity(item);
            }
        });

        diaryAdapter.setOnItemLongClickListener(new DiaryAdapter.OnItemLongClickListener() {

            public boolean onItemLongClick(View view, int postion) {

                deleteDailog(postion);

                return true;
            }
        });

        //将适配器绑定到控件上
        diary_rv.setAdapter(diaryAdapter);

    }

    /*跳转到添加页面*/
    public void toAdd(View view) {

        FloatingActionButton diary_fab = (FloatingActionButton) view.findViewById(R.id.diary_fab);

        diary_fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                item.putExtra("flag", "0");

                startActivity(item);

            }
        });


    }

    /*删除dialog*/
    public void deleteDailog(int position) {

        dbWriter = dataBaseHelper.getWritableDatabase();

        cursor = dbWriter.query("Diary", null, null, null, null, null, "id desc");

        deleteData = new AlertDialog.Builder(getActivity()).create();

        @SuppressLint("InflateParams") View dialogView = LayoutInflater.from(getActivity())
                .inflate(R.layout.delete_dialog, null);

        deleteData.setTitle("确认删除");

        deleteData.setView(dialogView);

        deleteData.setIcon(R.mipmap.dialog_delete_icon);

        deleteData.setCancelable(false);

        ImageButton deletedata_dialog_ok_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_ok_btn);

        ImageButton deletedata_dialog_cancedl_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_cancel_btn);

        cursor.moveToPosition(position);

        deletedata_dialog_ok_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                dbWriter.delete("Diary", "id=" + cursor.getInt(cursor.getColumnIndex("id")), null);

                cursor = dbWriter.query("Diary", null, null, null, null, null, null);

                diaryList = new ArrayList<>();

                bindingAdapter();

                addData();

                deleteData.cancel();
            }
        });

        deletedata_dialog_cancedl_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                deleteData.cancel();

            }
        });

        deleteData.show();
    }

    /*设置密码*/
    public void setLock() {

        SharedPreferences setting_info = getContext().getSharedPreferences("info", MODE_PRIVATE);

        boolean info_lock = setting_info.getBoolean("lock", false);

        if (info_lock) {

            diary_check_layout.setVisibility(View.VISIBLE);

            diary_layout.setVisibility(View.GONE);

        } else {

            diary_check_layout.setVisibility(View.GONE);

            diary_layout.setVisibility(View.VISIBLE);

        }
    }

    /*从数据库获取密码*/
    public void getPassword() {

        SharedPreferences setting_info = getContext().getSharedPreferences("info", MODE_PRIVATE);

        info_password = setting_info.getString("password", "def");
    }

    public void onResume() {

        super.onResume();

        getPassword();

        setLock();
    }
}
