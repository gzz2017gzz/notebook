package com.gzz.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.gzz.config.interceptor.TestInterceptor;

/**
 * 自定义配置类实现JavaBean注解形式配置
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Autowired
	private TestInterceptor testInterceptor;

	/**
	 * 拦截器配置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(testInterceptor).addPathPatterns("/**");
	}

//	/**
//	 * 跨域CORS配置
//	 */
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/cors/**").allowedHeaders("*").allowedMethods("POST", "GET").allowedOrigins("*");
//	}

	/**
	 * 视图控制器配置
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/index");
	}

	/**
	 * 配置请求视图映射
	 */
	@Bean
	public InternalResourceViewResolver resourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		// 请求视图文件的前缀地址
		internalResourceViewResolver.setPrefix("/");
		// 请求视图文件的后缀
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
//
//	/**
//	 * 视图配置
//	 */
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		registry.viewResolver(resourceViewResolver());
//		registry.jsp("/WEB-INF/jsp/", ".jsp");
//	}

	/**
	 * 消息内容转换配置 配置fastJson返回json转换
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 调用父类的配置
		// 创建fastJson消息转换器
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		// 创建配置类
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		// 修改配置返回内容的过滤
		fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		// 将fastjson添加到视图消息转换器列表内
		converters.add(fastConverter);
	}
}
