package com.gzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * quartz定时任务分布式节点-1 程序启动入口
 */
//@Slf4j
//@EnableScheduling
@SpringBootApplication
public class Chapter39Application {
	public static void main(String[] args) {
		SpringApplication.run(Chapter39Application.class, args);
//		log.info("【【【【【【定时任务分布式节点 - quartz-cluster-node-first 已启动】】】】】】");
	}
}
