package com.shouwei.csdn.fragment;


import com.shouwei.csdn.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment_3 extends Fragment {

    View view;
    Activity mActivity;
    RelativeLayout.LayoutParams params;
    ImageView iv1;

    @SuppressLint("ValidFragment")
    public StartFragment_3(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_3,null);
        init();
        return view;
    }
    private void init(){
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        iv1 = (ImageView) view.findViewById(R.id.fragment3_iv1);
        params = (RelativeLayout.LayoutParams) iv1.getLayoutParams();
        params.setMargins(0, dm.heightPixels / 3 * 1, 0, 0);
        iv1.setLayoutParams(params);
    }


}
