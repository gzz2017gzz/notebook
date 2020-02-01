package com.yuqiyu.chapter19.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class JWTConfiguration extends WebMvcConfigurationSupport {
	@Autowired
	private JwtTokenInterceptor jwtTokenInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtTokenInterceptor).addPathPatterns("/api/**");
	}
}
