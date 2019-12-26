package com.lance.net.server;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lance.net.server.common.ChatServer;

import io.netty.channel.ChannelFuture;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private ChatServer chatServer;
	private Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9090);
		ChannelFuture future = chatServer.start(address);
		logger.info("netty server is started on 127.0.0.1:9090 !");
		Runtime.getRuntime().addShutdownHook(new Thread(() -> chatServer.destroy()));
		future.channel().closeFuture().syncUninterruptibly();
	}
}