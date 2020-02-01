package com.gzz.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	private static final Set<SysUser> users = new HashSet<>();

	static {// 数据库
		users.add(new SysUser(1L, "gzz", "123456", Arrays.asList("ROLE_ADMIN", "ROLE_DOCKER")));
		users.add(new SysUser(2L, "fbb", "123456", Arrays.asList("ROLE_ADMIN", "ROLE_DOCKER")));
		users.add(new SysUser(3L, "hdd", "123456", Arrays.asList("ROLE_ADMIN", "ROLE_DOCKER")));
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// 查询
		SysUser user = users.stream().filter(o -> o.getUserName().equals(userName)).findFirst().orElse(new SysUser());
		if (user == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		// 把角色放入认证器里
		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
		return new User(user.getUserName(), user.getPassword(), authorities);
	}

}
