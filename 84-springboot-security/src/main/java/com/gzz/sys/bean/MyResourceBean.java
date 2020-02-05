package com.gzz.sys.bean;

import lombok.Data;

/** 资源表resource对应的类 */
@Data
public class MyResourceBean {
	private Long id;
	private String url;
	private String roles;

	public String[] getRolesArray() {
		return roles.split(",");
	}
}
