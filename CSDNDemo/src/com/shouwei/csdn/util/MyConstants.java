package com.shouwei.csdn.util;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class MyConstants {

	// public final static int KEY_FIRST_INVERSE = 1;
	//
	// public final static int KEY_FIRST_CLOCKWISE = 2;
	//
	// public final static int KEY_SECOND_INVERSE = 3;
	//
	// public final static int KEY_SECOND_CLOCKWISE = 4;
	// 类型
	// 新闻
	public final static int NEWS_TYPE_NEWS = 1;
	// 技术论坛
	public final static int NEWS_TYPE_BBS_TEC = 2;
	// 新闻论坛
	public final static int NEWS_TYPE_BBS_LIFE = 3;
	// 全部问答
	public final static int NEWS_TYPE_ASK = 4;
	// 悬赏问答
	public final static int NEWS_TYPE_ASK_REWARD = 5;
	// 未解决问答
	public final static int NEWS_TYPE_ASK_UNSOLVED = 6;
	// 已解决问答
	public final static int NEWS_TYPE_ASK_RESOLVED = 7;
	// 标签问答
	public final static int NEWS_TYPE_ASK_TAGS = 8;

	// 新闻
	public final static String URL_NEWS = "http://news.csdn.net/news";
	// 技术论坛
	public final static String URL_BBS_TEC = "http://bbs.csdn.net/recommend_tech_topics?page=";
	// /生活论坛
	public final static String URL_BBS_LIFE = "http://bbs.csdn.net/recommend_nontech_topics?page=";
	// 全部问答
	public final static String URL_ASK_ALL = "http://ask.csdn.net/?page=";
	// 悬赏问答
	public final static String URL_ASK_REWARD = "?type=reward";
	// 待回答
	public final static String URL_ASK_UNSOLVED = "?type=unsolved";
	// 已回答
	public final static String URL_ASK_RESOLVED = "?type=resolved";
	// 标签问答
	public final static String URL_ASK_TAGS = "http://ask.csdn.net/tags?page=";

	/**
	 * 根据类型和页码返回网络地址 1:新闻，2：论坛，3：问答
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
			url += URL_ASK_ALL;
			url += page;
			break;
		case 5:
			url = URL_ASK_ALL+page+URL_ASK_REWARD;
			break;
		case 6:
			url = URL_ASK_ALL+page+URL_ASK_UNSOLVED;
			break;
		case 7:
			url = URL_ASK_ALL+page+URL_ASK_RESOLVED;
			break;
		case 8:
			url = URL_ASK_ALL+page+URL_ASK_TAGS;
			break;
		default:
			break;
		}
		return url;
	}

	static String TAG = "savion";
	static boolean isShowLog = true;

	public static void myLog(String str) {
		if (isShowLog) {
			Log.i(TAG, str);
		}
	}

	/**
	 * 统一提示方法
	 * 
	 * @param activity
	 * @param msg
	 * @auth shouwei
	 */
	public static void showToast(Activity activity, String msg) {
		Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
	}
}
