package com.gzz.user;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	private static final Set<User> users = new HashSet<>();
	static {
		users.add(new User(1L, "gzz", "a3caed36f0fe5a01e5f144db8927235e", Arrays.asList("ADMIN", "DOCKER")));
		users.add(new User(1L, "fbb", "a3caed36f0fe5a01e5f144db8927235e", Arrays.asList("ADMIN", "DOCKER")));
		users.add(new User(1L, "hdd", "a3caed36f0fe5a01e5f144db8927235e", Arrays.asList("ADMIN", "DOCKER")));
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = users.stream().filter(o -> o.getUsername().equals(userName)).findFirst().get();
		if (user == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		return user;
	}

}
