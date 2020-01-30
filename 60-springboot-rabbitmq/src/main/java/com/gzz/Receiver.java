package com.gzz;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RabbitListener(queues = "hello")
public class Receiver {
	private Logger log = LoggerFactory.getLogger(getClass());

	@RabbitHandler
	public void process(String hello) {
		log.info("Receiver : " + hello);
	}

}
