package com.gzz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.gzz.config.MemcachedConfit;

import net.spy.memcached.MemcachedClient;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = MemcachedConfit.class)
public class RepositoryTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemcachedClient client;

	@Test
	public void testSetGet() {
		client.set("testkey", 1000, "666666");
		logger.info("***********testkey={}", client.get("testkey").toString());
	}

}