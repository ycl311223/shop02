package com.ycl.test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import com.ycl.dao.AddressDao;
import com.ycl.dao.UserDao;
import com.ycl.model.Address;
import com.ycl.model.User;

public class TestAddressDao {

	@After
	public void tearDown() throws Exception {
	}
	
	AddressDao ad=new AddressDao();
	UserDao userDao=new UserDao();
	@Test
	public void testAdd() {
		User user=(User) userDao.load(User.class, 4);
		Address a=new Address();
		a.setName("银川");
		a.setPhone("15248813013");
		a.setPostcode("751400");
		a.setUser(user);
		ad.add(a);
	}
	@Test
	public void testload() {
		Object load = ad.load(Address.class, 3);
		System.out.println(load);
	}
	
	@Test
	public void testlist() {
		Map<String,Object> params=new HashMap<>();
		params.put("id", 4);
		List list = ad.list(Address.class, params);
		System.out.println(list);
	}
	@Test
	public void testupdate() {
		User user=(User) userDao.load(User.class, 4);
		
		Address a=(Address) ad.load(Address.class,3);
		a.setName("北京");
		a.setPhone("15248813013");
		a.setPostcode("751400");
		a.setUser(user);
		ad.update(a);
	}
	
	@Test
	public void testdelete() {
		ad.delete(Address.class, 2);
	}

}
