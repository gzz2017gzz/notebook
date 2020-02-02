package com.gzz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.util.DigestUtils;

import com.gzz.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private FailureAuthenticationHandler failureAuthenticationHandler;
	@Autowired
	private SuccessAuthenticationHandler successAuthenticationHandler;
	@Autowired
	private PersistentTokenRepository persistentTokenRepository;
	@Autowired
	private UserService userService;

	/**
	 * 加密解密 这里是自定义MD5 security内置了多种加密 可以直接new 接口出来
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return md5((String) rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(md5((String) rawPassword));
			}
		});
	}

	private static final String SALT = "tamboo";

	public static String md5(String password) {
		password = password + SALT;
		return DigestUtils.md5DigestAsHex(password.getBytes());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.httpBasic() //httpBasic 登录
		// 这里要说明一下 .anyRequest().authenticated() 是不能使用rememberMe 功能的
		//
		http.formLogin()//
				.failureHandler(failureAuthenticationHandler) // 自定义登录失败处理
				.successHandler(successAuthenticationHandler) // 自定义登录成功处理
				.and().logout().logoutUrl("/logout")//
				.and().formLogin()//
				.loginPage("/login")//
				.loginProcessingUrl("/authentication/form") // 自定义登录路径
				.and().authorizeRequests()// 对请求授权
				.antMatchers("/login", "/authentication/require", "/authentication/form").permitAll().anyRequest().authenticated()
//                .and().rememberMe().rememberMeCookieName("reberm").tokenValiditySeconds(3600)  //使用 cookie的形势去做记住我
				.and().rememberMe()//
				.tokenValiditySeconds(3600)//
				.tokenRepository(persistentTokenRepository)//
				.and().csrf().disable();// 禁用跨站攻击
	}

	public static void main(String[] args) {
		log.info(md5("123456"));
	}
}