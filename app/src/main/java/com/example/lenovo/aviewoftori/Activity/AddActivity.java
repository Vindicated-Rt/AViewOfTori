package com.example.lenovo.aviewoftori.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.aviewoftori.R;

/**
 * Created by asus on 2018/4/23.
 */

public class AddActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

    }
   /*public void setToolBar(){

        diary_toolbar = (Toolbar) view.findViewById(R.id.diary_toolbar);

        setSupportActionBar(diary_toolbar);

        diary_toolbar.setTitle(getString(R.string.memo));

        diary_toolbar.setNavigationIcon(R.drawable.home_side);

        diary_toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddActivity.class);

                startActivity(intent);


            }
        });
        }*/




}
