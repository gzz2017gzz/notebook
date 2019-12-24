package com.dl.notebook.sys.sysuser;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dl.notebook.common.base.BaseDao;
import com.dl.notebook.common.base.Page;
import com.dl.notebook.common.base.SqlUtil;

@Repository
public class SysUserDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	public SysUserDao() {
		select.append("SELECT t.id,t.name,t.password,t.login_id");
		select.append(" FROM sys_user t WHERE 1=1");

		insert.append("INSERT INTO sys_user (name,password,login_id)");
		insert.append(" VALUES (:name,:password,:login_id)");
	}

	public int save(SysUser vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO sys_user (id,name,password,login_id)");
		sql.append(" VALUES (?,?,?,?) ");
		Object[] params = { vo.getId(), vo.getName(), vo.getPassword(), vo.getLogin_id() };
		return jdbcTemplate.update(sql.toString(), params);
	}

	public int delete(Long[] ids) {
		String sql = "DELETE FROM sys_user WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	public SysUser findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[] { id }, new BeanPropertyRowMapper<>(SysUser.class));
	}

	public int update(SysUser vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE sys_user SET name=?,password=?,login_id=?");
		sql.append(" WHERE id=? ");
		Object[] params = { vo.getName(), vo.getPassword(), vo.getLogin_id(), vo.getId() };
		return jdbcTemplate.update(sql.toString(), params);
	}

	public Page<SysUser> queryPage(SysUserCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		sb.append(" ORDER BY id DESC");
		return queryPage(sb.toString(), cond, SysUser.class);
	}

	public List<SysUser> queryList(SysUserCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(SysUser.class));
	}

}
