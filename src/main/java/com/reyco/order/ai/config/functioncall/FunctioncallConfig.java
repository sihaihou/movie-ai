package com.reyco.order.ai.config.functioncall;

import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reyco.order.ai.service.OrderService;

/**
 * 实现functioncall的方式2
 * @author reyco
 *
 */
//@Configuration
public class FunctioncallConfig {
	
	@Bean
	public FunctionCallback queryOrder(OrderService orderService) {
        return FunctionCallback.builder()
            .function("queryOrder", new QueryOrderFunctionCallService(orderService)) 
            .description("处理订单查询") // (2) function description
            .inputType(String.class) // (3) input type to build the JSON schema
            .build();
	}
	@Bean
	public FunctionCallback cancelOrder(OrderService orderService) {
        return FunctionCallback.builder()
            .function("cancelOrder", new CancelOrderFunctionCallService(orderService)) 
            .description("处理订单取消") // (2) function description
            .inputType(String.class) // (3) input type to build the JSON schema
            .build();
	}
}
