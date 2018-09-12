package com.ycl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import com.ycl.dao.CarProductDao;
import com.ycl.dao.OrderDao;
import com.ycl.dao.ProductDao;
import com.ycl.model.CarProduct;
import com.ycl.model.Order;
import com.ycl.model.Product;

public class TestCarProductDao {

	CarProductDao cpd=new CarProductDao();
	@Test
	public void testAdd() {
		CarProduct cp=new CarProduct();
		cp.setNumber(10);
		OrderDao od=new OrderDao();
		Order order = (Order) od.load(Order.class, 1);
		cp.setOrder(order);
		cp.setPrice(100);
		ProductDao pd=new ProductDao();
		Product product = (Product) pd.load(Product.class, 2);
		cp.setProduct(product);
		
		cpd.add(cp);
	}

}
