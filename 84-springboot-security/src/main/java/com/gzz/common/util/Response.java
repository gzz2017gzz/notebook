package com.gzz.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 返回服务器状态的bean类 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
	private Integer code;
	private String msg;
	private T data;
}
