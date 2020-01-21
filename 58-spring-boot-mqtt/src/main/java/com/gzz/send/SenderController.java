package com.gzz.send;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@RestController
public class SenderController {

	@Autowired
	private MqttClient client;

	@RequestMapping("send")
	public void send() throws Exception {

		JSONObject json = new JSONObject();
		json.put("name", "中华人民共和国");
		json.put("age", "5000");
		MqttMessage message = new MqttMessage(JSON.toJSONBytes(json));
		message.setQos(0);
		client.publish("ROBOT_CMD" + "/" + "testMq4Iot01", message);

	}

}
