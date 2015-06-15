package com.shouwei.csdn.adapter;

import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MBaseAdapter extends BaseAdapter {
	Resources mResources;
	Activity mActivity;
	LayoutInflater mLayoutInflater;
	List<?> list;

	public MBaseAdapter(Activity mActivity, List<?> list) {
		mResources = mActivity.getResources();
		this.mActivity = mActivity;
		mLayoutInflater = LayoutInflater.from(mActivity);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

}
