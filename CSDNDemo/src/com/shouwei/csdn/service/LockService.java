package com.shouwei.csdn.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LockService extends Service{

	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onCreate() {
		super.onCreate();
	}

}
