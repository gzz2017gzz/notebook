package com.gzz.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 商品添加定时任务实现类
 */
@Slf4j
public class GoodAddTimer extends QuartzJobBean {

	/**
	 * 定时任务逻辑实现方法 每当触发器触发时会执行该方法逻辑
	 * 
	 * @param jobExecutionContext 任务执行上下文
	 */
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		log.info("分布式节点quartz-cluster-node-first，商品添加完成后执行任务，任务时间：{}", new Date());
	}
}
