package com.gzz.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gzz.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@PostMapping("/login")
	public String login(User user, Map<String, Object> map) {
		map.put("user", user);
		return "/page/index.btl";
	}
}
