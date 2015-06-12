package com.shouwei.csdn.entity;

public class BBSTable {

	String time;
	String target_url;
	String content;
	String popularity;

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

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

	@Override
	public String toString() {
		return "BBSTable [time=" + time + ", target_url=" + target_url
				+ ", content=" + content + ", popularity=" + popularity + "]";
	}
}
