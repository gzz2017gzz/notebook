package com.gzz.config.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.gzz.config.sms.SmsCodeAuthenticationSecurityConfig;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.apply(smsCodeAuthenticationSecurityConfig)
		.and().formLogin()//
			.loginPage("/login")//
			.loginProcessingUrl("/authentication/form")//
		.and().logout()//
			.logoutUrl("/logout")//
		.and().authorizeRequests()// 如果有允许匿名的url，填在下面
			.antMatchers("/login", "/sms/**", "/authentication/form").permitAll()//
		.anyRequest().authenticated();//
		// 关闭CSRF跨域
		http.csrf().disable();
	}
}
