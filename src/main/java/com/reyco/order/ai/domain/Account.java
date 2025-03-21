package com.reyco.order.ai.domain;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = -1253827167280063513L;
	private String userId;
	private String username;
	public Account() {
		// TODO Auto-generated constructor stub
	}
	public Account(String userId, String username) {
		super();
		this.userId = userId;
		this.username = username;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Account [userId=" + userId + ", username=" + username + "]";
	}
}
