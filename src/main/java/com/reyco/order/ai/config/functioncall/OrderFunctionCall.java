package com.reyco.order.ai.config.functioncall;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import com.reyco.order.ai.domain.Order;
import com.reyco.order.ai.service.OrderService;

/**
 * 实现functioncall的方式1
 * 
 * @author reyco
 *
 */
//@Configuration
public class OrderFunctionCall {

	@Autowired
	private OrderService orderService;

	@Bean
	@Description("处理订单查询")
	public Function<String, Order> queryOrder() {
		return orderNumber -> {
			System.out.println("查询订单,orderNumber："+orderNumber);
			return orderService.get(orderNumber);
		};
	}

	@Bean
	@Description("处理订单取消")
	public Function<String, String> cancelOrder() {
		return orderNumber -> {
			System.out.println("取消订单,orderNumber："+orderNumber);
			orderService.delete(orderNumber);
			return "取消成功";
		};
	}
}
