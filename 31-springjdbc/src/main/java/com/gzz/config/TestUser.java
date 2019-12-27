package com.gzz.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestUser {
	@Autowired
	private JdbcTemplate jdbcTemplate;// 注入jdbc模板
	private Logger log = LoggerFactory.getLogger(getClass());

	public TestUser() {
		log.info("TestUser");
	}

	@PostConstruct
	public void run() {
		log.info("@PostConstruct");
		int id = -1;
		Object[] obj = new Object[] { id };
		String sql = "select max(name) from user where id>?";
		String name = jdbcTemplate.queryForObject(sql, obj, String.class);
		log.info("name={}", name);
	}
}
