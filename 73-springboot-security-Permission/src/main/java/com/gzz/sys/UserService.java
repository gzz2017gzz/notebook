package com.gzz.sys;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	public static final Set<SysUser> SYS_USERS = new HashSet<>();

	public static final Set<SysRole> SYS_ROLES = new HashSet<>();

	static {
		SYS_ROLES.add(new SysRole(1, "ROLE_JAVA", Arrays.asList("c", "r", "u", "d")));
		SYS_ROLES.add(new SysRole(2, "ROLE_DOCKER", Arrays.asList("c", "r", "u")));
		SYS_ROLES.add(new SysRole(3, "ROLE_PHP", Arrays.asList("c", "u")));
		SYS_ROLES.add(new SysRole(4, "ROLE_PYTHON", Arrays.asList("c", "d")));
		SYS_ROLES.add(new SysRole(5, "ROLE_CENTOS", Arrays.asList("c", "r", "d")));

		SYS_USERS.add(new SysUser(1, "gzz", "123456", SYS_ROLES.stream().filter(role -> Arrays.asList(1, 2).contains(role.getId())).collect(Collectors.toList())));
		SYS_USERS.add(new SysUser(2, "fbb", "123456", SYS_ROLES.stream().filter(role -> Arrays.asList(3, 2).contains(role.getId())).collect(Collectors.toList())));
		SYS_USERS.add(new SysUser(3, "hdd", "123456", SYS_ROLES.stream().filter(role -> Arrays.asList(4, 5).contains(role.getId())).collect(Collectors.toList())));
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		SysUser sysUser = SYS_USERS.stream().filter(user -> user.getUserName().equals(userName)).findFirst().orElse(null);
		if (sysUser == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		return sysUser;
	}

}
