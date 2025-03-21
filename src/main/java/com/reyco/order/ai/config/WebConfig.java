package com.reyco.order.ai.config;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/**")
         .addResourceLocations("classpath:/static/");
	}
	@Override
	public void addCorsMappings(@NonNull CorsRegistry registry) {
		registry.addMapping("/**")
		.allowCredentials(true)
		.allowedOriginPatterns("*")
		.allowedMethods("GET","POST","PUT","PATCH","DELETE","HEAD","OPTIONS")
		.allowedHeaders("*")
		.maxAge(3600);
	}
}
