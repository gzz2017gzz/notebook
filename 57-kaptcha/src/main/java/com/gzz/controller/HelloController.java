package com.gzz.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.impl.DefaultKaptcha;

/**
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2019-12-24 10:50:00
 */
@RestController

public class HelloController {
	Logger logger = LoggerFactory.getLogger(HelloController.class);

	@RequestMapping("/")
	public String index() {
		return "Hello Spring Boot 2.1.11!";
	}

	@Autowired
	private DefaultKaptcha captchaProducer;

	@RequestMapping("/defaultKaptcha")
	public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String createText = captchaProducer.createText();
		logger.info("verifyCode:" + createText);
		BufferedImage challenge = captchaProducer.createImage(createText);
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ImageIO.write(challenge, "jpg", stream);
		byte[] bytes = stream.toByteArray();
		
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		out.write(bytes);
		out.flush();
		out.close();
		
	 
	}

}