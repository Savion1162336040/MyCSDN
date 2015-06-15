package com.shouwei.csdn.fragment;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.shouwei.csdn.R;
import com.shouwei.csdn.adapter.FragmentBBSItemAdapter;
import com.shouwei.csdn.entity.BBSTable;
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
	List<BBSTable> dataList;
	ListView lv;
	final static int SUCCEED = 10086,FALIED = 10000;
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what == SUCCEED) {
				//有数据
				FragmentBBSItemAdapter adapter = new FragmentBBSItemAdapter(mActivity, (List<BBSTable>) msg.obj);
				lv.setAdapter(adapter);
			}else{
				//无数据
			}
		};
	};

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
		lv = (ListView) view.findViewById(R.id.fragment_frame_content_lv);
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
				dataList = HtmlParse.parseBBSHTML(HtmlParse.getData(bbs_type, page));
				if(dataList!=null&&dataList.size()>0){
					Message msg = new Message();
					msg.what = SUCCEED;
					msg.obj = dataList;
					handler.sendMessage(msg);
				}else{
					handler.sendEmptyMessage(FALIED);
				}
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
