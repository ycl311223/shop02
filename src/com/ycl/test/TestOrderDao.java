package com.ycl.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;


import com.ycl.dao.AddressDao;
import com.ycl.dao.OrderDao;
import com.ycl.dao.UserDao;
import com.ycl.model.Address;
import com.ycl.model.Order;
import com.ycl.model.Pager;
import com.ycl.model.Product;
import com.ycl.model.SystemContext;
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
		User user=(User) ud.load(User.class, 2);
		o.setUser(user);
		AddressDao ad=new AddressDao();
		Address addr=(Address) ad.load(Address.class, 1);
		o.setAddress(addr);
		od.add(o);
	}
	@Test
	public void testLoad() {
		Order order = (Order) od.load(Order.class, 1);
		System.out.println(order);
	}
	@Test
	public void testFind() {
		SystemContext.setOrder("desc");
		SystemContext.setSort("pname");
		SystemContext.setPageSize(10);
		SystemContext.setPageOffset(1);
		Map<String,Object> params=new HashMap<>();
		Pager find = od.find(Order.class, params);
		System.out.println(find);
	}
}
