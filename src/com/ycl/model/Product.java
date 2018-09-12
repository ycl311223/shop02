package com.ycl.model;

public class Product {
	private int id;
	private String name;
	private double price;
	private Category category;
	private String intro;
	private int status;
	private String img;
	private int stock;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Product(int id, String name, double price, Category category, String intro, int status, String img,
			int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.intro = intro;
		this.status = status;
		this.img = img;
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", intro="
				+ intro + ", status=" + status + ", img=" + img + ", stock=" + stock + "]";
	}
	
	
}
