package com.shouwei.csdn.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Window;


import java.util.ArrayList;
import java.util.List;

import com.shouwei.csdn.R;
import com.shouwei.csdn.adapter.StartActivityAdapter;
import com.shouwei.csdn.customview.SelectPointView;
import com.shouwei.csdn.fragment.StartFragment_1;
import com.shouwei.csdn.fragment.StartFragment_2;
import com.shouwei.csdn.fragment.StartFragment_3;
import com.shouwei.csdn.fragment.StartFragment_4;

public class StartActivity extends FragmentActivity {

    ViewPager mViewpager;
    StartFragment_1 sf1;
    StartFragment_2 sf2;
    StartFragment_3 sf3;
    StartFragment_4 sf4;
    FragmentManager fm;
    List<Fragment> fragmentList;
    SelectPointView mSelectPointView;
    int oldI = -1,currentState=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);

        initView();
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.start_activity_viewpager);
        mSelectPointView = (SelectPointView) findViewById(R.id.start_activity_selectpointview);

        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (oldI != i&&currentState == 2) {
                    mSelectPointView.setPosition(i);
                    oldI = i;
                }
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //i有三种状态值，分别表示三种滑动状态
                //1:正在滑动，2：滑动结束，3：没有滑动
                currentState = i;

            }
        });
        mSelectPointView.setOnPointSelectListener(new SelectPointView.OnPointSelectListener() {
            @Override
            public void onPointSelect(int position) {
                mViewpager.setCurrentItem(position);
            }
        });

        initViewPager();

    }

    private void initViewPager() {
        fragmentList = new ArrayList<Fragment>();
        sf1 = new StartFragment_1(this);
        sf2 = new StartFragment_2(this);
        sf3 = new StartFragment_3(this);
        sf4 = new StartFragment_4(this);

        fragmentList.add(sf1);
        fragmentList.add(sf2);
        fragmentList.add(sf3);
        fragmentList.add(sf4);

        fm = getSupportFragmentManager();

        StartActivityAdapter saa = new StartActivityAdapter(fm, fragmentList);
        mViewpager.setAdapter(saa);
    }

}
