package com.dl.notebook.common.security;

import com.dl.notebook.sys.sysuser.SysUser;
import java.security.Principal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public abstract class PrincipalAction {
	protected UserSubject getUserSubject(Principal principal) {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
		return (UserSubject) token.getPrincipal();
	}

	protected SysUser getUser(Principal principal) {
		return getUserSubject(principal).getUser();
	}
}
