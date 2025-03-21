package com.reyco.order.ai.config;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Component
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/**")
         .addResourceLocations("classpath:/static/")
         .resourceChain(true)
         .addResolver(new PathResourceResolver() {
             @Override
             protected Resource getResource(@NonNull String resourcePath, @NonNull Resource location) throws IOException {
                 Resource requestedResource = location.createRelative(resourcePath);
                 return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                         : new ClassPathResource("/static/index.html"); // 如果请求的资源不存在，则返回 index.html
             }
         });
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
