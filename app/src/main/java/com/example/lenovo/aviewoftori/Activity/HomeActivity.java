package com.example.lenovo.aviewoftori.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.example.lenovo.aviewoftori.Adapter.FragmentAdapter;
import com.example.lenovo.aviewoftori.Base.DataBaseHelper;
import com.example.lenovo.aviewoftori.Fragment.DiaryFragment;
import com.example.lenovo.aviewoftori.Fragment.MemoFragment;
import com.example.lenovo.aviewoftori.Fragment.ToolFragment;
import com.example.lenovo.aviewoftori.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager home_viewPager;//初始化滑动视图

    private PagerTabStrip home_pagertitle; //初始化标题栏

    private Toolbar home_toolbar;//初始化工具栏

    private List<String> title; //初始化标题字符串集合

    private List<Fragment> fragments;//初始化fragment集合

    private DrawerLayout drawerLayout;//初始化侧滑栏

    private FragmentAdapter home_adapter;//初始化fragment适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        fragments = new ArrayList<Fragment>();

        title = new ArrayList<String>();

        homefindid();

        setToolbar();

        /*创建数据库，建两张表*/
        createBase();

        home_adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, title);

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

                        home_toolbar.getMenu().findItem(R.id.toolbar_list_btn).setVisible(false);

                        home_toolbar.getMenu().findItem(R.id.toolbar_gird_btn).setVisible(false);

                        break;

                    case 1:

                        getSupportActionBar().setTitle(getString(R.string.memo));

                        home_toolbar.getMenu().findItem(R.id.toolbar_list_btn).setVisible(true);

                        home_toolbar.getMenu().findItem(R.id.toolbar_gird_btn).setVisible(false);

                        break;

                    case 2:

                        getSupportActionBar().setTitle(getString(R.string.tool));

                        home_toolbar.getMenu().findItem(R.id.toolbar_list_btn).setVisible(false);

                        home_toolbar.getMenu().findItem(R.id.toolbar_gird_btn).setVisible(false);

                        break;
                }
            }


            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /*创建数据库*/
    private void createBase() {

        DataBaseHelper dataBaseHelper;

        dataBaseHelper = new DataBaseHelper(this,"Store.db",null,1);

        dataBaseHelper.getReadableDatabase();

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

    /*item点击事件*/
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment memo_fragment = (Fragment) home_adapter.getCurrentFragment();

        ListView memo_listview = (ListView) memo_fragment.getView().findViewById(R.id.memo_listview);

        GridView memo_gridview = (GridView) memo_fragment.getView().findViewById(R.id.memo_gridview);

        switch (item.getItemId()){

            case R.id.toolbar_list_btn:

                home_toolbar.getMenu().findItem(R.id.toolbar_list_btn).setVisible(false);

                home_toolbar.getMenu().findItem(R.id.toolbar_gird_btn).setVisible(true);

                memo_listview.setVisibility(View.GONE);

                memo_gridview.setVisibility(View.VISIBLE);

                break;

            case R.id.toolbar_gird_btn:

                home_toolbar.getMenu().findItem(R.id.toolbar_list_btn).setVisible(true);

                home_toolbar.getMenu().findItem(R.id.toolbar_gird_btn).setVisible(false);

                memo_listview.setVisibility(View.VISIBLE);

                memo_gridview.setVisibility(View.GONE);

                break;

        }

        return super.onOptionsItemSelected(item);
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

        getMenuInflater().inflate(R.menu.home_toolbar_menu, menu);

        return true;
    }

    /*页卡切换设置*/
    public void onPageSelected(int position) {

        Fragment memo_fragment = (Fragment) home_adapter.getCurrentFragment();

        ListView memo_listview = (ListView) memo_fragment.getView().findViewById(R.id.memo_listview);

        GridView memo_gridview = (GridView) memo_fragment.getView().findViewById(R.id.memo_gridview);

        switch (position) {
            case 0:

                break;

            case 1:

                home_toolbar.getMenu().findItem(R.id.toolbar_list_btn).setVisible(true);

                home_toolbar.getMenu().findItem(R.id.toolbar_gird_btn).setVisible(false);

                memo_listview.setVisibility(View.VISIBLE);

                memo_gridview.setVisibility(View.GONE);

                break;

            case 2:

                break;
        }
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageScrollStateChanged(int state) {

    }
}
