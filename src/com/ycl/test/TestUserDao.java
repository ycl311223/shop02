package com.ycl.test;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ycl.dao.UserDao;
import com.ycl.model.Pager;
import com.ycl.model.Product;
import com.ycl.model.SystemContext;
import com.ycl.model.User;

public class TestUserDao {
	UserDao userDao=new UserDao();
	@Test
	public void testAdd() {
		User u=new User();
		u.setUsername("zzt");
		u.setPassword("456");
		u.setNickname("ll");
		u.setType(0);
		
		UserDao userDao=new UserDao();
		userDao.add(u);
	}
	@Test
	public void testLoad() {
		User u=new User();
		Object load = userDao.load(User.class, 4);
		System.out.println(load);
	}
	@Test
	public void testLoadByUserName() {
		Object load = userDao.loadByUserName(User.class, "ycl");
		System.out.println(load);
	}
	
	@Test
	public void testUpdate() {
		User u=(User) userDao.load(User.class, 4);
		u.setUsername("ycl");
		u.setPassword("123");
		u.setNickname("ll");
		u.setType(1);
		userDao.update(u);
	}
	
	@Test
	public void testUserDaoDelete() {
		userDao.delete(User.class, 2);
	}
	@Test
	public void testFind() {
		SystemContext.setOrder("desc");
		SystemContext.setSort("username");
		SystemContext.setPageSize(10);
		SystemContext.setPageOffset(0);
		Map<String,Object> params=new HashMap<>();
		Pager find = userDao.find(User.class, params);
		System.out.println(find.getDatas());
	}
}
