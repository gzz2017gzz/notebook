package com.gzz.config;

import javax.sql.DataSource;

import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Configuration
public class QuartzConfiguration {

	@Bean
	public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) {
		schedulerFactoryBean.setOverwriteExistingJobs(true);
		return schedulerFactoryBean.getScheduler();
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, DataSource dataSource) throws Exception {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setJobFactory(jobFactory);
 		schedulerFactoryBean.setDataSource(dataSource);
		return schedulerFactoryBean;
	}

	@Component
	public class AutowiringSpringBeanJobFactory extends AdaptableJobFactory {
		@Autowired
		private AutowireCapableBeanFactory factory;

		@Override
		protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
			Object job = super.createJobInstance(bundle);
			/** 将job实例交付给spring ioc **/
			factory.autowireBean(job);
			return job;
		}
	}
}
