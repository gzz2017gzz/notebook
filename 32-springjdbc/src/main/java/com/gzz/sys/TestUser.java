package com.gzz.sys;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.gzz.config.SpringConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestUser {
	@Autowired
	private JdbcTemplate jdbcTemplate;// 注入jdbc模板

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(SpringConfig.class);
	}

	@PostConstruct
	public void run() {
		String sql = "select max(name) from user where id>?";
		String name = jdbcTemplate.queryForObject(sql, String.class, -1);
		log.info("name={}", name);
	}
}
