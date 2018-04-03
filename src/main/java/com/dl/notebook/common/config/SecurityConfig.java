package com.dl.notebook.common.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dl.notebook.common.security.UserSecurityService;
import com.dl.notebook.common.util.MD5Util;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private UserSecurityService securityService;

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated()//
				.and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll()//
				.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login")
				.invalidateHttpSession(true).clearAuthentication(true);//
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityService).passwordEncoder(new PasswordEncoder() {

			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Util.encode((String) rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				logger.info(encode(rawPassword));
				return encodedPassword.equals(encode(rawPassword));
			}
		});
	}
}
