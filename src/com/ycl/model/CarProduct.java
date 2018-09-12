package com.ycl.model;

public class CarProduct {
	private int id;
	private int number;
	private double price;
	private Order order;
	private Product product;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "CarProduct [id=" + id + ", number=" + number + ", price=" + price + ", order=" + order + ", product="
				+ product + "]";
	}
	public CarProduct(int id, int number, double price, Order order, Product product) {
		super();
		this.id = id;
		this.number = number;
		this.price = price;
		this.order = order;
		this.product = product;
	}
	
	public CarProduct() {
		// TODO Auto-generated constructor stub
	}
}
