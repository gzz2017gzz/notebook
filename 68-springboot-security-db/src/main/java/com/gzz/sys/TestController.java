package com.gzz.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzz.config.UserEntity;

@RestController
public class TestController {
	@Autowired
	private UserJPA dao;

	@RequestMapping("test")
	public List<UserEntity> login() {
		return dao.findAll();
	}
}
