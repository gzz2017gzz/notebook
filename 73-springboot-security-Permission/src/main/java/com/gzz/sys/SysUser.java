package com.gzz.sys;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysUser extends User {
	private static final long serialVersionUID = 1L;

	public SysUser(Integer id, String userName, String password, List<SysRole> roles) {
		super(userName, password, roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList()));
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}

	private Integer id;
	private String userName;
	private String password;
	private List<SysRole> roles;
}
