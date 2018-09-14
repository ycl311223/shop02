package com.ycl.model;

public class SystemContext {
	private static ThreadLocal<Integer> pageSize=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageIndex=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageOffset=new ThreadLocal<Integer>();
	private static ThreadLocal<String> realpath=new ThreadLocal<String>();
	private static ThreadLocal<String> order=new ThreadLocal<String>();
	private static ThreadLocal<String> sort=new ThreadLocal<String>();
	
	public static Integer getPageSize() {
		return pageSize.get();
	}
	public static void setPageSize(Integer _pageSize) {
		SystemContext.pageSize.set(_pageSize);
	}
	public static Integer getPageIndex() {
		return pageIndex.get();
	}
	public static void setPageIndex(Integer _pageIndex) {
		SystemContext.pageIndex.set(_pageIndex);
	}
	public static Integer getPageOffset() {
		return pageOffset.get();
	}
	public static void setPageOffset(Integer _pageOffset) {
		SystemContext.pageOffset.set(_pageOffset);
	}
	public static String getRealpath() {
		return realpath.get();
	}
	public static void setRealpath(String _realpath) {
		SystemContext.realpath.set(_realpath);
	}
	public static String getOrder() {
		return order.get();
	}
	public static void setOrder(String _order) {
		SystemContext.order.set(_order);
	}
	public static String getSort() {
		return sort.get();
	}
	public static void setSort(String _sort) {
		SystemContext.sort.set(_sort);
	}
	
	public static void removePageSize() {
		pageSize.remove();
	}
	public static void removePageOffset() {
		pageOffset.remove();
	}
	public static void removeRealPath() {
		realpath.remove();
	}
	public static void removeOrder() {
		order.remove();
	}
	public static void removeSort() {
		sort.remove();
	}
	
}
