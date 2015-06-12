package com.shouwei.csdn.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shouwei.csdn.R;

public class SideMenuFragment extends Fragment {

	Activity mActivity;
	View view;
	LinearLayout read, ask, bbs, my, setting;
	MySideMenuItemClickListener mSideListener;
	TextView ask_tv, read_tv, bbs_tv, my_tv, set_tv;
	ImageView ask_iv, read_iv, bbs_iv, my_iv, set_iv;

	public SideMenuFragment(Activity mActivity) {
		this.mActivity = mActivity;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mSideListener = (MySideMenuItemClickListener) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(mActivity).inflate(R.layout.sidemenu_view,
				null);
		Log.i("savion","sidemenu oncreate");
		init();
		return view;
	}

	private void init() {
		read = (LinearLayout) view.findViewById(R.id.sidemenu_view_read);
		ask = (LinearLayout) view.findViewById(R.id.sidemenu_view_ask);
		bbs = (LinearLayout) view.findViewById(R.id.sidemenu_view_bbs);
		my = (LinearLayout) view.findViewById(R.id.sidemenu_view_my);
		setting = (LinearLayout) view.findViewById(R.id.sidemenu_view_setting);
		read_iv = (ImageView) view.findViewById(R.id.sidemenu_view_read_icon);
		ask_iv = (ImageView) view.findViewById(R.id.sidemenu_view_ask_icon);
		bbs_iv = (ImageView) view.findViewById(R.id.sidemenu_view_bbs_icon);
		my_iv = (ImageView) view.findViewById(R.id.sidemenu_view_my_icon);
		set_iv = (ImageView) view.findViewById(R.id.sidemenu_view_set_icon);
		read_tv = (TextView) view.findViewById(R.id.sidemenu_view_read_tv);
		ask_tv = (TextView) view.findViewById(R.id.sidemenu_view_ask_tv);
		my_tv = (TextView) view.findViewById(R.id.sidemenu_view_my_tv);
		bbs_tv = (TextView) view.findViewById(R.id.sidemenu_view_bbs_tv);
		set_tv = (TextView) view.findViewById(R.id.sidemenu_view_set_tv);
		read.setOnClickListener(MyClick);
		ask.setOnClickListener(MyClick);
		bbs.setOnClickListener(MyClick);
		my.setOnClickListener(MyClick);
		setting.setOnClickListener(MyClick);
		MyClick.onClick(read);
	}

	OnClickListener MyClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.sidemenu_view_read:
				mSideListener.itemClick(0,
						getResources().getString(R.string.text_read));
				refreshcolor(read,read_tv);
				read_iv.setBackgroundResource(R.drawable.read_white);
				break;
			case R.id.sidemenu_view_ask:
				mSideListener.itemClick(1,
						getResources().getString(R.string.text_ask));
				refreshcolor(ask,ask_tv);
				ask_iv.setBackgroundResource(R.drawable.ask_white);
				break;
			case R.id.sidemenu_view_bbs:
				mSideListener.itemClick(2,
						getResources().getString(R.string.text_bbs));
				refreshcolor(bbs,bbs_tv);
				bbs_iv.setBackgroundResource(R.drawable.bbs_white);
				break;
			case R.id.sidemenu_view_my:
				mSideListener.itemClick(3,
						getResources().getString(R.string.text_my));
				refreshcolor(my,my_tv);
				my_iv.setBackgroundResource(R.drawable.my_white);
				break;
			case R.id.sidemenu_view_setting:
				mSideListener.itemClick(4,
						getResources().getString(R.string.text_setting));
				refreshcolor(setting,set_tv);
				set_iv.setBackgroundResource(R.drawable.set_white);
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 刷新背景颜色
	 * 
	 * @auth shouwei
	 */
	private void refreshcolor(LinearLayout l,TextView tv) {
		read.setBackgroundColor(getResources().getColor(R.color.transparent));
		ask.setBackgroundColor(getResources().getColor(R.color.transparent));
		bbs.setBackgroundColor(getResources().getColor(R.color.transparent));
		my.setBackgroundColor(getResources().getColor(R.color.transparent));
		setting.setBackgroundColor(getResources().getColor(R.color.transparent));
		read_iv.setBackgroundResource(R.drawable.read);
		ask_iv.setBackgroundResource(R.drawable.ask);
		bbs_iv.setBackgroundResource(R.drawable.bbs);
		my_iv.setBackgroundResource(R.drawable.my);
		set_iv.setBackgroundResource(R.drawable.set);
		read_tv.setTextColor(getResources().getColor(R.color.gray_text_color));
		ask_tv.setTextColor(getResources().getColor(R.color.gray_text_color));
		bbs_tv.setTextColor(getResources().getColor(R.color.gray_text_color));
		my_tv.setTextColor(getResources().getColor(R.color.gray_text_color));
		set_tv.setTextColor(getResources().getColor(R.color.gray_text_color));
		l.setBackgroundColor(getResources().getColor(R.color.red));
		tv.setTextColor(getResources().getColor(R.color.white));
		
	}

	public interface MySideMenuItemClickListener {
		public void itemClick(int index, String title);
	}
}
