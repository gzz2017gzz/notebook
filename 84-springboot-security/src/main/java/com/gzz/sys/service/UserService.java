package com.gzz.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gzz.sys.bean.MyUserBean;
import com.gzz.sys.mapper.MyUserMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * UserDetailsService的实现类，用于在程序中引入一个自定义的AuthenticationProvider，实现数据库访问模式的验证
 */
@Slf4j
@Service
public class UserService implements UserDetailsService {
	@Autowired
	private MyUserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("username={}", username);
		MyUserBean userBean = mapper.selectByUsername(username);
		log.info("userBean={}", userBean);
		if (userBean == null) {
			throw new UsernameNotFoundException("数据库中无此用户！");
		}
		return userBean;
	}
}
