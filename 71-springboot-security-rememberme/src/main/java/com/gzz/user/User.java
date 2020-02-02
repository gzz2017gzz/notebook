package com.gzz.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String password;
	private List<String> roles;

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
		return authorities;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

}
