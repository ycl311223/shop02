package com.ycl.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import com.ycl.dao.CategoryDao;
import com.ycl.dao.ProductDao;
import com.ycl.model.Category;
import com.ycl.model.Pager;
import com.ycl.model.Product;
import com.ycl.model.SystemContext;

public class TestProductDao {

	ProductDao pd=new ProductDao();

	@Test
	public void testAdd() {
		Product p=new Product();
		p.setName("西瓜");
		p.setPrice(15.2);
		p.setStatus(0);
		p.setStock(150);
		p.setIntro("特别好吃");
		p.setImg("xxx.jpg");
		CategoryDao cd=new CategoryDao();
		Category category=(Category) cd.load(Category.class, 2);
		p.setCategory(category);
		pd.add(p);
	}
	@Test
	public void testDelete() {
		pd.delete(Product.class, 1);
	}
	@Test
	public void testLoad() {
		Product p=(Product) pd.load(Product.class, 2);
		System.out.println(p);
	}
	@Test
	public void testList() {
		Map<String,Object> params=new HashMap<>();
		params.put("id", 2);
		List list = pd.list(Product.class, params);
		System.out.println(list);
	}
	@Test
	public void testFind() {
		SystemContext.setOrder("desc");
		SystemContext.setSort("id");
		SystemContext.setPageSize(10);
		SystemContext.setPageOffset(1);
		Map<String,Object> params=new HashMap<>();
		Pager find = pd.find(Product.class, params);
		System.out.println(find);
	}
	
	@Test
	public void testUpdate() {
		Product op=(Product) pd.load(Product.class, 2);
		op.setName("西红柿");
		pd.update(op);
	}

}
