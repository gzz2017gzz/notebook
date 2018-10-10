package com.dl.notebook.sys.sysuser;

import com.dl.notebook.common.base.Page;
import com.dl.notebook.common.util.MD5Util;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserService {
	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private SysUserDao dao;

	@Transactional
	public int save(SysUser sysUser) {
		sysUser.setPassword(MD5Util.md5(sysUser.getPassword()));
		return this.dao.save(sysUser);
	}

	public int delete(Long[] ids) {
		return this.dao.delete(ids);
	}

	public SysUser findById(Long id) {
		return this.dao.findById(id);
	}

	@Transactional
	public int update(SysUser sysUser) {
		return this.dao.update(sysUser);
	}

	public Page<SysUser> queryPage(SysUserCond cond) {
		return this.dao.queryPage(cond);
	}

	public List<SysUser> queryList(SysUserCond cond) {
		return this.dao.queryList(cond);
	}

}
