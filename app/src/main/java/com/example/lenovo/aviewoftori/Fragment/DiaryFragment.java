package com.example.lenovo.aviewoftori.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.aviewoftori.Activity.AddActivity;
import com.example.lenovo.aviewoftori.Adapter.DiaryAdapter;
import com.example.lenovo.aviewoftori.Base.DiaryBase;
import com.example.lenovo.aviewoftori.Other.Diary;
import com.example.lenovo.aviewoftori.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment {

    private RecyclerView diary_rv;

    //private List<Diary>diaryList;


    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.diary_fragment, container, false);

        diary_rv = (RecyclerView) view.findViewById(R.id.diary_rv);

        //创建数据库
        createBase();

        //加载适配器
        bindingAdapter();

        //添加数据
        addData();

        //跳转添加界面,另为了view内获取控件，需传入view
        toAdd(view);

        // Inflate the layout for this fragment
        return view;
    }

    private void createBase() {

        DiaryBase diaryBase;

        diaryBase = new DiaryBase(getContext(),"DiaryStore.db",null,1);

        diaryBase.getReadableDatabase();




    }

    private void bindingAdapter() {

        //获取布局管理者并绑定到控件上上
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        diary_rv.setLayoutManager(linearLayoutManager);

        //获取数据源并加载到适配器上
        List<Diary>diaryList = new ArrayList<>();

        DiaryAdapter diaryAdapter = new DiaryAdapter(diaryList);

        //将适配器绑定到控件上
        diary_rv.setAdapter(diaryAdapter);

        addData();

        return view;
    }

    /*添加数据*/
    public void addData(){

    }

    public void toAdd(View view){

        FloatingActionButton dairy_fab = (FloatingActionButton)view.findViewById(R.id.diary_fab);

        dairy_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(getActivity(), AddActivity.class);

                startActivity(intent);

            }
        });


    }

}
