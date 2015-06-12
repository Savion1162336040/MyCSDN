package com.shouwei.csdn.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * ��ҳ��Fragment��adapter
 * Created by sw on 2015/6/2.
 */
public class StartActivityAdapter extends FragmentPagerAdapter {

    FragmentManager fm;
    List<Fragment> list;

    public StartActivityAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fm = fm;
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }
}
