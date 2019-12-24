package com.proxy.jdkinvoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvokeHandler implements InvocationHandler {
	private Object target;

	public InvokeHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object o, Method method, Object[] args) throws Throwable {
		System.out.println("++++++before " + method.getName() + "++++++");
		Object result = method.invoke(target, args);
		System.out.println("++++++after " + method.getName() + "++++++");
		return result;
	}
}