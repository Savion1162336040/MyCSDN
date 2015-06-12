package com.shouwei.csdn.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shouwei.csdn.R;

public class LoginActivity extends BaseActivity implements OnClickListener{
	RelativeLayout logingroup,login_relative;
	LinearLayout login_detail_linear;
	Button csdn_login,login_detail_ok;
	TextView titlebar_back,titlebar_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		init();
	}
	
	private void init(){
		DisplayMetrics dm = getActivityMetrics();
		logingroup = (RelativeLayout) findViewById(R.id.login_btngroup);
		csdn_login = (Button) findViewById(R.id.login_btn_csdn);
		login_detail_ok = (Button) findViewById(R.id.login_detail_login);
		login_relative = (RelativeLayout) findViewById(R.id.login_relative);
		login_detail_linear = (LinearLayout) findViewById(R.id.login_detail_relative);
		titlebar_back = (TextView) findViewById(R.id.title_bar_back);
		titlebar_title = (TextView) findViewById(R.id.title_bar_title);
		titlebar_back.setText(mResources.getString(R.string.text_cancel));
		titlebar_title.setText(mResources.getString(R.string.text_login));
		login_detail_ok.setOnClickListener(this);
		csdn_login.setOnClickListener(this);
		titlebar_back.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn_csdn:
			startL2RRock(login_relative, login_detail_linear);
			break;
		case R.id.title_bar_back:
			startR2LRock(login_detail_linear, login_relative);
			break;
		case R.id.login_detail_login:
			goToActivity(mActivity, MainActivity.class);
			break;
		default:
			break;
		}
	}
	
}
