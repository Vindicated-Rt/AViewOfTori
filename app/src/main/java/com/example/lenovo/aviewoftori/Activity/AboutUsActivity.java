package com.example.lenovo.aviewoftori.Activity;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.aviewoftori.R;

public class AboutUsActivity extends AppCompatActivity {

    private TextView poem;

    private TextView html_Vindicated_Rt;

    private TextView html_Vni0427;

    private Toolbar aboutus_toolbar;

    private Typeface Tea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus_activity);

        poem = (TextView) findViewById(R.id.poem_content);

        html_Vindicated_Rt = (TextView) findViewById(R.id.html_Vindicated_Rt);

        html_Vni0427 = (TextView) findViewById(R.id.html_Vni0427);

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

        settype();

        sethtml();
    }

    /*设置字体*/
    public void settype(){

        AssetManager AM = getAssets();

        Tea = Typeface.createFromAsset(AM, "fonts/tea.ttf");

        poem.setTypeface(Tea);

    }

    /*初始化网站*/
    public void sethtml(){

        String html1 = "<a href='https://vindicated-rt.github.io/'>Vindicated-Rt</a>";

        String html2 = "<a href='https://vni0427.github.io/'>Vni0427</a>";

        html_Vindicated_Rt.setMovementMethod(LinkMovementMethod.getInstance());

        html_Vindicated_Rt.setText(Html.fromHtml(html1));

        html_Vni0427.setMovementMethod(LinkMovementMethod.getInstance());

        html_Vni0427.setText(Html.fromHtml(html2));

    }
}
