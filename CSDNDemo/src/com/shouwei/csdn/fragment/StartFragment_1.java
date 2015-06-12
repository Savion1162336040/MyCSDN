package com.shouwei.csdn.fragment;


import com.shouwei.csdn.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment_1 extends Fragment {

    View view;
    Activity mActivity;
    ImageView iv1;
    RelativeLayout.LayoutParams params;

    @SuppressLint("ValidFragment")
    public StartFragment_1(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_1,null);
        init();
        return view;
    }

    private void init(){
       DisplayMetrics dm = new DisplayMetrics();
       mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
       int height = dm.heightPixels/3*1;
       Log.i("savion", "图片margin高度为 == >"+height);
       iv1 = (ImageView) view.findViewById(R.id.fragment1_iv1);
       params = (LayoutParams) iv1.getLayoutParams();
       params.setMargins(0, height, 0, 0);
       iv1.setLayoutParams(params);
    }
}
