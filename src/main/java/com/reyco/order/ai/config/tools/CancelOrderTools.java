package com.reyco.order.ai.config.tools;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import com.reyco.order.ai.config.tools.CancelOrderToolsService.CancelOrderToolsRequest;
import com.reyco.order.ai.config.tools.CancelOrderToolsService.CancelOrderToolsResponse;
import com.reyco.order.ai.config.tools.CreateOrderToolsService.CreateOrderToolsRequest;
import com.reyco.order.ai.config.tools.CreateOrderToolsService.CreateOrderToolsResponse;
import com.reyco.order.ai.config.tools.EditOrderToolsService.EditOrderToolsRequest;
import com.reyco.order.ai.config.tools.EditOrderToolsService.EditOrderToolsResponse;
import com.reyco.order.ai.config.tools.QueryMyOrderListToolsService.QueryMyOrderListToolsRequest;
import com.reyco.order.ai.config.tools.QueryMyOrderListToolsService.QueryMyOrderListToolsResponse;
import com.reyco.order.ai.config.tools.QueryOrderToolsService.QueryOrderToolsRequest;
import com.reyco.order.ai.config.tools.QueryOrderToolsService.QueryOrderToolsResponse;
import com.reyco.order.ai.service.OrderService;

@Configuration
public class CancelOrderTools {
	
	public static final String QUERY_MY_ORDER_TOOL = "queryMyOrder";
	
	public static final String QUERY_ORDER_TOOL = "queryOrder";
	public static final String CREATE_ORDER_TOOL = "createOrder";
	public static final String EDIT_ORDER_TOOL = "editOrder";
	public static final String CANCEL_ORDER_TOOL = "cancelOrder";
	
	
	@Bean(QUERY_MY_ORDER_TOOL)
	@Description("处理查询我的订单")
	public Function<QueryMyOrderListToolsRequest, QueryMyOrderListToolsResponse> queryMyOrder(OrderService orderService) {
		return new QueryMyOrderListToolsService(orderService);
	}
	
	@Bean(QUERY_ORDER_TOOL)
	@Description("处理查询订单")
	public Function<QueryOrderToolsRequest, QueryOrderToolsResponse> queryOrder(OrderService orderService) {
		return new QueryOrderToolsService(orderService);
	}
	
	@Bean(CREATE_ORDER_TOOL)
	@Description("处理创建订单")
	public Function<CreateOrderToolsRequest, CreateOrderToolsResponse> createOrder(OrderService orderService) {
		return new CreateOrderToolsService(orderService);
	}
	
	@Bean(EDIT_ORDER_TOOL)
	@Description("处理编辑订单")
	public Function<EditOrderToolsRequest, EditOrderToolsResponse> editOrder(OrderService orderService) {
		return new EditOrderToolsService(orderService);
	}
	
	@Bean(CANCEL_ORDER_TOOL)
	@Description("处理取消订单")
	public Function<CancelOrderToolsRequest, CancelOrderToolsResponse> cancelOrder(OrderService orderService) {
		return new CancelOrderToolsService(orderService);
	}
	
}
