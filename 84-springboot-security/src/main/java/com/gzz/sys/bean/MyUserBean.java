package com.gzz.sys.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/** 用户表user对应的类，同时实现了UserDetails接口，成为登录验证的信息类 */
@Data
public class MyUserBean implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String address;
	private String username;
	private String password;
	private String roles;

	/** 从数据库中取出roles字符串后，进行分解，构成一个GrantedAuthority的List返回 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] authorities = roles.split(",");
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
		for (String role : authorities) {
			simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
		}
		return simpleGrantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
