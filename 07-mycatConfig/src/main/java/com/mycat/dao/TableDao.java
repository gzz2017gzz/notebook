package com.mycat.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mycat.model.Field;
import com.mycat.model.Table;

@Repository
public class TableDao {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(TableDao.class);// 日志类
	@Autowired
	private JdbcTemplate jdbcTemplate;// jdbc模版类

	public List<Table> queryTable() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT TABLE_NAME,if(TABLE_COMMENT='',TABLE_NAME,TABLE_COMMENT) COMMENTS,TABLE_SCHEMA ");
		sb.append("FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'keep'");
		return jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<>(Table.class));
	}

	/**
	 * @功能描述: 查询字段名列表
	 */
	public List<Field> queryColumn(String tablename) {
		StringBuilder sb = new StringBuilder();
		sb.append("	SELECT COLUMN_NAME NAME,");
		sb.append("	CASE WHEN COLUMN_COMMENT = '' THEN COLUMN_NAME	ELSE COLUMN_COMMENT	END COMMENTS");
		sb.append("	FROM");
		sb.append("		INFORMATION_SCHEMA.COLUMNS");
		sb.append(" WHERE 1 = 1");
		sb.append("   AND TABLE_NAME =  '" + tablename.toUpperCase() + "'");
		sb.append("   AND table_schema = 'keep'");
		sb.append(" ORDER BY ORDINAL_POSITION");
//		logger.info(sb.toString());
		return jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<>(Field.class));
	}
}
