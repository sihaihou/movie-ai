package com.reyco.order.ai.config;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

/**
 * 代理
 * @author reyco
 *
 */
@Configuration
public class ProxyConfig{
	
	@PostConstruct
	public void buildProxy(){
		String host = "127.0.0.1";
		int port = 7890;
		System.setProperty("proxyType", "4");
		System.setProperty("proxyHost", host);
		System.setProperty("proxyPort", String.valueOf(port));
		System.setProperty("proxySet", "true");
	}
}
