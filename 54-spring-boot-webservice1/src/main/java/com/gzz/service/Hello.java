package com.gzz.service;

import javax.jws.WebService;

@WebService
public class Hello {
	public String say(String city) {
		return "城市名称为：" + city;
	}
}
