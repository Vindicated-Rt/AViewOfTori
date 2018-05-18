package com.example.lenovo.aviewoftori.Fragment;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.lenovo.aviewoftori.Activity.AddActivity;
import com.example.lenovo.aviewoftori.Adapter.MemoGridAdapter;
import com.example.lenovo.aviewoftori.Adapter.MemoListAdapter;
import com.example.lenovo.aviewoftori.Base.DataBaseHelper;
import com.example.lenovo.aviewoftori.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemoFragment extends Fragment {

    private ListView memo_listView;

    private GridView memo_gridView;

    private MemoListAdapter memoListAdapter;

    private MemoGridAdapter memoGridAdapter;

    private DataBaseHelper dataBaseHelper;

    private SQLiteDatabase dbReader;

    private Cursor cursor;

    public MemoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.memo_fragment, container, false);

        dataBaseHelper = new DataBaseHelper(getContext(), "Store.db", null, 1);

        dbReader = dataBaseHelper.getReadableDatabase();

        memo_listView = (ListView) view.findViewById(R.id.memo_listview);

        memo_gridView = (GridView) view.findViewById(R.id.memo_gridview);

        addData();

        toAdd(view);

        return view;

    }

    /*跳转到添加页面*/
    public void toAdd(View view) {

        FloatingActionButton diary_fab = (FloatingActionButton) view.findViewById(R.id.memo_fab);

        diary_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddActivity.class);

                intent.putExtra("flag", "1");

                startActivity(intent);

            }
        });


    }

    /*获取数据方法*/
    public void addData() {

        /*初始化光标,限制逆序移动*/
        cursor = dbReader.query("Memo", null, null, null, null, null, "id desc");

        memoListAdapter = new MemoListAdapter(getContext(), cursor);

        memoGridAdapter = new MemoGridAdapter(getContext(), cursor);

        memo_listView.setAdapter(memoListAdapter);

        memo_gridView.setAdapter(memoGridAdapter);
    }


}
