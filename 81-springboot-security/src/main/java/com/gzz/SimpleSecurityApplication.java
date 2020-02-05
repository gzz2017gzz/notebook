package com.gzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSecurityApplication.class, args);
	}
}
/*
simple_security:最简单的网站登录验证入门 https://zhuanlan.zhihu.com/p/47224331
normal_security:定制化的网站登录验证  https://zhuanlan.zhihu.com/p/47395352
security_withdb：使用数据库的网站登录验证 https://zhuanlan.zhihu.com/p/47584036
authorization_withdb:介绍自定义鉴权流程的 https://zhuanlan.zhihu.com/p/47873694

https://github.com/apkkids/spring_security_exam
/*