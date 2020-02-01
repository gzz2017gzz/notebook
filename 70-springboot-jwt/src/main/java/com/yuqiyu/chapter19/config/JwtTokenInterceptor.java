package com.yuqiyu.chapter19.config;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yuqiyu.chapter19.entity.TokenInfoEntity;
import com.yuqiyu.chapter19.jpa.TokenJPA;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtTokenInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TokenJPA tokenDao;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

		// 自动排除生成token的路径,并且如果是options请求是cors跨域预请求，设置allow对应头信息
		if (request.getRequestURI().equals("/token") || RequestMethod.OPTIONS.toString().equals(request.getMethod())) {
			return true;
		}

		// 其他请求获取头信息
		final String authHeader = request.getHeader("X-YAuth-Token");
		try {
			// 如果没有header信息
			if (authHeader == null || authHeader.trim() == "") {
				throw new SignatureException("not found X-YAuth-Token.");
			}

			// 获取jwt实体对象接口实例
			final Claims claims = Jwts.parser().setSigningKey("HengYuAuthv1.0.0").parseClaimsJws(authHeader).getBody();
			// 从数据库中获取token
			TokenInfoEntity token = tokenDao.findByAppId(claims.getSubject());
			// 数据库中的token值
			String tokenval = new String(token.getToken());
			// 如果内存中不存在,提示客户端获取token
			if (tokenval == null || tokenval.trim() == "") {
				throw new SignatureException("not found token info, please get token agin.");
			}
			// 判断内存中的token是否与客户端传来的一致
			if (!tokenval.equals(authHeader)) {
				throw new SignatureException("not found token info, please get token agin.");
			}
		}
		// 验证异常处理
		catch (SignatureException | ExpiredJwtException e) {
			// 输出对象
			PrintWriter writer = response.getWriter();

			// 输出error消息
			writer.write("need refresh token");
			writer.close();
			return false;
		}
		// 出现异常时
		catch (final Exception e) {
			// 输出对象
			PrintWriter writer = response.getWriter();
			// 输出error消息
			writer.write(e.getMessage());
			writer.close();
			return false;
		}
		return true;
	}

//	private <T> T getDAO(Class<T> clazz, HttpServletRequest request) {
//		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//		return factory.getBean(clazz);
//	}

}
