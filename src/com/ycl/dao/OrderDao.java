package com.ycl.dao;

import java.util.Map;

import com.ycl.model.Pager;

public class OrderDao extends BaseDao {
	@Override
	public void add(Object obj) {
		super.add(obj);
	}
	@Override
	public void delete(Class clz, int id) {
		super.delete(clz, id);
	}
	@Override
	public void update(Object obj) {
		super.update(obj);
	}
	@Override
	public Object load(Class clz, int id) {
		return super.load(clz, id);
	}
	@Override
	public Pager find(Class clz, Map<String, Object> params) {
		return super.find(clz, params);
	}
}
