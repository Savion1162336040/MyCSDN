package com.shouwei.csdn.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.shouwei.csdn.R;
import com.shouwei.csdn.util.MyConstants;

public class AskFragment extends Fragment implements OnClickListener {
	Activity mActivity;
	View view;
	ImageView open_sidemenu,search;
	SlidingMenu mSlidingmenu;
	TextView center;
	public AskFragment(Activity mActivity,SlidingMenu mSlidingmenu) {
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
		view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_frame,
				null);
		init();
		getInfo();
		return view;
	}
	private void init(){
		open_sidemenu = (ImageView) view.findViewById(R.id.content_title_bar_view_opensidemenu);
		center = (TextView) view.findViewById(R.id.content_title_bar_view_center);
		search = (ImageView) view.findViewById(R.id.content_title_bar_view_search);
		open_sidemenu.setOnClickListener(this);
		search.setOnClickListener(this);
		center.setText("问答");
	}

	private void getInfo(){
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
