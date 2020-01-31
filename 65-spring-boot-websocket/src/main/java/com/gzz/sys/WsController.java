package com.gzz.sys;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {
	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public WiselyReponse say(WiselyMessage message) {
		return new WiselyReponse("欢迎使用webScoket：" + message.getName());
	}
}
