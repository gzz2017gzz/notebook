package com.gzz.mqtt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gzz.alitools.ConnectionOptionWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "aliyun")
public class MqttConfig {

	private MqttVo mqtt;
	private ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	private int qosLevel = 0;

	@Bean
	public MqttClient run() throws Exception {
		log.info("配置信息是:{}", mqtt);
		MemoryPersistence memoryPersistence = new MemoryPersistence();
		ConnectionOptionWrapper connectionOptionWrapper = new ConnectionOptionWrapper(mqtt.getInstanceId(), mqtt.getAccessKey(), mqtt.getSecretKey(), mqtt.getClientId());
		MqttClient mqttClient = new MqttClient("tcp://" + mqtt.getEndPoint() + ":1883", mqtt.getClientId(), memoryPersistence);
		mqttClient.setCallback(new MqttCallbackExtended() {
			@Override
			public void connectComplete(boolean reconnect, String serverURI) {
				log.info("connect success");
				executorService.submit(() -> {
					try {
						final String topicFilter[] = { mqtt.getParentTopic() + "/" + "testMq4Iot01" };
						final int[] qos = { qosLevel };
						mqttClient.subscribe(topicFilter, qos);
					} catch (MqttException e) {
						log.error(e.getMessage(), e);
					}
				});
			}

			@Override
			public void connectionLost(Throwable throwable) {
				throwable.printStackTrace();
			}

			@Override
			public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
				log.info("收到消息然后调用解析程序:" + s + " , body is " + new String(mqttMessage.getPayload()));
			}

			@Override
			public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
				log.info("发送消息: " + iMqttDeliveryToken.getTopics()[0]);
			}
		});
		mqttClient.connect(connectionOptionWrapper.getMqttConnectOptions());

		return mqttClient;
	}

	public MqttVo getMqtt() {
		return mqtt;
	}

	public void setMqtt(MqttVo mqtt) {
		this.mqtt = mqtt;
	}

}
