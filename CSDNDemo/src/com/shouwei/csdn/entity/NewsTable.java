package com.shouwei.csdn.entity;

import java.util.List;

public class NewsTable {

	String title;
	String time;
	String target_url;
	String content;
	String read;
	String reply;
	String img_url;
	List<String> tags;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTarget_url() {
		return target_url;
	}

	public void setTarget_url(String target_url) {
		this.target_url = target_url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "NewsTable [title=" + title + ", time=" + time + ", target_url="
				+ target_url + ", content=" + content + ", read=" + read
				+ ", reply=" + reply + ", img_url=" + img_url + ", tags="
				+ tags + "]";
	}
}
