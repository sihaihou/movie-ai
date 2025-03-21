package com.reyco.order.ai.mcp;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.reyco.order.ai.domain.Order;
import com.reyco.order.ai.service.OrderService;

//@Service
public class OrderMCPService {

	@Autowired
	private OrderService orderService;
	
	@Tool(description = "查询订单")
	public Order queryOrderInfo(@ToolParam(description = "订单号") String orderNumber) {
		return orderService.get(orderNumber);
	}
	
}
