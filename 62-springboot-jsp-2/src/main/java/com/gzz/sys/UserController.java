package com.gzz.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping("find")
	public List<User> findUser() {
		List<User> list = new ArrayList<>();
		User user = new User("1", "你好", 1);
		list.add(new User("11", "你好", 1));
		list.add(user);
		list.add(user);
		list.add(user);

		return list;
	}

}
