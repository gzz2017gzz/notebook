package com.example;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setUsername("gzz");
		factory.setPassword("gzz");
		Connection connection = factory.newConnection();// 获取连接
		Channel channel = connection.createChannel();// 获取通道
		channel.queueDeclare(QueueName.QUEUE_ONE, false, false, false, null);
		String message = "this is a message";
		channel.basicPublish("", QueueName.QUEUE_ONE, null, message.getBytes());
		channel.close();
		connection.close();
	}
}
