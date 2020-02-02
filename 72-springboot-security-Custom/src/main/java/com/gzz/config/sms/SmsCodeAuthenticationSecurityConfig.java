package com.gzz.config.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.gzz.user.UserService;

@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	@Autowired
	private UserService userService; // 用我们自己定义的UserDetailsService
	@Autowired
	private AuthenticationSuccessHandler customSuccess;
	@Autowired
	private AuthenticationFailureHandler customFailure;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		// 设置AuthenticationManager
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		// 设置失败成功处理器
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(customSuccess);
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(customFailure);
		// 设置UserDetailsService
		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
		smsCodeAuthenticationProvider.setUserDetailsService(userService);
		// 这里说明要把我们自己写的Provider放在过滤链的哪里
		http.authenticationProvider(smsCodeAuthenticationProvider).addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
