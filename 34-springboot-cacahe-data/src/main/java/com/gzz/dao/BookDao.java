package com.gzz.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.gzz.config.Dao;

@Dao
public class BookDao {
	private static final Logger logger = LoggerFactory.getLogger(BookDao.class);

	@Cacheable("books")
	public Book getByIsbn(String isbn) {
		logger.info("没有走缓存");
		return new Book(isbn, "Some book");
	}
}