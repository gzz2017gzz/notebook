package com.lance.crawler;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class CrawlerTest {

	@Test
	public void run() throws InterruptedException, ExecutionException {
		CountDownLatch c = new CountDownLatch(1);
		String[] urls = { "https://www.baidu.com" };
		Crawler crawler = new Crawler();
		crawler.setUrls(urls);
		crawler.parallelDrainQueue(3);
		c.await();
	}

}
