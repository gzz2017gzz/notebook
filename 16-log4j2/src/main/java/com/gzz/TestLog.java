package com.gzz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestLog {
	private final Log logger = LogFactory.getLog(TestLog.class);// 日志类

	@Test
	public void Head() {
		for (int i = 1; i < 1000; i++) {
			logger.info("中华人民共和国!");
			logger.error("中华人民共和国!");
			logger.debug("中华人民共和国!");
		}
	}

}
