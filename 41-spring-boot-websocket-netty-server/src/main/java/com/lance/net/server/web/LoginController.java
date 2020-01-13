package com.lance.net.server.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lance.net.server.module.UserInfo;

@Controller
public class LoginController {
	/**
	 * 跳转登录页面
	 */
	@RequestMapping({ "", "/", "index" })
	public String index() {
		return "index.jsp";
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String doLogin(UserInfo user) {
		UserInfo.map.put(user.getPhone(), user);
		return "redirect:/chat/list?token=" + user.getPhone();
	}
}