package com.shouwei.csdn.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import android.util.Log;

import com.shouwei.csdn.entity.BBSTable;
import com.shouwei.csdn.entity.NewsTable;

/**
 * 获取网络数据
 * 
 * @author sw
 * @date 2015-6-8
 */
public class HtmlParse {

	/**
	 * 获取网络html数据
	 * 
	 * @param type
	 * @param page
	 * @return
	 * @auth shouwei
	 */
	public static String getData(int type, int page) {
		String url = MyConstants.getURL(type, page);
		String htmlStr = HttpUtils.doGet(url);
		return htmlStr;
	}
	
	
//	static AjaxCallBack<String> callback = new AjaxCallBack<String>() {
//		public void onSuccess(String t) {
//			super.onSuccess(t);
//			MyConstants.myLog("t == >" + t);
//		};
//
//		public void onFailure(Throwable t, int errorNo, String strMsg) {
//			super.onFailure(t, errorNo, strMsg);
//		};
//	};

	/**
	 * 解析新闻html
	 * 
	 * @param str
	 * @auth shouwei
	 */
	public static List<NewsTable> parseNewsHtml(String str) {
		List<NewsTable> newsTableList = new ArrayList<NewsTable>();
		NewsTable newsTable;
		Document document = Jsoup.parse(str);
		Elements elements = document.getElementsByClass("unit");
		for (int i = 0; i < elements.size(); i++) {
			newsTable = new NewsTable();
			// 标题
			String title = elements.get(i).getElementsByTag("h1").get(0)
					.child(0).text();
			// 目标地址
			String target_url = elements.get(i).getElementsByTag("h1").get(0)
					.child(0).attr("href");

			// 发表时间
			String time = elements.get(i).getElementsByTag("h4").get(0)
					.child(0).text();
			// 阅读次数
			String readCount = elements.get(i).getElementsByTag("h4").get(0)
					.child(1).text();
			// 回复数
			String reply = elements.get(i).getElementsByTag("h4").get(0)
					.child(2).text();
			// 图片地址
			// 获取dt标签下的所有子标签
			Elements e = elements.get(i).getElementsByTag("dl").get(0)
					.getElementsByTag("dt").get(0).children();
			String icon_url = "";
			// 如果没有子标签，也就是没有图片，则不获取图片地址
			if (e.size() != 0) {
				icon_url = e.get(0).child(0).attr("src");
			}
			// 内容描述
			String content = elements.get(i).getElementsByTag("dl").get(0)
					.child(1).text();
			Elements tags = elements.get(i).getElementsByClass("tag").get(0)
					.getElementsByTag("a");
			List<String> tagList = new ArrayList<String>();
			for (int j = 0; j < tags.size(); j++) {
				String tag = tags.get(j).text();
				tagList.add(tag);
			}
			newsTable.setContent(content);
			newsTable.setImg_url(icon_url);
			newsTable.setRead(readCount);
			newsTable.setTitle(title);
			newsTable.setTarget_url(target_url);
			newsTable.setReply(reply);
			newsTable.setTime(time);
			newsTable.setTags(tagList);
			newsTableList.add(newsTable);
		}
		return newsTableList;
	}

	/**
	 * 解析论坛HTML
	 * 
	 * @param str
	 * @auth shouwei
	 */
	public static List<BBSTable> parseBBSHTML(String str) {
		BBSTable bbs;
		List<BBSTable> bbslist = new ArrayList<BBSTable>();
		Document document = Jsoup.parse(str);
		Elements elements = document.getElementsByClass("list_1");
		Elements lis = elements.get(0).getElementsByTag("ul").get(0).getElementsByTag("li");
		MyConstants.myLog("lis size = "+lis.size());
		for (int i = 0; i < lis.size(); i++) {
			bbs = new BBSTable();
			String time = lis.get(i).getElementsByTag("span").get(0)
					.text();
			String pop = lis.get(i).ownText();
			String content = lis.get(i).getElementsByTag("a").text();
			String target_url = lis.get(i).getElementsByTag("a")
					.attr("herf");
			bbs.setTitle(content);
			bbs.setPopularity(pop);
			bbs.setTarget_url(target_url);
			bbs.setTime(time);
			bbslist.add(bbs);
			
		}
		return bbslist;
	}

	public static void parseASKHTML() {
		
	}
}
