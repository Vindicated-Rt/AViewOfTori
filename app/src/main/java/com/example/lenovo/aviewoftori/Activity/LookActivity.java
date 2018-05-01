package com.example.lenovo.aviewoftori.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lenovo.aviewoftori.R;

/**
 * Created by asus on 2018/4/29.
 */

public class LookActivity extends AppCompatActivity {

    private FloatingActionButton look_fab;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_activity);

        look_fab = (FloatingActionButton) findViewById(R.id.look_fab);

        look_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LookActivity.this,AddActivity.class);

            }
        });

    }
}
