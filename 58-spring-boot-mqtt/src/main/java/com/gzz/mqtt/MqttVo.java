package com.gzz.mqtt;

import lombok.Data;

@Data
public class MqttVo {
	private String instanceId;
	private String endPoint;
	private String accessKey;
	private String secretKey;
	private String parentTopic;
	private String clientId;
}
