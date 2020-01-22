package com.gzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//参考官方文档
//https://github.com/AliwareMQ/lmq-demo/
//https://github.com/AliwareMQ/lmq-demo/blob/master/lmq-java-demo/src/main/java/com/aliyun/openservices/lmq/example/demo/MQ4IoTSendMessageToMQ4IoTUseSignatureMode.java
//请求http://localhost:8080/send 验证发送
@SpringBootApplication
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
