package com.dl.notebook.sys.sysuser;

import com.dl.notebook.common.base.BaseCondition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserCond extends BaseCondition {
	private Long id;
	private String name;
	private String password;
	private String login_id;

	public void addCondition() {
		add(this.name, "AND t.name LIKE ?", 3);
		add(this.login_id, "AND t.login_id = ?");
	}
}
