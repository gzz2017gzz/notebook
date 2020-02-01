package com.gzz.sys.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzz.sys.entity.DemoEntity;

@RestController
public class IndexController {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/save")
	public String validator(@Valid DemoEntity entity, BindingResult result) {
		if (result.hasErrors()) {
			StringBuffer msg = new StringBuffer();
			// 获取错误字段集合
			List<FieldError> fieldErrors = result.getFieldErrors();
	 
			// 遍历错误字段获取错误消息
			for (FieldError fieldError : fieldErrors) {
				// 获取错误信息
				String errorMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
				// 添加到错误消息集合内
				msg.append(fieldError.getField() + "：" + errorMessage + " , ");
			}
			return msg.toString();
		}
		return "验证通过:" + "名称：" + entity.getName() + "年龄：" + entity.getAge() + "邮箱地址：" + entity.getMail();
	}
}
