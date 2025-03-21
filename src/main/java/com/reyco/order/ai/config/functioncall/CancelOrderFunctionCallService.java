package com.reyco.order.ai.config.functioncall;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reyco.order.ai.config.functioncall.CancelOrderFunctionCallService.CancelOrderRequest;
import com.reyco.order.ai.config.functioncall.CancelOrderFunctionCallService.CancelOrderResponse;
import com.reyco.order.ai.service.OrderService;
/**
 * 取消订单
 * 
 * @author reyco
 *
 */
public class CancelOrderFunctionCallService implements Function<CancelOrderRequest, CancelOrderResponse> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderService orderService;

	public CancelOrderFunctionCallService(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@Override
	public CancelOrderResponse apply(CancelOrderRequest request) {
		logger.debug("取消订单,request：", request);
		
		orderService.delete(request.orderNumber);
		return new CancelOrderResponse("取消成功");
	}
	public record CancelOrderRequest(String orderNumber) {};
	public record CancelOrderResponse(String message) {};
}
