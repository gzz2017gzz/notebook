package com.gzz.config.authentication;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.gzz.sys.SysRole;
import com.gzz.sys.SysUser;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
	@Override
	public boolean hasPermission(Authentication authentication, Object targetRole, Object targetPermission) {
		List<SysRole> roles = ((SysUser) authentication.getPrincipal()).getRoles();
		for (SysRole role : roles) {
			if (role.getPermissions().contains(targetPermission))
				return true;
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
		return false;
	}
}
