package com.gzz.config.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccessDeniedAuthenticationHandler implements AccessDeniedHandler {
	private final ObjectMapper objectMapper;

	public AccessDeniedAuthenticationHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
		log.info("没有权限");
		httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
	}
}
