package com.gzz.job;

import java.util.Date;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import lombok.extern.slf4j.Slf4j;

/**
 * 商品库存检查定时任务
 */
@Slf4j
public class GoodStockCheckTimer extends QuartzJobBean {
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		log.info("分布式节点quartz-cluster-node-first，执行库存检查定时任务，执行时间：{}", new Date());
	}
}
