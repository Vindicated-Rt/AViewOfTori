package com.example.lenovo.aviewoftori.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lenovo.aviewoftori.Adapter.GuideAdapter;
import com.example.lenovo.aviewoftori.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private List<View> viewList;

    private GuideAdapter guideAdapter;

    private ViewPager guide_vp;

    private Button guide_btn_start;

    private ImageView[] dots;

    private int[] ids = {R.id.guide_iv_1, R.id.guide_iv_2, R.id.guide_iv_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_activity);

        initview();

        initdots();

        start();
    }

    /*跳转主页面*/
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void start() {

        guide_btn_start = (Button) viewList.get(2).findViewById(R.id.guide_btn_start);

        //guide_btn_start.setAlpha(1/2);

        guide_btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GuideActivity.this, HomeActivity.class);

                startActivity(intent);

                finish();

            }
        });

    }

    public void initview() {
         /*将layout转为view对象，并将对象添加到List<View>中，作为适配器数据源*/
        View view1 = View.inflate(this, R.layout.guide_view1, null);

        View view2 = View.inflate(this, R.layout.guide_view2, null);

        View view3 = View.inflate(this, R.layout.guide_view3, null);

        viewList = new ArrayList<View>();

        viewList.add(view1);

        viewList.add(view2);

        viewList.add(view3);

        /*适配器加载数据源*/
        guideAdapter = new GuideAdapter(viewList);

        /*控件绑定适配器*/
        guide_vp = (ViewPager) findViewById(R.id.guide_vp);

        guide_vp.setAdapter(guideAdapter);

        guide_vp.addOnPageChangeListener(this);
    }

    public void initdots() {

        dots = new ImageView[viewList.size()];

        for (int i = 0; i < viewList.size(); i++) {

            dots[i] = (ImageView) findViewById(ids[i]);

        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /*更换页面小圆点的明亮*/
    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i < viewList.size(); i++) {

            if (position == i) {

                dots[i].setImageResource(R.mipmap.guidecirclelight);
            } else {
                dots[i].setImageResource(R.mipmap.guidecircledark);
            }

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
