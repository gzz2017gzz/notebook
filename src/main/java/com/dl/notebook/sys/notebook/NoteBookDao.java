package com.dl.notebook.sys.notebook;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dl.notebook.common.base.BaseDao;
import com.dl.notebook.common.base.Page;
import com.dl.notebook.common.base.SqlUtil;

@Repository
public class NoteBookDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	public NoteBookDao() {
		select.append("SELECT t.id,t.title,t.content,t.release_date,t.author,t.status,t.level,t.type");
		select.append(" FROM sys_note_book t WHERE 1=1");
		insert.append("INSERT INTO sys_note_book (title,content,release_date,author,status,level,type)");
		insert.append(" VALUES (:title,:content,:release_date,:author,:status,:level,:type)");
	}

	public int save(NoteBook vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO sys_note_book (id,title,content,release_date,author,status,level,type)");
		sql.append(" VALUES (?,?,?,?,?,?,?,?) ");
		Object[] params = { vo.getId(), vo.getTitle(), vo.getContent(), vo.getRelease_date(), vo.getAuthor(), vo.getStatus(), vo.getLevel(), vo.getType() };
		return jdbcTemplate.update(sql.toString(), params);
	}

	public int delete(Long[] ids) {
		String sql = "DELETE FROM sys_note_book WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	public NoteBook findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[] { id }, new BeanPropertyRowMapper<>(NoteBook.class));
	}

	public int update(NoteBook vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE sys_note_book SET title=?,content=?,release_date=?,author=?,status=?,level=?,type=?");
		sql.append(" WHERE id=? ");
		Object[] params = { vo.getTitle(), vo.getContent(), vo.getRelease_date(), vo.getAuthor(), vo.getStatus(), vo.getLevel(), vo.getType(), vo.getId() };
		return jdbcTemplate.update(sql.toString(), params);
	}

	public Page<NoteBook> queryPage(NoteBookCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		sb.append(" ORDER BY id DESC");
		return queryPage(sb.toString(), cond, NoteBook.class);
	}

	public List<NoteBook> queryList(NoteBookCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());

		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(NoteBook.class));
	}

}
