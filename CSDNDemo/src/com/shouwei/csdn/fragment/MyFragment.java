package com.shouwei.csdn.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.shouwei.csdn.R;
import com.shouwei.csdn.util.MyConstants;

public class MyFragment extends Fragment implements OnClickListener {
	Activity mActivity;
	View view;
	ImageView open_sidemenu,user_icon;
	SlidingMenu mSlidingmenu;
	TextView user_name;
	public MyFragment(Activity mActivity,SlidingMenu mSlidingmenu) {
		this.mActivity = mActivity;
		this.mSlidingmenu = mSlidingmenu;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_my,
				null);
		init();
		return view;
	}
	private void init(){
		open_sidemenu = (ImageView) view.findViewById(R.id.fragment_my_opensidemenu);
		user_icon = (ImageView) view.findViewById(R.id.fragment_my_icon);
		user_name = (TextView) view.findViewById(R.id.fragment_my_user_name);
		open_sidemenu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_my_opensidemenu:
			if(mSlidingmenu!=null){
				mSlidingmenu.showMenu();
			}
			break;
		default:
			break;
		}
	}
}
