package com.gzz.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/demo")
public class DemoController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/get")
	public Map<String, Object> get(@RequestParam String name, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		log.info("name={}", name);
		map.put("name", name);
		log.info("session-id={}", request.getSession().getId());
		map.put("session-id", request.getSession().getId());
		Enumeration<String> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			log.info("header: {}, {}", key, request.getHeader(key));
			map.put(key, request.getHeader(key));
		}
		return map;
	}
}
