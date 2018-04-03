package com.dl.notebook.sys.sysuser;

import com.dl.notebook.common.base.Page;
import com.dl.notebook.common.security.PrincipalAction;
import com.dl.notebook.common.util.MD5Util;
import java.security.Principal;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "api/sysUser" })
public class SysUserAction extends PrincipalAction {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private SysUserService service;

	@RequestMapping({ "save" })
	public int save(@RequestBody SysUser sysUser, Principal principal) {
		return this.service.save(sysUser);
	}

	@RequestMapping({ "delete" })
	public int delete(@RequestParam("ids[]") Long[] ids) {
		return this.service.delete(ids);
	}

	@RequestMapping({ "update" })
	public int update(@RequestBody SysUser sysUser, Principal principal) {
		return this.service.update(sysUser);
	}

	@RequestMapping({ "updatePassword" })
	public int updatePassword(@RequestBody SysUser sysUser, Principal principal) {
		sysUser.setPassword(sysUser.getNew_password());
		sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
		return this.service.update(sysUser);
	}

	@RequestMapping({ "queryPage" })
	public Page<SysUser> queryPage(@RequestBody SysUserCond cond, Principal principal) {
		return this.service.queryPage(cond);
	}

	@RequestMapping({ "queryList" })
	public List<SysUser> queryList(@RequestBody SysUserCond cond, Principal principal) {
		return this.service.queryList(cond);
	}

	@RequestMapping({ "findById" })
	public SysUser findById(@RequestParam("id") Long id) {
		return this.service.findById(id);
	}

	@RequestMapping({ "getUser" })
	public SysUser getUser(Principal principal) {
		return super.getUser(principal);
	}
}
