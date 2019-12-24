package com.gzz.sys.user;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gzz.common.base.BaseDao;

import lombok.extern.slf4j.Slf4j;
/**
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2019-12-24 10:50:00
 **/
@Slf4j
@Repository
public class UserDao extends BaseDao {
	@Value("${spring.datasource.url}")
	private String url;
	public static String dbName;// 数据库用户名

	/**
	 * @功能描述 系统变量及初始化
	 */
	@PostConstruct
	private void init() {
		String[] split = url.split("[?]")[0].split("/");
		dbName = split[split.length - 1];
		log.info("当前数据库名称是:{}", dbName);
	}

	public List<Field> queryFields(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("	SELECT  column_name,column_comment, column_type, is_nullable, IF (column_key = 'pri', '是', '')  pri ");
		sb.append("	FROM information_schema.columns WHERE table_schema = ? AND table_name = ?");
		log.info(sb.toString());
		return jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<>(Field.class), dbName, tableName);
	}

	public List<Table> queryTables() {
		String sql = "SELECT table_name,table_comment FROM information_schema.TABLES WHERE table_schema = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Table.class), dbName);
	}
}
