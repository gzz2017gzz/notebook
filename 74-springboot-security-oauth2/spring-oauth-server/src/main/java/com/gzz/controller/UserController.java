package com.gzz.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	Logger log = LoggerFactory.getLogger(getClass());

	/** 资源服务器提供的受保护接口 */
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		log.info("principal={}", principal);
		return principal;
	}

}
