package com.reyco.order.ai.config;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 记忆配置
 * @author reyco
 *
 */
@Configuration
public class ChatMemoryConfig {
	
	@Bean
	public ChatMemory chatMemory() {
		return new InMemoryChatMemory();
	}
	
}
