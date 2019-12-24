package com.gzz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gzz.sys.role.Role;
import com.gzz.sys.role.RoleDao;

@SpringBootApplication
public class Application {
	private static Random random = new Random();
	@Autowired
	private RoleDao dao;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public static String getUUID() {
//		return "4f6013f84686446bbf2a9a7c6c5f51d2";
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}

	public static int getint() {
//		return 123468;
		return random.nextInt(100);
	}

//	@PostConstruct
	public void run() {
		dao.queryList();
	}

	public void run2() {
		long start = System.currentTimeMillis();
		List<Role> list = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			list.add(new Role(getUUID(), "姓名" + i, getint(), getUUID(), getUUID(), getUUID(), getUUID(), getUUID(), getUUID(), getUUID(), getUUID(), getUUID(), getUUID(),
					getUUID(), getUUID(), getUUID(), getUUID()));
			if (i % 1000 == 0 || i == 9999) {
				dao.insertBatch(list);
				list.clear();
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}

}
