package com.ycl.model;

import java.util.List;

public class User {
	private int id;
	private String username;
	private String password;
	private String nickname;
	/**
	 * 0普通用户 1超级管理员
	 * */
	private int type;
	private List<Address> addresses;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public User(int id, String username, String password, String nickname, int type, List<Address> addresses) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.type = type;
		this.addresses = addresses;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", type=" + type + ", addresses=" + addresses + "]";
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
}
