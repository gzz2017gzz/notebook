package com.gzz.common.config;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/** 本类是鉴权的决策类 */
@Component
public class AccessDecision implements AccessDecisionManager {
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		for (ConfigAttribute attribute : configAttributes) {// 从configAttributes中获取访问资源所需要的角色，它来自MySecurityMetadataSource的getAttributes
			for (GrantedAuthority authority : authentication.getAuthorities()) { // 从authentication中获取当前用户具有的角色逐一进行角色匹配
				if (authority.getAuthority().equals("ROLE_ADMIN")) {
					return; // 用户具有ROLE_ADMIN权限，则可以访问所有资源
				}
				if (authority.getAuthority().equals(attribute.getAttribute())) {
					return; // 匹配成功就直接返回
				}
			}
		}
		throw new AccessDeniedException("你没有访问" + object + "的权限!");// 不能完成匹配
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
