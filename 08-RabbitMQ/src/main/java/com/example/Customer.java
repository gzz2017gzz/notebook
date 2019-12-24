package com.example;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Customer {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setUsername("gzz");
		factory.setPassword("gzz");
		Connection conection = factory.newConnection();
		Channel channel = conection.createChannel();
		channel.queueDeclare(QueueName.QUEUE_ONE, false, false, false, null);
		// 创建消费者，在回调函数中处理结果
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("从队列" + QueueName.QUEUE_ONE + "接受到：" + message);
			}
		};
		channel.basicConsume(QueueName.QUEUE_ONE, true, consumer);
	}
}
