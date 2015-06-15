package com.shouwei.csdn.entity;

import java.util.List;

import org.jsoup.nodes.TextNode;

public class BBSTable {

	String time;
	String target_url;
	String title;
	String popularity;
	List<TextNode> nodes;

	public List<TextNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TextNode> nodes) {
		this.nodes = nodes;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
				+ ", title=" + title + ", popularity=" + popularity
				+ ", nodes=" + nodes.size() + "]";
	}
	public String toNodesString(){
		String s = "";
		for (int i = 0; i < nodes.size(); i++) {
			s += nodes.get(i).toString();
		}
		return s;
	}

}
