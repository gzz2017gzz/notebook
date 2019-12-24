package com.dl.notebook.sys.notebook;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dl.notebook.common.base.BaseDao;
import com.dl.notebook.common.base.Page;
import com.dl.notebook.common.base.SqlUtil;

/**
 * @类说明:记事本数据访问层
 * @author:高振中
 * @date:2018-04-04 18:46:20
 **/
@Repository
public class NoteBookDao extends BaseDao{
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public NoteBookDao () {
		select.append("SELECT t.id,t.title,t.content,t.release_date,t.author,t.status,t.level,t.type,t.dr");
		select.append(" FROM sys_note_book t WHERE dr=0");

		insert.append("INSERT INTO sys_note_book (title,content,release_date,author,status,level,type,dr)");
		insert.append(" VALUES (:title,:content,:release_date,:author,:status,:level,:type,:dr)");
	}

	/**
	 * @方法说明:新增记事本记录
	 **/
	public int save(NoteBook vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO sys_note_book (id,title,content,release_date,author,status,level,type,dr)");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?) ");
		Object[] params ={vo.getId(),vo.getTitle(),vo.getContent(),vo.getRelease_date(),vo.getAuthor(),vo.getStatus(),vo.getLevel(),vo.getType(),vo.getDr()};
//		logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:新增记事本记录并返回自增涨主键值
	 **/
	public long saveReturnPK(NoteBook vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明:批量插入记事本记录
	 **/
	public int[] insertBatch(List<NoteBook> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:物理删除记事本记录(多条)
	 **/
//	public int delete(Long ids[]) {
//		String sql = "DELETE FROM sys_note_book WHERE id" + SqlUtil.ArrayToIn(ids);
//		return jdbcTemplate.update(sql);
//	}

	/**
	 * @方法说明:按ID查找单个记事本实体
	 **/
	public NoteBook findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(NoteBook.class));
	}

	/**
	 * @方法说明:更新记事本记录
	 **/
	public int update(NoteBook vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE sys_note_book SET title=?,content=?,release_date=?,author=?,status=?,level=?,type=?,dr=?");
		sql.append(" WHERE id=? ");
		Object[] params = {vo.getTitle(),vo.getContent(),vo.getRelease_date(),vo.getAuthor(),vo.getStatus(),vo.getLevel(),vo.getType(),vo.getDr(),vo.getId()};
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:按条件查询分页记事本列表
	 **/
	public Page<NoteBook> queryPage(NoteBookCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		sb.append(" ORDER BY id DESC");
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, NoteBook.class);
	}

	/**
	 * @方法说明:按条件查询不分页记事本列表
	 **/
	public List<NoteBook> queryList(NoteBookCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(NoteBook.class));
	}

	/**
	 * @方法说明:按条件查询记事本记录个数
	 **/
	public long queryCount(NoteBookCond cond) {
		String countSql = "SELECT COUNT(1) FROM sys_note_book t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明:逻辑删除记事本记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE sys_note_book SET dr=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}