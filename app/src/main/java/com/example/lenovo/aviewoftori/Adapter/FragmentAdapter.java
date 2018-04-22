package com.example.lenovo.aviewoftori.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.List;


public class FragmentAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener{

    private List<Fragment> fragments;

    private List<String> tiltle;

    /*构造方法*/
    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tiltle) {

        super(fm);

        this.fragments = fragments;

        this.tiltle = tiltle;

    }

    /*标题栏数据位置*/
    public CharSequence getPageTitle(int position) {

        return tiltle.get(position);

    }

    /*获得fragment条目*/
    public Fragment getItem(int position) {

        return fragments.get(position);

    }

    /*获得总共页面数*/
    public int getCount() {
        return fragments.size();
    }

    /*初始化容器内容,将ViewGroup数据加载为适配器item*/
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    /*销毁页面*/
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
