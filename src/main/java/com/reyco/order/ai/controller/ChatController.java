package com.reyco.order.ai.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("chat")
public class ChatController {

	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ChatClient chatClient;
	
	@GetMapping(value = "ai", produces = "text/html;charset=UTF-8")
	public Flux<String> ai(@RequestParam(value = "k", defaultValue = "您好") String k,HttpServletRequest request) {
		String conversationId = request.getHeader("token");
		logger.debug("k:{},token:{}",k,conversationId);
		
		return chatClient.prompt()
				.system(promptSystemSpec -> promptSystemSpec.param("currentTime", new SimpleDateFormat().format(Calendar.getInstance().getTime())))
				.system(promptSystemSpec -> promptSystemSpec.param("userId",conversationId))
				.advisors(advisorSpec -> advisorSpec.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY,conversationId).param(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
				.user(k)
				.stream()
				.content();
	}
}
