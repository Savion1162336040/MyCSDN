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

public class FragmentReadItemAdapter extends BaseAdapter{

	List<NewsTable> list;
	Activity mActivity;
	public FragmentReadItemAdapter(List<NewsTable> list,Activity mActivity){
		this.list = list;
		this.mActivity = mActivity;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list!=null?list.size():0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder vh;
		if(convertView==null){
			vh = new ViewHolder();
			convertView = LayoutInflater.from(mActivity).inflate(R.layout.fragment_read_news_item, null);
			vh.content = (TextView) convertView.findViewById(R.id.fragment_read_item_content);
			vh.title = (TextView) convertView.findViewById(R.id.fragment_read_item_title);
			vh.time = (TextView) convertView.findViewById(R.id.fragment_read_item_time);
			vh.iv = (ImageView) convertView.findViewById(R.id.fragment_read_item_iv);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder) convertView.getTag();
		}
		
		vh.content.setText(list.get(position).getContent());
		vh.time.setText(list.get(position).getTime());
		vh.title.setText(list.get(position).getTitle());
		String img_url = list.get(position).getImg_url();
		if(img_url!=null&&!("".equals(img_url))){
			MyConstants.myLog(position +" img_url == >"+list.get(position).getImg_url());
			FinalBitmap.create(mActivity).display(vh.iv, list.get(position).getImg_url());
			vh.iv.setVisibility(View.VISIBLE);
		}else{
			vh.iv.setVisibility(View.GONE);
		}
		return convertView;
	}
	
	class ViewHolder{
		TextView title,content,time;
		ImageView iv;
	}

}
