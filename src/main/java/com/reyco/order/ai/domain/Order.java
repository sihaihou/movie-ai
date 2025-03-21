package com.reyco.order.ai.domain;

import java.io.Serializable;

public class Order implements Serializable{
	private static final long serialVersionUID = -8791995109145920903L;
	private String userId;
	private String username;
	private String productId;
	private String orderNumber; //订单号
	private String name;  //电影名称
	private Integer theaterNumber; //大厅
	private Integer price; //价格
	private String time;  //时间
	private String remark; //电影描述
	private String createTime;
	private Byte state = 0;  //0正常  1取消
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getTheaterNumber() {
		return theaterNumber;
	}
	public void setTheaterNumber(Integer theaterNumber) {
		this.theaterNumber = theaterNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Byte getState() {
		return state;
	}
	public void setState(Byte state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Order [userId=" + userId + ", username=" + username + ", productId=" + productId + ", orderNumber="
				+ orderNumber + ", name=" + name + ", theaterNumber=" + theaterNumber + ", price=" + price + ", time="
				+ time + ", remark=" + remark + ", createTime=" + createTime + ", state=" + state + "]";
	}
}
