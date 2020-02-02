package com.gzz.config.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义失败处理器
 */
@Component
@Slf4j
public class CustomFailure extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		log.info("登录失败！");
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		JSONObject json = Result.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), "验证失败");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json.toJSONString());
	}
}
