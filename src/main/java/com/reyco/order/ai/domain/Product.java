package com.reyco.order.ai.domain;

import java.io.Serializable;

/**
 * 产品信息
 * @author reyco
 *
 */
public class Product implements Serializable{
	private static final long serialVersionUID = -8878981588597916698L;
	private String productId;
	private String name;
	private Integer price;
	private String time;
	private String remark;
	private Integer theaterNumber;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public Integer getTheaterNumber() {
		return theaterNumber;
	}
	public void setTheaterNumber(Integer theaterNumber) {
		this.theaterNumber = theaterNumber;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", time=" + time
				+ ", remark=" + remark + ", theaterNumber=" + theaterNumber + "]";
	}
}
