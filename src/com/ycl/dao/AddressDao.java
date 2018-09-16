package com.ycl.dao;

import java.util.List;
import java.util.Map;

public class AddressDao extends BaseDao {
	@Override
	public void add(Object obj) {
		super.add(obj);
	}
	
	@Override
	public Object load(Class clz, int id) {
		return super.load(clz, id);
	}
	
	@Override
	public void update(Object obj) {
		super.update(obj);
	}
	@Override
	public void delete(Class clz, int id) {
		super.delete(clz, id);
	}
	@Override
	public List list(Class clz, Map<String, Object> params) {
		return super.list(clz, params);
	}
}
