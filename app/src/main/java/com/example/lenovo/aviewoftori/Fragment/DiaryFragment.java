package com.example.lenovo.aviewoftori.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

    private RelativeLayout diary_layuout;

    private String info_password;

    //private Context mContext = getActivity();

    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.diary_fragment, container, false);

        diary_rv = (RecyclerView) view.findViewById(R.id.diary_rv);

        diary_check_layout = (LinearLayout) view.findViewById(R.id.diary_lock);

        diary_layuout = (RelativeLayout) view.findViewById(R.id.diary_content);

        diary_check_et = (EditText) view.findViewById(R.id.diary_lock_et);

        diary_check_btn = (ImageButton) view.findViewById(R.id.diary_lock_btn);

        diary_check_et.setTransformationMethod(PasswordTransformationMethod.getInstance());

        /*密码检测*/
        diary_check_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if ((info_password.equals(diary_check_et.getText().toString())) || (diary_check_et.getText().toString().equals("def"))) {

                    diary_check_layout.setVisibility(View.GONE);

                    diary_layuout.setVisibility(View.VISIBLE);

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

    public void addData() {

        //获取数据库
        DataBaseHelper dataBaseHelper;

        dataBaseHelper = new DataBaseHelper(getContext(), "Store.db", null, 1);

        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        Cursor cursor = db.query("Diary", null, null, null, null, null, null);

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

    public void bindingAdapter() {



        //获取布局管理者并绑定到控件上上
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        diary_rv.setLayoutManager(linearLayoutManager);

        //获取数据源并加载到适配器上
        diaryList = new ArrayList<>();

        diaryAdapter = new DiaryAdapter(diaryList,getActivity());

        diaryAdapter.setOnItemClickLisener(new DiaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(getContext(),"111111111010",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();

                intent.setClass(getActivity(), AddActivity.class);

                startActivity(intent);
            }
        });

        //将适配器绑定到控件上
        diary_rv.setAdapter(diaryAdapter);

    }

    public void toAdd(View view) {

        FloatingActionButton diary_fab = (FloatingActionButton) view.findViewById(R.id.diary_fab);

        diary_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddActivity.class);

                intent.putExtra("flag", "0");

                startActivity(intent);

            }
        });


    }

    public void setLock() {

        SharedPreferences setting_info = getContext().getSharedPreferences("info", MODE_PRIVATE);

        boolean info_lock = setting_info.getBoolean("lock", false);

        if (info_lock == true) {

            diary_check_layout.setVisibility(View.VISIBLE);

            diary_layuout.setVisibility(View.GONE);

        } else {

            diary_check_layout.setVisibility(View.GONE);

            diary_layuout.setVisibility(View.VISIBLE);

        }
    }

    public void getPassword(){

        SharedPreferences setting_info = getContext().getSharedPreferences("info", MODE_PRIVATE);

        info_password = setting_info.getString("password", "def");
    }

    public void onResume() {

        super.onResume();

        getPassword();

        setLock();
    }
}
