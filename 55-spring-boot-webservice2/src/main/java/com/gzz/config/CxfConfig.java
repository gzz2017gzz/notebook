package com.gzz.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gzz.server.Hello;

@Configuration
public class CxfConfig {

	@Autowired
	private Bus bus;

	@Autowired
	private Hello hello;

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, hello);
		endpoint.publish("/hello");// 接口发布在 /NetbarServices 目录下
		return endpoint;
	}

}
