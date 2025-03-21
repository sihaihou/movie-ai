package com.reyco.order.ai.config.functioncall;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reyco.order.ai.config.functioncall.QueryOrderFunctionCallService.QueryOrderRequest;
import com.reyco.order.ai.domain.Order;
import com.reyco.order.ai.service.OrderService;

/**
 * 查询订单
 * @author reyco
 *
 */
public class QueryOrderFunctionCallService implements Function<QueryOrderRequest, Order> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private OrderService orderService;
	
	public QueryOrderFunctionCallService(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@Override
	public Order apply(QueryOrderRequest request) {
		logger.debug("查询订单,request：{}",request);
		
		return orderService.get(request.orderNumber);
	}
	public record QueryOrderRequest(String orderNumber) {};
	public record QueryOrderResponse(Order order) {};
}
