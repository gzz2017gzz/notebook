package com.gzz.config;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.spy.memcached.MemcachedClient;

@Configuration
public class MemcachedConfit {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${memcache.ip}")
	private String ip;
	@Value("${memcache.port}")
	private int port;

	@Bean
	public MemcachedClient getMemcachedClient() {
		try {
			return new MemcachedClient(new InetSocketAddress(ip, port));
		} catch (IOException e) {
			logger.error("inint MemcachedClient failed ", e);
			return null;
		}
	}

}