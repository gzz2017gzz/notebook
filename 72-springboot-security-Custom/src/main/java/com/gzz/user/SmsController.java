package com.gzz.user;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SmsController {

	@RequestMapping("/sms/code")
	public String sms(String mobile, HttpSession session) {
		int code = (int) Math.ceil(Math.random() * 9000 + 1000);
		JSONObject json = new JSONObject();
		json.put("mobile", mobile);
		json.put("code", code);
		session.setAttribute("smsCode", json.toJSONString());
		log.info("{}：为 {} 设置短信验证码：{}", session.getId(), mobile, code);
		return "你的手机号" + mobile + "验证码是" + code;
	}

}
