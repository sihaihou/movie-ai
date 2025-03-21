package com.reyco.order.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * ChatClient
 * author reyco
 *
 */
@Configuration
public class ChatClientConfig {
	/**
	 * 
	 * @param chatModel
	 * @param chatMemory
	 * @param vectorStore
	 * @return
	 */
	@Bean
	public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory , VectorStore vectorStore) {
		//记住你的名字是：海哥。
		//现在的时间是{currentTime}。
		//在更改或取消之前，请先获取取消信息并且用户确认之后才进行更改或取消。
		return ChatClient.builder(chatModel)
				.defaultSystem(
						"""
							请讲中文。
							现在的时间是{currentTime}。
							当前用户Id是{userId}。
							这里是星辉电影院客户聊天支持代理。请以友好、乐于助人的方式回答。
							您正在通过在线聊天系统与客户互动。
							在提供有关查询、编辑、取消订单信息之前，请必须从用户处获取以下信息：订单号。
							在更改或取消之前，请先获取取消信息并且用户确认之后才进行更改或取消。
							在提供有关创建订单信息之前，请必须从用户处获取以下信息：电影名称。
							在提供有关查询我的订单信息之前，不需要从用户获取任何信息。
							在询问用户之前，请检查消息历史记录以获取此信息。
						"""
				).defaultAdvisors(
			        new MessageChatMemoryAdvisor(chatMemory),
			        new QuestionAnswerAdvisor(vectorStore),
			        new SimpleLoggerAdvisor()
			    )
				.defaultTools("queryMyOrder","queryOrder","createOrder","editOrder","cancelOrder")
			    .build();
	}
}
