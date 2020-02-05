package com.gzz.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.gzz.sys.service.UserService;

import lombok.extern.slf4j.Slf4j;

/** Security 配置类 */
@Slf4j
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService;
	@Autowired
	private AccessDecision accessDecision;
	@Autowired
	private SecurityMetadataSource securityMetadataSource;
	@Autowired
	private DeniedHandler deniedHandler;

	private static BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 加入数据库验证类，下面的语句实际上在验证链中加入了一个DaoAuthenticationProvider
		auth.userDetailsService(userService).passwordEncoder(encoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/index.html", "/static/**", "/favicon.ico", "/error", "/login_p");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O object) {
				object.setSecurityMetadataSource(securityMetadataSource);
				object.setAccessDecisionManager(accessDecision);
				return object;
			}
		})//
				.and().formLogin()//
				.loginPage("/login_p").loginProcessingUrl("/login").permitAll().usernameParameter("myusername").passwordParameter("mypassword")// 1.自定义参数名称，与login.html中的参数对应
				.and().logout()//
				.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()//
				.and().csrf().disable().exceptionHandling()//
				.accessDeniedHandler(deniedHandler);
	}

	public static void main(String[] args) {
		log.info("pwd={}", encoder().encode("pwd"));
	}
}
