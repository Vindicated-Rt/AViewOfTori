package com.example.lenovo.aviewoftori.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        fragments = new ArrayList<Fragment>();

        title = new ArrayList<String>();

        homefindid();

        setToolbar();

        FragmentAdapter home_adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, title);

        setViewPager();

        home_viewPager.setAdapter(home_adapter);

        home_viewPager.setCurrentItem(1);

        /*viewPager滑动监听事件*/
        home_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            public void onPageSelected(int position) {

                switch (position) {

                    case 0:

                        getSupportActionBar().setTitle(getString(R.string.diary));

                        home_toolbar.getMenu().findItem(R.id.toolbar_list_grid_btn).setVisible(false);

                        break;

                    case 1:

                        getSupportActionBar().setTitle(getString(R.string.memo));

                        home_toolbar.getMenu().findItem(R.id.toolbar_list_grid_btn).setVisible(true);

                        break;

                    case 2:

                        getSupportActionBar().setTitle(getString(R.string.tool));

                        home_toolbar.getMenu().findItem(R.id.toolbar_list_grid_btn).setVisible(false);

                        break;
                }
            }


            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /*实例化对象*/
    public void homefindid() {

        home_viewPager = (ViewPager) findViewById(R.id.home_viewpager);

        home_pagertitle = (PagerTabStrip) findViewById(R.id.home_viewpager_title);

        drawerLayout = (DrawerLayout) findViewById(R.id.home_drawerlayout);

    }

    /*设置toolbar*/
    public void setToolbar() {

        home_toolbar = (Toolbar) findViewById(R.id.home_toolbar);//实例化toolbar

        setSupportActionBar(home_toolbar);//给当前布局添加toolbar

        home_toolbar.setNavigationIcon(R.mipmap.home_side);//设置导航图标

        /*导航图标监听点击事件*/
        home_toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                drawerLayout.openDrawer(Gravity.LEFT);//打开侧滑栏
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

    /*布置toolbar布局*/
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

}
