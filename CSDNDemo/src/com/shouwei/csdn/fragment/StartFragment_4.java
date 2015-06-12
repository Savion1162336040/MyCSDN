package com.shouwei.csdn.fragment;

import com.shouwei.csdn.R;
import com.shouwei.csdn.activity.LoginActivity;
import com.shouwei.csdn.activity.MainActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment_4 extends Fragment {

	View view;
	Activity mActivity;
	ImageView iv1, iv2;
	LayoutParams params, params2;

	@SuppressLint("ValidFragment")
	public StartFragment_4(Activity mActivity) {
		this.mActivity = mActivity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(mActivity)
				.inflate(R.layout.fragment_4, null);
		init();
		return view;
	}

	private void init() {
		DisplayMetrics dm = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		iv1 = (ImageView) view.findViewById(R.id.fragment4_iv1);
		iv2 = (ImageView) view.findViewById(R.id.fragment4_iv2);
		params = (LayoutParams) iv1.getLayoutParams();
		params.setMargins(0, dm.heightPixels / 3 * 1, 0, 0);
		iv1.setLayoutParams(params);

		params2 = (LayoutParams) iv2.getLayoutParams();
		params2.setMargins(0, dm.heightPixels / 3 * 2, 0, 0);
		iv2.setLayoutParams(params2);
		iv2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mActivity, LoginActivity.class));
				mActivity.finish();
			}
		});

	}
}
