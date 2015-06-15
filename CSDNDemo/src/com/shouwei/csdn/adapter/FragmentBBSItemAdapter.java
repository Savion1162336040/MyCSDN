package com.shouwei.csdn.adapter;

import java.util.List;

import com.shouwei.csdn.R;
import com.shouwei.csdn.entity.BBSTable;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FragmentBBSItemAdapter extends MBaseAdapter {

	public FragmentBBSItemAdapter(Activity mActivity, List<BBSTable> list) {
		super(mActivity, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(
					R.layout.fragment_bbs_item_view, null);
			vh = new ViewHolder();
			vh.pop = (TextView) convertView
					.findViewById(R.id.fragment_bbs_item_pop);
			vh.title = (TextView) convertView
					.findViewById(R.id.fragment_bbs_item_title);
			vh.time = (TextView) convertView
					.findViewById(R.id.fragment_bbs_item_time);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		vh.pop.setText(((BBSTable) list.get(position)).getPopularity());
		vh.time.setText(((BBSTable) list.get(position)).getTime());
		vh.title.setText(((BBSTable) list.get(position)).getTitle());

		return convertView;
	}

	class ViewHolder {
		TextView title, time, pop;
	}
}
