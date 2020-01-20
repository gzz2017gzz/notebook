package com.gzz.job;

import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@Component
public class TestTask extends QuartzJobBean {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private Scheduler scheduler;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) {
		JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
		String json = JSON.toJSONString(map.get("list"));
		List<User> list = JSONArray.parseArray(json, User.class);
		log.info("接收到的参数是json={}", json);
		log.info("接收到的参数是list={}", list);

		try {
			scheduler.getJobGroupNames().forEach(group -> {// 用来监控全部任务状态
				log.info("===>groupName={}", group);
				try {
					scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group)).forEach(jobKey -> {
						log.info("groupName={},jobName={}", jobKey.getGroup(), jobKey.getName());
					});
				} catch (SchedulerException e) {
					log.error("遍历异常", e);
					e.printStackTrace();
				}
			});
		} catch (SchedulerException e) {
			log.error("初始化异常", e);
		}

	}
}
