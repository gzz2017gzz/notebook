package com.gzz.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gzz.sys.UserJPA;

@Slf4j
@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserJPA userJPA;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("username={}",username);
		UserEntity user = userJPA.findByUsername(username);
		if (user == null) {
			log.info("1");
			throw new UsernameNotFoundException("未查询到用户：" + username + "信息！");
		}
		return user;
	}
}
