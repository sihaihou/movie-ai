package com.reyco.order.ai.config.tools;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reyco.order.ai.config.tools.QueryOrderToolsService.QueryOrderToolsRequest;
import com.reyco.order.ai.config.tools.QueryOrderToolsService.QueryOrderToolsResponse;
import com.reyco.order.ai.domain.Order;
import com.reyco.order.ai.service.OrderService;

public class QueryOrderToolsService implements Function<QueryOrderToolsRequest, QueryOrderToolsResponse> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private OrderService orderService;
	
	public QueryOrderToolsService(OrderService orderService) {
		this.orderService = orderService;
	}
	
    public QueryOrderToolsResponse apply(QueryOrderToolsRequest request) {
    	logger.debug("QueryOrder,request:{}",request);
    	
    	Order order = orderService.get(request.orderNumber);
        return new QueryOrderToolsResponse(order);
    }
    
    public record QueryOrderToolsRequest(String orderNumber) {}
    public record QueryOrderToolsResponse(Order order) {}
	
}
