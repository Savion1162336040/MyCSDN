package com.shouwei.csdn.activity;

import com.shouwei.csdn.util.Rotate3dAnimation;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class BaseActivity extends Activity {

	public Activity mActivity;
	public Resources mResources;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		mActivity = this;
		mResources = getResources();
		intent = new Intent();
	}

	// 测量屏幕
	public DisplayMetrics getActivityMetrics() {
		DisplayMetrics dm = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm;
	}
	public  void goToActivity(Class<?> mClass){
		intent.setClass(mActivity, mClass);
		startActivity(intent);
	}
	public void goToActivity(Activity activity,Class<?> mclass){
		intent.setClass(mActivity, mclass);
		startActivity(intent);
		activity.finish();
	}
	
	public Animation LoginOutAnimation(){
		DisplayMetrics dm = getActivityMetrics();
		float rotateX = dm.widthPixels/2;
		float rotateY = dm.widthPixels/2;
		Rotate3dAnimation rotate = new Rotate3dAnimation(0, 90, rotateX, rotateY, 0.0f, false);
		rotate.setDuration(250);
		rotate.setFillAfter(true);
		rotate.setInterpolator(new AccelerateInterpolator());
		return rotate;
	}
	public Animation LoginInAnimation(){
		DisplayMetrics dm = getActivityMetrics();
		float rotateX = dm.widthPixels/2;
		float rotateY = dm.widthPixels/2;
		Rotate3dAnimation rotate = new Rotate3dAnimation(-90, 0, rotateX, rotateY, 0.0f, false);
		rotate.setDuration(250);
		rotate.setFillAfter(true);
		rotate.setInterpolator(new AccelerateInterpolator());
		return rotate;
	}
	public Animation LoginBackInAnimation(){
		DisplayMetrics dm = getActivityMetrics();
		float rotateX = dm.widthPixels/2;
		float rotateY = dm.widthPixels/2;
		Rotate3dAnimation rotate = new Rotate3dAnimation(90, 0, rotateX, rotateY, 0.0f, false);
		rotate.setDuration(250);
		rotate.setFillAfter(true);
		rotate.setInterpolator(new AccelerateInterpolator());
		return rotate;
	}
	public Animation LoginBackOutAnimation(){
		DisplayMetrics dm = getActivityMetrics();
		float rotateX = dm.widthPixels/2;
		float rotateY = dm.widthPixels/2;
		Rotate3dAnimation rotate = new Rotate3dAnimation(0, -90, rotateX, rotateY, 0.0f, false);
		rotate.setDuration(250);
		rotate.setFillAfter(true);
		rotate.setInterpolator(new AccelerateInterpolator());
		return rotate;
	}
	/**
	 * 从左向右翻转动画
	 * @param leftview
	 * @param rightview
	 * @auth shouwei
	 */
	public void startL2RRock(ViewGroup leftview,ViewGroup rightview){
		final ViewGroup right = rightview;
		final ViewGroup left = leftview;
		Animation anim = LoginOutAnimation();
		anim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				left.setVisibility(View.INVISIBLE);
				right.setVisibility(View.VISIBLE);
				right.startAnimation(LoginInAnimation());
			}
		});
		leftview.startAnimation(anim);
	}
	/**
	 * 从右向左翻转动画
	 * @param v1
	 * @param v2
	 * @auth shouwei
	 */
	public void startR2LRock(ViewGroup rightview,ViewGroup leftview){
		final ViewGroup left = leftview;
		final ViewGroup right = rightview;
		Animation anim = LoginBackOutAnimation();
		anim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				right.setVisibility(View.INVISIBLE);
				left.setVisibility(View.VISIBLE);
				left.startAnimation(LoginBackInAnimation());
			}
		});
		rightview.startAnimation(anim);
	}
}
