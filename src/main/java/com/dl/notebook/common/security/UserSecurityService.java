package com.dl.notebook.common.security;

import com.dl.notebook.sys.sysuser.SysUser;
import com.dl.notebook.sys.sysuser.SysUserCond;
import com.dl.notebook.sys.sysuser.SysUserDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {
	@Autowired
	private SysUserDao sysUserDao;
	private Log logger = LogFactory.getLog(getClass());

	public UserDetails loadUserByUsername(String userName) {
		SysUserCond cond = new SysUserCond();
		cond.setLogin_id(userName);
		List<SysUser> list = this.sysUserDao.queryList(cond);
		if (list.size() != 0) {
			SysUser user = list.get(0);
			List<SimpleGrantedAuthority> auths = new ArrayList<>();
			auths.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new UserSubject(user, auths);
		}
		this.logger.info("没有找到用户");
		throw new UsernameNotFoundException("admin: " + userName + " do not exist!");
	}
}
