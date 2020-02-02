package com.gzz.config.authentication;

import com.alibaba.fastjson.JSONObject;

public class Result {

	public static JSONObject create(Integer value, String message) {
		JSONObject json = new JSONObject();
		json.put("code", value);
		json.put("message", message);
		return json;
	}
}
