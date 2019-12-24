package com.gzz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	@RequestMapping("user/add")
	@ResponseBody
	public User login(@RequestBody User user) {
		System.out.println(user);
		return user;
	}

	@RequestMapping("/")
	public String index() {
		System.out.println("index");
		return "index";
	}

	@RequestMapping("users")
	@ResponseBody
	public User users() {
		return new User();
	}
}
