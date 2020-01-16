package com.gzz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static Logger log = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {
		User user = (User) OsCache.getInstance().get("user_", -1);
		if (user != null) {
			log.info(user.toString());
		} else {
			log.info("no user.");
			System.out.println();
			user = new User();
			user.setName("wulinfeng");
			user.setAge(34);
			user.setSex(1);
			OsCache.getInstance().put("user_", user, -1);
			user = (User) OsCache.getInstance().get("user_", -1);
			log.info(user.toString());
			System.out.println();
		}
	}
}
