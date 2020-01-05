package com.cloud.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Sites {

	private Site site;

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
	private List list = new ArrayList();

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
	public void addSite(Site site) {
		list.add(site);
	}
}
