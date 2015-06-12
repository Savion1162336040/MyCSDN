package com.shouwei.csdn.activity;

import com.shouwei.csdn.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashActivity extends BaseActivity {
	SharedPreferences mSharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		mSharedPreferences = getSharedPreferences("appRun",
				Context.MODE_PRIVATE);
		boolean first = mSharedPreferences.getBoolean("isfirstrun", true);
		if (first) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					startActivity(new Intent(SplashActivity.this,
							StartActivity.class));
					Editor edit = mSharedPreferences.edit();
					edit.putBoolean("isfirstrun", false);
					edit.commit();
					finish();
				}
			}, 2000);
		} else {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					startActivity(new Intent(SplashActivity.this,
							LoginActivity.class));
					finish();
				}
			}, 2000);
		}

	}

}
