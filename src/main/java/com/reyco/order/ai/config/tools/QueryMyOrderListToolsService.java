package com.reyco.order.ai.config.tools;

import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reyco.order.ai.config.tools.QueryMyOrderListToolsService.QueryMyOrderListToolsRequest;
import com.reyco.order.ai.config.tools.QueryMyOrderListToolsService.QueryMyOrderListToolsResponse;
import com.reyco.order.ai.domain.Order;
import com.reyco.order.ai.service.OrderService;

public class QueryMyOrderListToolsService implements Function<QueryMyOrderListToolsRequest, QueryMyOrderListToolsResponse> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private OrderService orderService;
	
	public QueryMyOrderListToolsService(OrderService orderService) {
		this.orderService = orderService;
	}
	
    public QueryMyOrderListToolsResponse apply(QueryMyOrderListToolsRequest request) {
    	logger.debug("QueryMyOrder,request:{}",request);
    	
    	List<Order> orders = orderService.getByUserId(request.userId);
        return new QueryMyOrderListToolsResponse(orders);
    }
    
    public record QueryMyOrderListToolsRequest(String userId) {}
    public record QueryMyOrderListToolsResponse(List<Order> orders) {}
	
}
