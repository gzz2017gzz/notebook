package com.gzz.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TestController {
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
