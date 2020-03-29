package com.gzz;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class User implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean, ApplicationContextAware {
	public User() {
		log.info("User --> 构造函数");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		log.info("BeanFactoryAware --> setBeanFactory");
	}

	@Override
	public void setBeanName(String s) {
		log.info("BeanNameAware --> setBeanName:" + s);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("InitializingBean --> afterPropertiesSet");
	}

	@Override
	public void destroy() throws Exception {
		log.info("DisposableBean --> destroy");

	}

	@PostConstruct
	public void init() {
		log.info("User --> @PostConstruct");
	}

	@PreDestroy
	public void destory() {
		log.info("User --> @PreDestroy");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.info("ApplicationContextAware --> setApplicationContext");
	}

	public void run() {
		log.info("User这个Bean可以使用了");
	}
}
