package com.example.lenovo.aviewoftori.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.aviewoftori.Adapter.FragmentAdapter;
import com.example.lenovo.aviewoftori.Fragment.DiaryFragment;
import com.example.lenovo.aviewoftori.Fragment.MemoFragment;
import com.example.lenovo.aviewoftori.Fragment.ToolFragment;
import com.example.lenovo.aviewoftori.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ViewPager home_viewPager;//初始化滑动视图

    private PagerTabStrip home_pagertitle; //初始化标题栏

    private Toolbar home_toolbar;//初始化工具栏

    private List<String> title; //初始化标题字符串集合

    private List<Fragment> fragments;//初始化fragment集合

    private DrawerLayout drawerLayout;//初始化侧滑栏

    private FragmentAdapter fragmentAdapter;//初始化fragment适配器

    private NavigationView side_view;//初始化侧滑栏布局

    private View head_layout;

    private TextView email_et;

    private TextView bio_et;

    private SharedPreferences setting_info;

    private String email;

    private String bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        fragments = new ArrayList<Fragment>();

        title = new ArrayList<String>();

        homefindid();

        setToolbar();

        setViewPager();

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, title);

        home_viewPager.setAdapter(fragmentAdapter);

        home_viewPager.setCurrentItem(1);

    }

    /*实例化对象*/
    public void homefindid() {

        home_viewPager = (ViewPager) findViewById(R.id.home_viewpager);

        home_pagertitle = (PagerTabStrip) findViewById(R.id.home_viewpager_title);

        drawerLayout = (DrawerLayout) findViewById(R.id.home_drawerlayout);

        home_pagertitle.setTabIndicatorColor(getResources().getColor(R.color.main_color));

        side_view = (NavigationView) findViewById(R.id.home_side_layout);

        head_layout = side_view.inflateHeaderView(R.layout.side_headview);

        email_et = (TextView) head_layout.findViewById(R.id.side_head_email_tv);

        bio_et = (TextView) head_layout.findViewById(R.id.side_head_bio_tv);

        /*侧滑栏item点击事件*/
        side_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            Intent intent_AboutUS = new Intent(HomeActivity.this, AboutUsActivity.class);

            Intent intent_setting = new Intent(HomeActivity.this, SettingActivity.class);

            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.side_item_memo:

                        home_viewPager.setCurrentItem(1);

                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;

                    case R.id.side_item_diary:

                        home_viewPager.setCurrentItem(0);

                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;

                    case R.id.side_item_tool:

                        home_viewPager.setCurrentItem(2);

                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;

                    case R.id.side_item_aboutus:

                        startActivity(intent_AboutUS);

                        break;

                    case R.id.side_item_setting:

                        startActivity(intent_setting);

                        break;
                }
                return true;
            }
        });
    }

    /*添加ViewPager的数据源*/
    public void setViewPager() {

        fragments.add(new DiaryFragment());

        fragments.add(new MemoFragment());

        fragments.add(new ToolFragment());

        title.add(getString(R.string.diary));

        title.add(getString(R.string.memo));

        title.add(getString(R.string.tool));
    }

    /*设置toolbar*/
    public void setToolbar() {

        home_toolbar = (Toolbar) findViewById(R.id.home_toolbar);//实例化toolbar

        setSupportActionBar(home_toolbar);//给当前布局添加toolbar

        home_toolbar.setNavigationIcon(R.mipmap.home_side);//设置导航图标

        getSupportActionBar().setTitle("");

        /*导航图标监听点击事件*/
        home_toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                drawerLayout.openDrawer(Gravity.LEFT);//打开侧滑栏
            }
        });
    }

    protected void onResume() {

        super.onResume();

        fragmentAdapter.notifyDataSetChanged();

        setting_info = getSharedPreferences("info", MODE_PRIVATE);

        email = setting_info.getString("email","YourEmail@.com");

        bio = setting_info.getString("bio","your bio");

        email_et.setText(email);

        bio_et.setText(bio);

    }
}
