package com.reyco.order.ai.config.tools;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reyco.order.ai.config.tools.CreateOrderToolsService.CreateOrderToolsRequest;
import com.reyco.order.ai.config.tools.CreateOrderToolsService.CreateOrderToolsResponse;
import com.reyco.order.ai.domain.Order;
import com.reyco.order.ai.domain.Product;
import com.reyco.order.ai.service.OrderService;
import com.reyco.order.ai.service.ProductService;

public class CreateOrderToolsService implements Function<CreateOrderToolsRequest, CreateOrderToolsResponse> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private OrderService orderService;
	
	public CreateOrderToolsService(OrderService orderService) {
		this.orderService = orderService;
	}
	
    public CreateOrderToolsResponse apply(CreateOrderToolsRequest request) {
    	logger.debug("CreateOrder,request:{}",request);
    	
    	String userId = request.userId;
		String orderNumber = OrderService.createOrderNumber();
		Product product = ProductService.getProductByName(request.movieName);
		Order createOrder = orderService.createOrder(userId, orderNumber, product.getProductId());
    	Order order = orderService.add(createOrder);
        return new CreateOrderToolsResponse(order);
    }
    
    public record CreateOrderToolsRequest(String userId,String movieName) {}
    public record CreateOrderToolsResponse(Order order) {}
	
}
