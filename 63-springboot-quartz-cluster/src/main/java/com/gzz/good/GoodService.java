package com.gzz.good;

import java.util.Date;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzz.job.GoodAddTimer;
import com.gzz.job.GoodSecKillRemindTimer;
import com.gzz.job.GoodStockCheckTimer;

/**
 * 商品业务逻辑
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class GoodService {
	/**
	 * 注入任务调度器
	 */
	@Autowired
	private Scheduler scheduler;
	/**
	 * 商品数据接口
	 */
	@Autowired
	private GoodRepository goodInfoRepository;

	/**
	 * 保存商品基本信息
	 */
	public Long saveGood(GoodEntity good) throws Exception {
		goodInfoRepository.save(good);
		// 构建创建商品定时任务
		buildCreateGoodTimer();
		// 构建商品库存定时任务
		buildGoodStockTimer();
		// 构建商品描述提醒定时任务
		buildGoodSecKillRemindTimer(good.getId());
		return good.getId();
	}

	/**
	 * 构建创建商品定时任务
	 */
	public void buildCreateGoodTimer() throws Exception {
		// 设置开始时间为1分钟后
		long startAtTime = System.currentTimeMillis() + 1000 * 60;
		// 任务名称
		String name = UUID.randomUUID().toString();
		// 任务所属分组
		String group = GoodAddTimer.class.getName();
		// 创建任务
		JobDetail jobDetail = JobBuilder.newJob(GoodAddTimer.class).withIdentity(name, group).build();
		// 创建任务触发器
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTime)).build();
		// 将触发器与任务绑定到调度器内
		scheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 * 构建商品库存定时任务
	 */
	public void buildGoodStockTimer() throws Exception {
		// 任务名称
		String name = UUID.randomUUID().toString();
		// 任务所属分组
		String group = GoodStockCheckTimer.class.getName();

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
		// 创建任务
		JobDetail jobDetail = JobBuilder.newJob(GoodStockCheckTimer.class).withIdentity(name, group).build();
		// 创建任务触发器
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
		// 将触发器与任务绑定到调度器内
		scheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 * 构建商品秒杀提醒定时任务 设置五分钟后执行
	 */
	public void buildGoodSecKillRemindTimer(Long goodId) throws Exception {
		// 任务名称
		String name = UUID.randomUUID().toString();
		// 任务所属分组
		String group = GoodSecKillRemindTimer.class.getName();
		// 秒杀开始时间
		long startTime = System.currentTimeMillis() + 1000 * 5 * 60;
		JobDetail jobDetail = JobBuilder.newJob(GoodSecKillRemindTimer.class).withIdentity(name, group).build();

		// 设置任务传递商品编号参数
		jobDetail.getJobDataMap().put("goodId", goodId);

		// 创建任务触发器
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startTime)).build();
		// 将触发器与任务绑定到调度器内
		scheduler.scheduleJob(jobDetail, trigger);
	}
}
