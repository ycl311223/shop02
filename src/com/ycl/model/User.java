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
	
}
