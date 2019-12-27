package com.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import com.proxy.service.UserService;
import com.proxy.service.impl.UserServiceImpl;

public class TestCglib {
	public static void main(String[] args) {
		CglibProxy cglibProxy = new CglibProxy();

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserServiceImpl.class);
		enhancer.setCallback(cglibProxy);

		UserService o = (UserService) enhancer.create();
		o.getName(1);
		o.getAge(1);
	}
}