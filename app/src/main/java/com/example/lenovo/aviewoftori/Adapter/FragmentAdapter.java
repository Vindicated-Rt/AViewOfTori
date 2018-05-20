package com.example.lenovo.aviewoftori.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.List;


public class FragmentAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

    private List<Fragment> fragments;

    private List<String> tiltle;

    private Fragment pagerFrament;

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

    /*设置当前Fragment方法*/
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        pagerFrament = (Fragment) object;
        super.setPrimaryItem(container, position, object);
    }

    /*获取当前Fragment方法*/
    public Fragment getCurrentFragment() {
        return pagerFrament;
    }


    public int getItemPosition(Object object) {
        /*当主视图试图确定某个项目的位置是否已更改时调用。
        * 如果给定项目的位置未更改，则返回{@link #POSITION_UNCHANGED};
        * 如果该项目不再存在于适配器中，则返回{@link #POSITION_NONE}。
        * 这是PagerAdapter类中的成员
        * */
        return POSITION_NONE;
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
