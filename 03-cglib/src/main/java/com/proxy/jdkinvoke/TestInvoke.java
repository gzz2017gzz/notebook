package com.proxy.jdkinvoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.proxy.service.UserService;
import com.proxy.service.impl.UserServiceImpl;

public class TestInvoke {
	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		InvocationHandler handler = new InvokeHandler(userService);
		UserService proxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), handler);
		System.out.println(proxy.getName(1));
		System.out.println(proxy.getAge(1));
	}
}
