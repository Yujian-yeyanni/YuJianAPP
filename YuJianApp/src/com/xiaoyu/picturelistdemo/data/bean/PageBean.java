package com.xiaoyu.picturelistdemo.data.bean;

public class PageBean extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pageSize;
	private int pageIndex;
	private int maxSize;
	private int maxPage;
	private int beginNum;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getBeginNum() {
		return beginNum;
	}

	public void setBeginNum(int beginNum) {
		this.beginNum = beginNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
