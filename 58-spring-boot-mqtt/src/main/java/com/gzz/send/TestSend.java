package com.gzz.send;

import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TestSend {
	@Autowired
	private MqttClient client;

	@Test
	public void save() throws Exception {
		JSONObject json = new JSONObject();
		json.put("name", "中华人民共和国");
		json.put("age", "5000");
		MqttMessage message = new MqttMessage(JSON.toJSONBytes(json));
		message.setQos(0);
		client.publish("ROBOT_CMD" + "/" + "testMq4Iot01", message);
		log.info("发完了");
		TimeUnit.SECONDS.sleep(2);
	}
}