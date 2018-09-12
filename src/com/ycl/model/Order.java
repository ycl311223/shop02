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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	public Date getConfirm_date() {
		return confirm_date;
	}
	public void setConfirm_date(Date confirm_date) {
		this.confirm_date = confirm_date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<CarProduct> getCarProducts() {
		return carProducts;
	}
	public void setCarProducts(List<CarProduct> carProducts) {
		this.carProducts = carProducts;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", buy_date=" + buy_date + ", pay_date=" + pay_date + ", confirm_date="
				+ confirm_date + ", user=" + user + ", address=" + address + ", status=" + status + ", price=" + price
				+ ", carProducts=" + carProducts + "]";
	}
	public Order(int id, Date buy_date, Date pay_date, Date confirm_date, User user, Address address, int status,
			double price, List<CarProduct> carProducts) {
		super();
		this.id = id;
		this.buy_date = buy_date;
		this.pay_date = pay_date;
		this.confirm_date = confirm_date;
		this.user = user;
		this.address = address;
		this.status = status;
		this.price = price;
		this.carProducts = carProducts;
	}
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
}
