package com.gzz;

import javax.xml.ws.Endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gzz.service.Hello;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);

		String url = "http://localhost:8088/ws";
		Endpoint.publish(url, new Hello());
		log.info("webservice 发布成功");

		// 命令行执行： wsimport -encoding utf-8 –d . -keep http://localhost:8088/ws?wsdl
	}
}
