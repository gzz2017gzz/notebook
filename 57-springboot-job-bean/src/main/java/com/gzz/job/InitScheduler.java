package com.gzz.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * @类说明：初始化调度器
 */
@Component
public class InitScheduler implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private Scheduler scheduler;

	@Override
	public void run(String... args) {
		try {
			List<User> list = new ArrayList<>();
			list.add(new User(1, "1"));
			list.add(new User(2, "2"));
			list.add(new User(3, "李四"));
			list.add(new User(4, "4"));
			JobDataMap map = new JobDataMap();
			map.put("list", list);
			log.info("初始化参数,map={}", JSON.toJSONString(map));
			scheduler.scheduleJob(JobBuilder.newJob(TestTask.class).withIdentity(new JobKey("job001", "test")).usingJobData(map)// 任务调度过程传复杂参数
					.build(), TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?")).build());

		} catch (SchedulerException e) {
			log.error("初始化异常", e);
		}
	}
}
