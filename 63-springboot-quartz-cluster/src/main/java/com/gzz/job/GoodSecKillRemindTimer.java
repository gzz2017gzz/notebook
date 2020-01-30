package com.gzz.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 商品秒杀提醒定时器 为关注该秒杀商品的用户进行推送提醒
 */
@Slf4j
public class GoodSecKillRemindTimer extends QuartzJobBean {

	/**
	 * 任务指定逻辑
	 */
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		// 获取任务详情内的数据集合
		JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
		// 获取商品编号
		Long goodId = dataMap.getLong("goodId");
		log.info("分布式节点quartz-cluster-node-first，开始处理秒杀商品：{}，关注用户推送消息.", goodId);

		// .../
	}
}
