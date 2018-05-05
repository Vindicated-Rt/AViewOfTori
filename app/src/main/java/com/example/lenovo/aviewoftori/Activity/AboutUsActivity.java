package com.example.lenovo.aviewoftori.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.aviewoftori.R;

public class AboutUsActivity extends AppCompatActivity {

    private TextView aboutus_tv;

    private Toolbar aboutus_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus_activity);

        aboutus_tv = (TextView)findViewById(R.id.aboutus_tv);

        aboutus_tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        aboutus_toolbar = (Toolbar) findViewById(R.id.aboutus_toolbar);

        setSupportActionBar(aboutus_toolbar);

        getSupportActionBar().setTitle(getString(R.string.aboutus));

        /*设置返回图标*/
        aboutus_toolbar.setNavigationIcon(R.mipmap.back);

        /*导航图标监听事件*/
        aboutus_toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                finish();
            }
        });
    }
}
