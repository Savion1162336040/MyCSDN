package com.shouwei.csdn.util;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class MyConstants {

	public final static int KEY_FIRST_INVERSE = 1;

	public final static int KEY_FIRST_CLOCKWISE = 2;

	public final static int KEY_SECOND_INVERSE = 3;

	public final static int KEY_SECOND_CLOCKWISE = 4;
	
	public final static int NEWS_TYPE_NEWS = 1;
	public final static int NEWS_TYPE_BBS_TEC = 2;
	public final static int NEWS_TYPE_BBS_LIFE = 3;
	public final static int NEWS_TYPE_ASK = 4;
	
	

	/**
	 * 统一提示方法
	 * @param activity
	 * @param msg
	 * @auth shouwei
	 */
	public static void showToast(Activity activity, String msg) {
		Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
	}
	
	
	public final static String URL_NEWS = "http://news.csdn.net/news";
	public final static String URL_BBS_TEC = "http://bbs.csdn.net/recommend_tech_topics?page=";
	public final static String URL_BBS_LIFE = "http://bbs.csdn.net/recommend_nontech_topics?page=";
	public final static String URL_ASK = "http://ask.csdn.net/?page=";

	public final static String URL_TEC_ASK="http://ask.csdn.net/?page=1&ref=toolbar";
	/**
	 * 根据类型和页码返回网络地址
	 * 1:新闻，2：论坛，3：问答
	 * @param urlType
	 * @param page
	 * @return
	 * @auth shouwei
	 */
	public static String getURL(int urlType, int page) {
		String url = "";
		switch (urlType) {
		case 1:
			url += URL_NEWS;
			url += "/" + page;
			break;
		case 2:
			url = URL_BBS_TEC;
			url += page;
			break;
		case 3:
			url = URL_BBS_LIFE;
			url += page;
			break;
		case 4:
			url += URL_ASK;
			url += page;
			break;
		default:
			break;
		}
		return url;
	}
	static String TAG = "savion";
	static boolean isShowLog = true;
	public static void myLog(String str){
		if(isShowLog){
			Log.i(TAG, str);
		}
	}
}
