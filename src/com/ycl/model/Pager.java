package com.ycl.model;

import java.util.List;

public class Pager {
	private int pageIndex;
	private int pageSize;
	private int pageOffset;
	private int totalRecord;
	private int totalPage;
	private List datas;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageOffset() {
		return pageOffset;
	}
	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	@Override
	public String toString() {
		return "Pager [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", pageOffset=" + pageOffset
				+ ", totalRecord=" + totalRecord + ", totalPage=" + totalPage + ", datas=" + datas + "]";
	}
	
	
}
