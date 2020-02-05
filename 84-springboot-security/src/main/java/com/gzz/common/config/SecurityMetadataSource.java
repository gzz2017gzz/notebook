package com.gzz.common.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.gzz.sys.bean.MyResourceBean;
import com.gzz.sys.mapper.ResourceMapper;

/** 自定义的元数据源类，用来提供鉴权过程中，访问资源所需的角色 */
@Component
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private ResourceMapper resourceMapper; // 本方法返回访问资源所需的角色集合
	AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();// 从object中得到需要访问的资源，即网址
		for (MyResourceBean resource : resourceMapper.selectAllResource()) {// 从数据库中得到所有资源，以及对应的角色
			if (antPathMatcher.match(resource.getUrl(), requestUrl) && resource.getRolesArray().length > 0) { // 首先进行地址匹配
				return SecurityConfig.createList(resource.getRolesArray());
			}
		}
		return SecurityConfig.createList();//返回一个空集合
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
}
