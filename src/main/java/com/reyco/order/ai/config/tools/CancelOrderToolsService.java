package com.reyco.order.ai.config.tools;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reyco.order.ai.config.tools.CancelOrderToolsService.CancelOrderToolsRequest;
import com.reyco.order.ai.config.tools.CancelOrderToolsService.CancelOrderToolsResponse;
import com.reyco.order.ai.service.OrderService;

public class CancelOrderToolsService implements Function<CancelOrderToolsRequest, CancelOrderToolsResponse> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private OrderService orderService;
	
	public CancelOrderToolsService(OrderService orderService) {
		this.orderService = orderService;
	}
	
    public CancelOrderToolsResponse apply(CancelOrderToolsRequest request) {
    	logger.debug("CancelOrder,request:{}",request);
    	
    	orderService.delete(request.orderNumber);
        return new CancelOrderToolsResponse("Success");
    }
    
    public record CancelOrderToolsRequest(String orderNumber) {}
    public record CancelOrderToolsResponse(String message) {}
	
}
