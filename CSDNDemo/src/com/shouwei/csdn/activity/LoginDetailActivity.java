package com.shouwei.csdn.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shouwei.csdn.R;

public class LoginDetailActivity extends BaseActivity implements OnClickListener{
	TextView back,title;
	Button login;
	LinearLayout login_relative;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_detail);
		init();
	}

	private void init(){
		back = (TextView) findViewById(R.id.title_bar_back);
		title = (TextView) findViewById(R.id.title_bar_title);
		back.setText(mResources.getString(R.string.text_cancel));
		title.setText(mResources.getString(R.string.text_login));
		login = (Button) findViewById(R.id.login_detail_login);
		login_relative = (LinearLayout) findViewById(R.id.login_detail_relative);
		login.setOnClickListener(this);
		back.setOnClickListener(this);
		login_relative.startAnimation(LoginInAnimation());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_detail_login:
			goToActivity(this,MainActivity.class);
			break;
		case R.id.title_bar_back:
			Bundle b = new Bundle();
			b.putString("intentanimation", "logindetail");
			break;
		default:
			break;
		}
	}
}
