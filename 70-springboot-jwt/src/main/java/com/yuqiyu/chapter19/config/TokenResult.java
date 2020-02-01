package com.yuqiyu.chapter19.config;

public class TokenResult  {
 
	// 状态
	private boolean flag = true;
	// 返回消息内容
	private String msg = "";
	// 返回token值
	private String token = "";

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
