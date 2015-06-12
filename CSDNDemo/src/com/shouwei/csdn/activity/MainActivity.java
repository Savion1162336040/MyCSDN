package com.shouwei.csdn.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalBitmap;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.shouwei.csdn.R;
import com.shouwei.csdn.fragment.AskFragment;
import com.shouwei.csdn.fragment.BBSFragment;
import com.shouwei.csdn.fragment.MyFragment;
import com.shouwei.csdn.fragment.ReadFragment;
import com.shouwei.csdn.fragment.SettingFragment;
import com.shouwei.csdn.fragment.SideMenuFragment;
import com.shouwei.csdn.fragment.SideMenuFragment.MySideMenuItemClickListener;

public class MainActivity extends SlidingFragmentActivity implements
		MySideMenuItemClickListener {
	SlidingMenu mSlidingMenu;
	int backCount = 0;
	long mill;
	FrameLayout content;
	FragmentManager fm;
	FragmentTransaction ft;
	ReadFragment readF;
	AskFragment askF;
	MyFragment myF;
	BBSFragment bbsF;
	SettingFragment setF;
	SideMenuFragment smf;
	List<Fragment> fragmentList;
	FinalBitmap finalBitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.sidemenu_frame);
		initSlidingMenu();
		init();
		initBitmapConfigure();
	}

	private void init() {
		content = (FrameLayout) findViewById(R.id.main_content);
		readF = new ReadFragment(this, mSlidingMenu);
		bbsF = new BBSFragment(this, mSlidingMenu);
		myF = new MyFragment(this, mSlidingMenu);
		setF = new SettingFragment(this, mSlidingMenu);
		askF = new AskFragment(this, mSlidingMenu);
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(readF);
		fragmentList.add(askF);
		fragmentList.add(bbsF);
		fragmentList.add(myF);
		fragmentList.add(setF);
		smf = new SideMenuFragment(this);
		fm = getSupportFragmentManager();
		ft = fm.beginTransaction();
		ft.replace(R.id.sidemenu_frame_content, smf);
		ft.add(R.id.main_content, readF);
		ft.add(R.id.main_content, askF);
		ft.add(R.id.main_content, bbsF);
		ft.add(R.id.main_content, myF);
		ft.add(R.id.main_content, setF);
		ft.commit();
		showFragment(readF);
	}
	/**
	 * 初始化图片加载 
	 * @auth shouwei
	 */
	private void initBitmapConfigure(){
		String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/csdndemo_imagecache";
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		finalBitmap = FinalBitmap.create(this);
		finalBitmap.configLoadingImage(R.drawable.default_img);
		finalBitmap.configLoadfailImage(R.drawable.default_img);
		finalBitmap.configDiskCachePath(path);
		finalBitmap.configRecycleImmediately(false);
	}

	/**
	 * 初始化侧滑菜单
	 * 
	 * @auth shouwei
	 */
	private void initSlidingMenu() {
		mSlidingMenu = getSlidingMenu();
		// mSlidingMenu.setMenu(R.layout.frame_menu); //设置左侧菜单的布局文件
		// mSlidingMenu.setSecondaryMenu(R.layout.frame_menu); 设置右侧菜单的布局文件

		// mSlidingMenu.setShadowWidth(5);
		// mSlidingMenu.setBehindOffset(100);
		mSlidingMenu.setFadeDegree(0.35f);
		// 设置SlidingMenu 的手势模式
		// TOUCHMODE_FULLSCREEN 全屏模式，在整个content页面中，滑动，可以打开SlidingMenu
		// TOUCHMODE_MARGIN
		// 边缘模式，在content页面中，如果想打开SlidingMenu,你需要在屏幕边缘滑动才可以打开SlidingMenu
		// TOUCHMODE_NONE 不能通过手势打开SlidingMenu
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mSlidingMenu.setBehindOffset(getActivityMetrics().widthPixels / 5 * 2);
	}
	
	private void showFragment(Fragment f){
		fm = getSupportFragmentManager();
		ft = fm.beginTransaction();
		ft.hide(readF);
		ft.hide(askF);
		ft.hide(bbsF);
		ft.hide(myF);
		ft.hide(setF);
		ft.show(f);
		ft.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 测量屏幕
	 * 
	 * @return
	 * @auth shouwei
	 */
	public DisplayMetrics getActivityMetrics() {
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm;
	}

	/**
	 * 按两次退出
	 */
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			backCount++;
			if (backCount >= 2) {
				if (System.currentTimeMillis() - mill <= 1000) {
					mill = 0;
					backCount = 0;
					return super.onKeyDown(keyCode, event);
				} else {
					backCount = 1;
					mill = System.currentTimeMillis();
					Toast.makeText(getApplicationContext(), "再按一次退出", 1000)
							.show();
				}
			} else {
				mill = System.currentTimeMillis();
				Toast.makeText(getApplicationContext(), "再按一次退出", 1000).show();
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void itemClick(int index, String title) {
		Log.i("savion","index == >"+index+",title == >" + title);
		showFragment(fragmentList.get(index));
	}

}
