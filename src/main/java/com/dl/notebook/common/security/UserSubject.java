package com.dl.notebook.common.security;

import com.dl.notebook.sys.sysuser.SysUser;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserSubject extends User {
	private SysUser user;
	private static final long serialVersionUID = 1L;

	public void setUser(SysUser user) {
		this.user = user;
	}

	public SysUser getUser() {
		return this.user;
	}

	public UserSubject(SysUser user, List<SimpleGrantedAuthority> auths) {
		super(user.getName(), user.getPassword(), auths);
		this.user = user;
	}
}
