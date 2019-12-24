package com.gzz.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

public class WebInit implements WebApplicationInitializer {
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(SpringConfig.class);// 注册Spring配置文件
		ctx.setServletContext(servletContext);
		Dynamic dynamic = servletContext.addServlet("springMVC", new DispatcherServlet(ctx));// 将spring配置添加到上下文环境中
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
	}
}
