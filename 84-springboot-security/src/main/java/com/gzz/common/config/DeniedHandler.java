package com.gzz.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gzz.common.util.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class DeniedHandler implements AccessDeniedHandler {
	@Autowired
	private ObjectMapper mapper;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(mapper.writeValueAsString(new Response<>(500, accessDeniedException.getMessage(), null)));
		out.flush();
		out.close();
	}
}
