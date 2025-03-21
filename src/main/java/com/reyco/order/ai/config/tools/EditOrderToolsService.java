package com.reyco.order.ai.config.tools;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reyco.order.ai.config.tools.EditOrderToolsService.EditOrderToolsRequest;
import com.reyco.order.ai.config.tools.EditOrderToolsService.EditOrderToolsResponse;
import com.reyco.order.ai.domain.Order;
import com.reyco.order.ai.service.OrderService;

public class EditOrderToolsService implements Function<EditOrderToolsRequest, EditOrderToolsResponse> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private OrderService orderService;
	
	public EditOrderToolsService(OrderService orderService) {
		this.orderService = orderService;
	}
	
    public EditOrderToolsResponse apply(EditOrderToolsRequest request) {
    	logger.debug("EditOrder,request:{}",request);
    	
    	Order oldOrder = orderService.get(request.orderNumber);
		oldOrder.setName(oldOrder.getName()+"-编辑后的订单名称");
		oldOrder.setPrice(oldOrder.getPrice()-10);
		Order newOrder = orderService.edit(oldOrder);
		return new EditOrderToolsResponse(newOrder);
    }
    
    public record EditOrderToolsRequest(String orderNumber) {}
    public record EditOrderToolsResponse(Order order) {}
	
}
