package com.gzz.starter.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gzz.starter.properties.DemoProperties;
import com.gzz.starter.service.DemoService;

@Configuration
@EnableConfigurationProperties(DemoProperties.class)
/** 当属性文件中有前缀为demo的配置才会去创建DemoService **/
@ConditionalOnProperty(prefix = "demo", name = "isopen", havingValue = "true")
public class DemoConfig {
	private static final Log logger = LogFactory.getLog(DemoConfig.class);
	@Autowired
	private DemoProperties demoProperties;

	@Bean
	public DemoService demoService() {
		logger.info("demoService-bean被创建");
		return new DemoService(demoProperties.getSayWhat(), demoProperties.getToWho());
	}
}
