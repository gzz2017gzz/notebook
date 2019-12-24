package com.dl.notebook.sys.sysuser;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String password;
	private String old_password;
	private String new_password;
	private String login_id;
}
