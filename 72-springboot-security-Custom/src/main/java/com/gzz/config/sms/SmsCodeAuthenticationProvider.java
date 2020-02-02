package com.gzz.config.sms;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 短信登陆鉴权 Provider，要求实现 AuthenticationProvider 接口 参考DaoAuthenticationProvider
 */
@Slf4j
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

		String mobile = (String) authenticationToken.getPrincipal();

		checkSmsCode(mobile);

		UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);

		// 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());

		authenticationResult.setDetails(authenticationToken.getDetails());

		return authenticationResult;
	}

	private void checkSmsCode(String mobile) {
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

		String inputCode = request.getParameter("smsCode");

		// 这里是验证码的验证方式
		JSONObject smsCode = JSONObject.parseObject((String) request.getSession().getAttribute("smsCode"));
		if (smsCode == null) {
			throw new BadCredentialsException("未检测到申请验证码");
		}

		String applyMobile = smsCode.getString("mobile");

		int code = smsCode.getInteger("code");
		log.info("applyMobile={},mobile={}", applyMobile, mobile);
		log.info("code={},inputCode={}", code, inputCode);
		if (!applyMobile.equals(mobile)) {

			throw new BadCredentialsException("申请的手机号码与登录手机号码不一致");
		}
		if (code != Integer.parseInt(inputCode)) {
			throw new BadCredentialsException("验证码错误");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
}
