package com.ycl.test;


import org.junit.Test;

import com.ycl.dao.UserDao;
import com.ycl.model.User;

public class TestUserDao {
	UserDao userDao=new UserDao();
	@Test
	public void testAdd() {
		User u=new User();
		u.setUsername("ycl");
		u.setPassword("123");
		u.setNickname("ll");
		u.setType(0);
		
		UserDao userDao=new UserDao();
		userDao.add(u);
	}
	@Test
	public void testLoad() {
		User u=new User();
		Object load = userDao.load(User.class, 2);
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
	
}
