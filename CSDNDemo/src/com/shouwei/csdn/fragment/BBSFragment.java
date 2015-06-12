package com.shouwei.csdn.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.shouwei.csdn.R;
import com.shouwei.csdn.util.HtmlParse;
import com.shouwei.csdn.util.MyConstants;

@SuppressLint("ValidFragment")
public class BBSFragment extends Fragment implements OnClickListener {
	Activity mActivity;
	View view;
	ImageView open_sidemenu, refresh;
	SlidingMenu mSlidingmenu;
	TextView center;
	int page = 1,bbs_type=MyConstants.NEWS_TYPE_BBS_TEC;
	RotateAnimation animation;

	public BBSFragment(Activity mActivity, SlidingMenu mSlidingmenu) {
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

	private void init() {
		open_sidemenu = (ImageView) view
				.findViewById(R.id.content_title_bar_view_opensidemenu);
		center = (TextView) view
				.findViewById(R.id.content_title_bar_view_center);
		refresh = (ImageView) view
				.findViewById(R.id.content_title_bar_view_search);
		open_sidemenu.setOnClickListener(this);
		refresh.setOnClickListener(this);
		center.setText("论坛");
	}

	/**
	 * 获取数据
	 * 
	 * @auth shouwei
	 */
	private void getInfo() {
		new Thread() {
			public void run() {
				HtmlParse.getData(bbs_type, page);
			};
		}.start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.content_title_bar_view_opensidemenu:
			if (mSlidingmenu != null) {
				mSlidingmenu.showMenu();
			}
			break;
		case R.id.content_title_bar_view_search:
			MyConstants.showToast(mActivity, "点击刷新");
			animation = (RotateAnimation) AnimationUtils.loadAnimation(
					mActivity, R.anim.rotate_anim);
			refresh.startAnimation(animation);
			break;
		default:
			break;
		}
	}

}
