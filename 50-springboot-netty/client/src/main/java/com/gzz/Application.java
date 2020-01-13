package com.gzz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gzz.client.TimeClient;

//参考文档：https://blog.csdn.net/wangmx1993328/article/details/83036285
@SpringBootApplication
public class Application implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private static ExecutorService pool = Executors.newFixedThreadPool(5);
	@Autowired
	private TimeClient client;

	@Override
	public void run(String... args) {
		/**
		 * 使用 3 个线程模拟三个客户端
		 */
		for (int i = 0; i < 3; i++) {
			pool.submit(() -> client.connect("localhost", 9898));
		}

	}
}
