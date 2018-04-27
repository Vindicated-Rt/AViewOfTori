package com.example.lenovo.aviewoftori.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.lenovo.aviewoftori.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemoFragment extends Fragment {

    private List<Map<String,Object>> datalist1;

    private int[] pic_name = {R.mipmap.guideview1,R.mipmap.guideview2};

    private String[] text_info = {"我邓文日天最牛逼", "我皮皮熊最牛逼"};

    private ListView listView;

    private GridView gridView;

    private SimpleAdapter simp1;

    private SimpleAdapter simp2;


    public MemoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.memo_fragment,container,false);

        int [] name1 = {R.id.memo_listview_item_iv,R.id.memo_listview_item_content_tv};

        int [] name2 = {R.id.memo_gridview_item_iv , R.id.memo_gridview_item_content_tv};

        String [] key1 = {"pic1" , "text1"};

        datalist1 = new ArrayList<Map<String,Object>>();

        getData1();

        simp1 = new SimpleAdapter(getContext() , datalist1 , R.layout.item_memo_listview ,key1 , name1);

        simp2 = new SimpleAdapter(getContext() , datalist1 , R.layout.item_memo_gridview , key1 , name2);

        listView = (ListView)view.findViewById(R.id.memo_listview);

        listView.setAdapter(simp1);

        gridView = (GridView)view.findViewById(R.id.memo_gridview);

        gridView.setAdapter(simp2);

        return view;

    }


    private List<Map<String,Object>> getData1(){

        for ( int i = 0 ; i < 2 ; i++ )
        {
            Map<String,Object> map = new HashMap<String, Object>();

            map.put("pic1" , pic_name[i]);

            map.put("text1" , text_info[i]);

            datalist1.add(map);
        }

        return datalist1;
    }

}
