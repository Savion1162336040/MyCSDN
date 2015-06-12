package com.shouwei.csdn.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shouwei.csdn.R;
import com.shouwei.csdn.util.MyConstants;

public class NewsDetailActivity extends BaseActivity{
	WebView web;
	String target_url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_detail_view);
		init();
	}
	
	private void init(){
		web = (WebView) findViewById(R.id.news_detail_view_web);
		web.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		target_url = getIntent().getStringExtra("target_url");
		if(target_url!=null&&!("".equals(target_url))){
			refreshData(target_url);
		}else{
			MyConstants.showToast(mActivity, "目标不存在");
			finish();
		}
	}
	
	private void refreshData(String url){
		web.loadUrl(url);
	}

}
