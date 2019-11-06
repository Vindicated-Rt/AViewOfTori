package com.mystery.aviewoftori.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by asus on 2018/4/22.
 */

public class GuideAdapter extends PagerAdapter {

    private List<View> viewList;

    public GuideAdapter(List<View> viewList) {

        this.viewList = viewList;

    }

    @Override
    public int getCount() {

        return viewList.size();

    }

    /*判断view是否由对象产生*/
    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;

    }

    /*实例化*/
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(viewList.get(position));//将view添加到ViewGroup中

        return viewList.get(position);

    }

    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(viewList.get(position));//销毁页卡

    }


}
