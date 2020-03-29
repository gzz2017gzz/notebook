package com.gzz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping(value = { "", "/" })
	public String index(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "/page/login.btl";
		} else {
			return "/page/index.btl";
		}
	}
}
