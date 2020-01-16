package com.gzz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gzz.server.TimeServer;

//参考文档：https://blog.csdn.net/wangmx1993328/article/details/83036285
@SpringBootApplication
public class Application implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private TimeServer nettyServer;

	@Override
	public void run(String... args)  {
		nettyServer.bind(9898);
	}
}
