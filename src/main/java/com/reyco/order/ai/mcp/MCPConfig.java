package com.reyco.order.ai.mcp;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MCPConfig {
	
	@Bean
	public ToolCallbackProvider orderMCPTools(OrderMCPService orderMCPService) {
		return MethodToolCallbackProvider.builder().toolObjects(orderMCPService).build();
	}
}
