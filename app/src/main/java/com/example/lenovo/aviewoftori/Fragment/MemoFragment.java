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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.lenovo.aviewoftori.Activity.AddActivity;
import com.example.lenovo.aviewoftori.Adapter.MemoGridAdapter;
import com.example.lenovo.aviewoftori.Adapter.MemoListAdapter;
import com.example.lenovo.aviewoftori.Base.DataBaseHelper;
import com.example.lenovo.aviewoftori.R;

public class MemoFragment extends Fragment {

    private ListView memo_listView;

    private GridView memo_gridView;

    private MemoListAdapter memoListAdapter;

    private MemoGridAdapter memoGridAdapter;

    private DataBaseHelper dataBaseHelper;

    private SQLiteDatabase dbReader;

    private Cursor cursor;

    private Intent item;

    public MemoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        item = new Intent(getActivity(),AddActivity.class);

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.memo_fragment, container, false);

        dataBaseHelper = new DataBaseHelper(getContext(), "Store.db", null, 1);

        dbReader = dataBaseHelper.getReadableDatabase();

        memo_listView = (ListView) view.findViewById(R.id.memo_listview);

        memo_gridView = (GridView) view.findViewById(R.id.memo_gridview);

        FloatingActionButton memo_fab = (FloatingActionButton) view.findViewById(R.id.memo_fab);

        /*按钮长按改变形式*/
        memo_fab.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {

                if(memo_listView.getVisibility()==View.VISIBLE){

                    memo_listView.setVisibility(View.GONE);

                    memo_gridView.setVisibility(View.VISIBLE);

                }else {

                    memo_listView.setVisibility(View.VISIBLE);

                    memo_gridView.setVisibility(View.GONE);

                }

                return true;
            }
        });

        /*LisView点击事件*/
        memo_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cursor.moveToPosition(position);

                item.putExtra("flag","memo");

                item.putExtra("id",cursor.getInt(cursor.getColumnIndex("id")));

                item.putExtra("content",cursor.getString(cursor.getColumnIndex("content")));

                item.putExtra("time",cursor.getString(cursor.getColumnIndex("time")));

                item.putExtra("image",cursor.getString(cursor.getColumnIndex("image")));

                startActivity(item);

            }
        });

        /*GridView点击事件*/
        memo_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cursor.moveToPosition(position);

                Intent item = new Intent(getActivity(),AddActivity.class);

                item.putExtra("flag","memo");

                item.putExtra("id",cursor.getInt(cursor.getColumnIndex("id")));

                item.putExtra("content",cursor.getString(cursor.getColumnIndex("content")));

                item.putExtra("time",cursor.getString(cursor.getColumnIndex("time")));

                item.putExtra("image",cursor.getString(cursor.getColumnIndex("image")));

                startActivity(item);

            }
        });

        addData();

        toAdd(view);

        return view;

    }

    /*跳转到添加页面*/
    public void toAdd(View view) {

        FloatingActionButton memo_fab = (FloatingActionButton) view.findViewById(R.id.memo_fab);

        memo_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item.putExtra("flag", "1");

                startActivity(item);

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
