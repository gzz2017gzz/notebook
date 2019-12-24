package com.gzz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gzz.dao.BookDao;
 
@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
	@Autowired
	private BookDao dao;

	@Override
	public void run(String... args) throws Exception {
		logger.info(".... Fetching books");
		logger.info("isbn-1234 -->" + dao.getByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + dao.getByIsbn("isbn-4567"));
		logger.info("isbn-1234 -->" + dao.getByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + dao.getByIsbn("isbn-4567"));
		logger.info("isbn-1234 -->" + dao.getByIsbn("isbn-1234"));
		logger.info("isbn-1234 -->" + dao.getByIsbn("isbn-1234"));
	}

}