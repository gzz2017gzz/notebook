package com.gzz.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;
/**
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2019-12-24 10:50:00
 **/
@Configuration
@ComponentScan("com.gzz")
@PropertySource("classpath:jdbc.properties")
public class SpringConfig {
	@Bean
	public DataSource getDataSource(Environment env) {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate(@Autowired DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
