package com.dl.notebook.common.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	private Log logger = LogFactory.getLog(getClass());

	@RequestMapping({ "/login" })
	public String login(@RequestParam(name = "error", required = false) String error) {
		if (error != null) {
			this.logger.info(error);
		}
		return "login";
	}

	@RequestMapping({ "/" })
	public String index() {
		return "index";
	}

	@RequestMapping({ "/logout" })
	public void logout() {
	}
}
