package com.ycl.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Test;


import com.ycl.dao.AddressDao;
import com.ycl.dao.OrderDao;
import com.ycl.dao.UserDao;
import com.ycl.model.Address;
import com.ycl.model.Order;
import com.ycl.model.User;

public class TestOrderDao {

	
	OrderDao od=new OrderDao();
	@Test
	public void testAdd() {
		Order o=new Order();
		o.setBuy_date(new Date());
		o.setPay_date(new Date());
		o.setConfirm_date(new Date());
		o.setPrice(1200);
		o.setStatus(1);
		UserDao ud=new UserDao();
		User user=(User) ud.load(User.class, 4);
		o.setUser(user);
		AddressDao ad=new AddressDao();
		Address addr=(Address) ad.load(Address.class, 3);
		o.setAddress(addr);
		od.add(o);
	}
	@Test
	public void testLoad() {
		Order order = (Order) od.load(Order.class, 1);
		System.out.println(order);
	}
}