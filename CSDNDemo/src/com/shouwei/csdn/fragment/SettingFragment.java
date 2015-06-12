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

public class SettingFragment extends Fragment implements OnClickListener {
	Activity mActivity;
	View view;
	ImageView title_bar_open_sidemenu,title_bar_search;
	SlidingMenu mSlidingmenu;
	TextView title_bar_title;
	
	public SettingFragment(Activity mActivity,SlidingMenu mSlidingmenu) {
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
		view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_setting,
				null);
		init();
		return view;
	}
	private void init(){
		title_bar_open_sidemenu = (ImageView) view.findViewById(R.id.content_title_bar_view_opensidemenu);
		title_bar_search = (ImageView) view.findViewById(R.id.content_title_bar_view_search);
		title_bar_title = (TextView) view.findViewById(R.id.content_title_bar_view_center);
		title_bar_title.setText("设置");
		title_bar_search.setVisibility(View.INVISIBLE);
		title_bar_open_sidemenu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.content_title_bar_view_opensidemenu:
			if(mSlidingmenu!=null){
				mSlidingmenu.showMenu();
			}
			break;
		case R.id.content_title_bar_view_search:
			MyConstants.showToast(mActivity, "点击刷新");
			break;
		default:
			break;
		}
	}
	
}
