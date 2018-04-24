package com.example.lenovo.aviewoftori.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.aviewoftori.Adapter.DiaryAdapter;
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

         List<Diary>diaryList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        diary_rv.setLayoutManager(linearLayoutManager);

        DiaryAdapter diaryAdapter = new DiaryAdapter(diaryList);

        diary_rv.setAdapter(diaryAdapter);

        addData();

        // Inflate the layout for this fragment
        return view;
    }

    /*添加数据*/
    public void addData(){



    }

}
