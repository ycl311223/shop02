package com.ycl.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import com.ycl.dao.CategoryDao;
import com.ycl.dao.UserDao;
import com.ycl.model.Category;
import com.ycl.model.User;

public class TestCategoryDao {

	CategoryDao cd=new CategoryDao();

	@Test
	public void testAdd() {
		Category c=new Category();
		c.setName("汽车");
		cd.add(c);
	}
	@Test
	public void testLoad() {
		Object load = cd.load(Category.class, 1);
		System.out.println(load);
	}
	
	@Test
	public void testList() {
		Map<String,Object> params=new HashMap<>();
		List list = cd.list(Category.class, null);
		System.out.println(list);
		
	}
	
	@Test
	public void testUpdate() {
		Category c=(Category) cd.load(Category.class, 2);
		c.setName("奢侈品");
		cd.update(c);
		
	}
	
	@Test
	public void testDelete() {
		cd.delete(Category.class, 1);
		
	}
	

}
