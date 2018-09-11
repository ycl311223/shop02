package com.ycl.model;

import java.util.Date;
import java.util.List;

public class Order {
	private int id;
	private Date buy_date;
	private Date pay_date;
	private Date confirm_date;
	private User user;
	private Address address;
	private int status;
	private double price;
	private List<CarProduct> carProducts;
	
}
