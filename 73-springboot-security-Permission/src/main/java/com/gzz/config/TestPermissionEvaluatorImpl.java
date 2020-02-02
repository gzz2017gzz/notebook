package com.gzz.config;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

interface TestPermissionEvaluator {
	boolean check(String user);
}

@Slf4j
@Service("testPermissionEvaluator")
public class TestPermissionEvaluatorImpl implements TestPermissionEvaluator {
	public boolean check(String user) {
		log.info("进入了自定义的匹配器user={}", user);
		if (user.equals("gzz"))
			return true;
		return false;
	}
}
