package com.gzz.test;

import com.gzz.service.Hello;
import com.gzz.service.HelloService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {
	public static void main(String[] args) {
		HelloService service = new HelloService();
		Hello example = service.getHelloPort();
		String city = example.say("上海");
		log.info("客户端调用webservice信息为：{}", city);
	}
}
