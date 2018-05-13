package com.example.lenovo.aviewoftori.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.aviewoftori.Adapter.FragmentAdapter;
import com.example.lenovo.aviewoftori.Fragment.DiaryFragment;
import com.example.lenovo.aviewoftori.Fragment.MemoFragment;
import com.example.lenovo.aviewoftori.Fragment.ToolFragment;
import com.example.lenovo.aviewoftori.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public static final int CHOOSE_PHOTO = 0;

    public static final int SET_DIARY = 1;

    public static final int SET_MEMO = 2;

    public static final int SET_TOOL = 3;

    private ViewPager home_viewPager;//初始化滑动视图

    private PagerTabStrip home_pagertitle; //初始化标题栏

    private Toolbar home_toolbar;//初始化工具栏

    private List<String> title; //初始化标题字符串集合

    private List<Fragment> fragments;//初始化fragment集合

    private DrawerLayout drawerLayout;//初始化侧滑栏

    private FragmentAdapter fragmentAdapter;//初始化fragment适配器

    private NavigationView side_view;//初始化侧滑栏布局

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

        /*侧滑栏item点击事件*/
        side_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            Intent intent_AboutUS = new Intent(HomeActivity.this, AboutUsActivity.class);

            Intent intent_setting = new Intent(HomeActivity.this, SettingActivity.class);

            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

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

    /*布置toolbar布局*/
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_toolbar_menu, menu);

        return true;
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

    /*toolbar的item点击事件*/
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment memo_fragment = (Fragment) fragmentAdapter.getCurrentFragment();

        ListView memo_listview = (ListView) memo_fragment.getView().findViewById(R.id.memo_listview);

        GridView memo_gridview = (GridView) memo_fragment.getView().findViewById(R.id.memo_gridview);

        switch (item.getItemId()) {

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

    /*以viewpager页卡数控制toolbar状态*/
    public boolean onPrepareOptionsMenu(Menu menu) {

        Fragment memo_fragment = (Fragment) fragmentAdapter.getCurrentFragment();

        ListView memo_listview = (ListView) memo_fragment.getView().findViewById(R.id.memo_listview);

        switch (home_viewPager.getCurrentItem()) {

            case 0:

                new Thread(new Runnable() {

                    public void run() {

                        Message message = new Message();

                        message.what = SET_DIARY;

                        handler.sendMessage(message);

                    }
                }).start();

                break;

            case 1:

                new Thread(new Runnable() {

                    public void run() {

                        Message message = new Message();

                        message.what = SET_MEMO;

                        handler.sendMessage(message);

                    }
                }).start();

                if (memo_listview.getVisibility() == View.VISIBLE) {

                    home_toolbar.getMenu().findItem(R.id.toolbar_list_btn).setVisible(true);

                    home_toolbar.getMenu().findItem(R.id.toolbar_gird_btn).setVisible(false);

                } else {

                    home_toolbar.getMenu().findItem(R.id.toolbar_list_btn).setVisible(false);

                    home_toolbar.getMenu().findItem(R.id.toolbar_gird_btn).setVisible(true);
                }

                break;

            case 2:

                new Thread(new Runnable() {

                    public void run() {

                        Message message = new Message();

                        message.what = SET_TOOL;

                        handler.sendMessage(message);

                    }
                }).start();

                break;

        }

        return super.onPrepareOptionsMenu(menu);
    }

    /*线程控制刷新toolbar*/
    private Handler handler = new Handler() {

        public void handleMessage(Message msg){

            switch (msg.what){

                case SET_DIARY:

                    home_toolbar.getMenu().findItem(R.id.toolbar_list_btn).setVisible(false);

                    home_toolbar.getMenu().findItem(R.id.toolbar_gird_btn).setVisible(false);

                    break;

                case SET_MEMO:

                    break;

                case SET_TOOL:

                    home_toolbar.getMenu().findItem(R.id.toolbar_list_btn).setVisible(false);

                    home_toolbar.getMenu().findItem(R.id.toolbar_gird_btn).setVisible(false);
                    break;

                default:

                    break;
            }

        }

    };

    protected void onResume() {

        super.onResume();

        fragmentAdapter.notifyDataSetChanged();
    }
}
