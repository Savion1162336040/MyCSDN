package com.shouwei.csdn.fragment;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.shouwei.csdn.R;
import com.shouwei.csdn.activity.NewsDetailActivity;
import com.shouwei.csdn.adapter.FragmentReadItemAdapter;
import com.shouwei.csdn.entity.NewsTable;
import com.shouwei.csdn.util.HtmlParse;
import com.shouwei.csdn.util.MyConstants;

public class ReadFragment extends Fragment implements OnClickListener, OnItemClickListener {
	Activity mActivity;
	View view, footview;
	ImageView open_sidemenu, refresh;
	Button loadmore;
	SlidingMenu mSlidingmenu;
	TextView center;
	List<NewsTable> datalist;
	ListView lv;
	static final int SUCCEED = 10086;
	static final int FALIED = 10000;
	RotateAnimation anim;
	int currentPage = 1;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == SUCCEED) {
				FragmentReadItemAdapter adapter = new FragmentReadItemAdapter(
						(List<NewsTable>) msg.obj, mActivity);
				lv.setAdapter(adapter);
			} else if (msg.what == FALIED) {
				MyConstants.showToast(mActivity, "无数据");
			}
			if (anim != null && anim.hasStarted()) {
				anim.cancel();
			}
		};
	};

	public ReadFragment(Activity mActivity, SlidingMenu mSlidingmenu) {
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
		getInfo(currentPage);
		return view;
	}

	private void init() {
		open_sidemenu = (ImageView) view
				.findViewById(R.id.content_title_bar_view_opensidemenu);
		center = (TextView) view
				.findViewById(R.id.content_title_bar_view_center);
		refresh = (ImageView) view
				.findViewById(R.id.content_title_bar_view_search);
		lv = (ListView) view.findViewById(R.id.fragment_frame_content_lv);
		footview = LayoutInflater.from(mActivity).inflate(
				R.layout.foot_view_more, null);
		loadmore = (Button) footview.findViewById(R.id.foot_view_more);
		open_sidemenu.setOnClickListener(this);
		refresh.setOnClickListener(this);
		center.setText("阅读");
		loadmore.setOnClickListener(this);
		lv.setFooterDividersEnabled(true);
		lv.addFooterView(footview);
		lv.setOnItemClickListener(this);
	}

	private void getInfo(int page) {
		final int mPage = page;
		new Thread() {
			@Override
			public void run() {
				super.run();
				datalist = HtmlParse.getData(
						MyConstants.NEWS_TYPE_NEWS, mPage);
				Message msg = new Message();
				if (datalist != null && datalist.size() > 0) {
					msg.what = SUCCEED;
					msg.obj = datalist;
					handler.sendMessage(msg);
				} else {
					handler.sendEmptyMessage(FALIED);
				}
			}
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
			anim = (RotateAnimation) AnimationUtils.loadAnimation(mActivity,
					R.anim.rotate_anim);
			refresh.startAnimation(anim);
			getInfo(currentPage=1);
			break;
		case R.id.foot_view_more:
			MyConstants.showToast(mActivity, "下一页");
			getInfo(++currentPage);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent t = new Intent();
		t.setClass(mActivity, NewsDetailActivity.class);
		t.putExtra("target_url", datalist.get(position).getTarget_url());
		startActivity(t);
	}

}
