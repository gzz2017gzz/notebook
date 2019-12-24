package com.dl.notebook.common.util;

import org.springframework.util.DigestUtils;

public class MD5Util {
	public static String md5(String srt) {
		return DigestUtils.md5DigestAsHex(srt.getBytes());
	}
	
	public static void main(String[] args) {//15517965929//264522
		System.out.println(md5("071819"));
	}
}
