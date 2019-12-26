package com.gzz.service;

import com.gzz.entity.UserEntity;

public interface LoginService {

	/**
	 * 用户登录
	 * @author lance
	 * 2014-6-11下午11:26:05
	 * @param user
	 * @return
	 */
	UserEntity login(UserEntity user);
}
