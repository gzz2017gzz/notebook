package com.dl.notebook.common.base;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private List<T> content = new ArrayList<>();
	private int pageSize = 10;
	private long totalElements;
	private int curpage = 0;
	private int pageCount;

	public Page(List<T> dataList, int curpage, long rowCount, int pagesize, int pageCount) {
		this.content.addAll(dataList);
		this.pageSize = pagesize;
		this.totalElements = rowCount;
		this.curpage = curpage;
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public long getPageCount() {
		return this.pageCount;
	}

	public int getCurpage() {
		return this.curpage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getContent() {
		return this.content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public long getTotalElements() {
		return this.totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
}
