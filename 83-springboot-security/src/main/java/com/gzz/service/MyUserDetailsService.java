package com.gzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gzz.config.MyUserBean;
import com.gzz.mapper.MyUserMapper;

/** UserDetailsService的实现类，用于在程序中引入一个自定义的AuthenticationProvider，实现数据库访问模式的验证 */
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private MyUserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUserBean userBean = mapper.selectByUsername(username);
		if (userBean == null) {
			throw new UsernameNotFoundException("数据库中无此用户！");
		}
		return userBean;
	}
}
