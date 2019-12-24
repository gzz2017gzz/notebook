package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestJson {
	private static Log logger = LogFactory.getLog(TestJson.class);// 定义日志工具
	private static String json = "[{\"id\":1,\"name\":\"张三\",\"age\":30},{\"id\":2,\"name\":\"张三丰\",\"age\":30},{\"id\":3,\"name\":\"张丰\",\"age\":3}]";

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		logger.info("jackson");
		jackson();
		logger.info("fastJson");
		fastJson();
		logger.info("gson");
		gson();
		User user = new User(1, 2, "3");
		List<User> list = new ArrayList<>();
		list.add(user);
		list.add(user);
		logger.info(JSON.toJSONString(list));

	}

	// jackson把json转List
	private static void jackson() throws JsonParseException, JsonMappingException, IOException {
		User users[] = new ObjectMapper().readValue(json, User[].class);
		List<User> list = Arrays.asList(users);// 把数组转成list
		printUsers(list);
	}

	// fastJson把json转List
	private static void fastJson() {
		List<User> users = JSONArray.parseArray(json, User.class);
		printUsers(users);
	}

	// gson把json转List
	private static void gson() {
		List<User> users = new Gson().fromJson(json, new TypeToken<List<User>>() {
		}.getType());
		printUsers(users);
	}

	private static void printUsers(List<User> users) {
		for (User user : users) {
			logger.info(user);
		}
	}
}
