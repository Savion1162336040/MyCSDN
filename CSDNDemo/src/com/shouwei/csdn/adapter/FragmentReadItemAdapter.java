package com.shouwei.csdn.adapter;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shouwei.csdn.R;
import com.shouwei.csdn.entity.NewsTable;
import com.shouwei.csdn.util.MyConstants;

public class FragmentReadItemAdapter extends MBaseAdapter {

	public FragmentReadItemAdapter(Activity mActivity, List<NewsTable> list) {
		super(mActivity, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = LayoutInflater.from(mActivity).inflate(
					R.layout.fragment_read_news_item, null);
			vh.content = (TextView) convertView
					.findViewById(R.id.fragment_read_item_content);
			vh.title = (TextView) convertView
					.findViewById(R.id.fragment_read_item_title);
			vh.time = (TextView) convertView
					.findViewById(R.id.fragment_read_item_time);
			vh.iv = (ImageView) convertView
					.findViewById(R.id.fragment_read_item_iv);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		
		vh.content.setText(((NewsTable) list.get(position)).getContent());
		vh.time.setText(((NewsTable) list.get(position)).getTime());
		vh.title.setText(((NewsTable) list.get(position)).getTitle());
		String img_url = ((NewsTable) list.get(position)).getImg_url();
		if (img_url != null && !("".equals(img_url))) {
			FinalBitmap.create(mActivity).display(vh.iv,
					((NewsTable) list.get(position)).getImg_url());
			vh.iv.setVisibility(View.VISIBLE);
		} else {
			vh.iv.setVisibility(View.GONE);
		}
		return convertView;
	}

	class ViewHolder {
		TextView title, content, time;
		ImageView iv;
	}

}
