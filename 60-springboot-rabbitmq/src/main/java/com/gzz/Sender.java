package com.gzz;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		this.rabbitTemplate.convertAndSend("hello", "我的测试实例:" + new Date());
	}

}