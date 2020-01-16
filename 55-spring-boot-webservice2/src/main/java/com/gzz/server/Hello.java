package com.gzz.server;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

@Component
@WebService
public class Hello {
	public String say(String name) {
		return "你好:" + name;
	}
}
