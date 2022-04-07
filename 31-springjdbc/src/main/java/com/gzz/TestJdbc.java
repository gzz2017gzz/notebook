package com.gzz;

/**
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2022-04-07 23:56:00
 **/
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gzz.config.SpringConfig;
import com.gzz.sys.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestJdbc {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;// 注入jdbc模板

	// 返回单行单列(字符串型) 使用聚合函数
	@Test
	public void run9() {
		String sql = "SELECT MAX(name) FROM user WHERE id > ?";
		String name = jdbcTemplate.queryForObject(sql, String.class, -1);
		log.info("name={}", name);
	}

	// 返回单行单列(数据型) 使用聚合函数
	@Test
	public void run8() {
		String sql = "SELECT COUNT(*) FROM user WHERE id > ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, -1);
		log.info("count={}", count);
	}

	// 返回单行记录弱类型 返回记录个数必须为一条
	@Test
	public void run7() {
		String sql = "SELECT id,name,age FROM user WHERE id = ?";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, -1);
		log.info("map={}", map);
	}

	// 有参数并且弱类型<Map<String, Object>> 适用于各种场景
	@Test
	public void run6() {
		String sql = "SELECT id,name,age FROM user WHERE id > ?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, -1);
		log.info("list={}", list);
	}

	// 返回单个对象的查询 返回记录个数必须为一条
	@Test
	public void run5() {
		String sql = "SELECT id,name,age FROM user WHERE id = ?";
		User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), 1);
		log.info("user={}", user);
	}

	// 有参数并且指定反回记录类型
	@Test
	public void run4() {
		String sql = "SELECT id,name,age FROM user WHERE id = ?";
		// BeanPropertyRowMapper//bean属性行映射:把一行记录转成实体类对象
		List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), -1);
		for (User user : list) {
			log.info("user={}", user);
		}
	}

	// insert update delete用同模板中的同一个方法 有参数delete语句
	@Test
	public void run3() {
		String sql = "DELETE FROM user WHERE id = ?";
		int count = jdbcTemplate.update(sql, 23);
		log.info("count={}", count);
	}

	// 有参数update语句
	@Test
	public void run2() {
		User user = User.builder().age(10).name("王五光").id(2).build();
		Object[] obj = new Object[] { user.getName(), user.getAge(), user.getId() };
		String sql = "UPDATE user SET name = ?,age = ? WHERE id = ?";
		int count = jdbcTemplate.update(sql, obj);
		log.info("count={}", count);
	}

	// 有参数insert语句
	@Test
	public void run1() {
		User user = User.builder().age(10).name("王五").build();
		Object[] obj = new Object[] { user.getAge(), user.getName() };
		String sql = "INSERT INTO user (age,name) VALUES ( ?, ? )";
		int count = jdbcTemplate.update(sql, obj);
		log.info("count={}", count);
	}

	// 没有参数insert语句
	@Test
	public void run() {
		int count = jdbcTemplate.update("INSERT INTO user (age,name) VALUES (20,'李四光')");
		log.info("count={}", count);
	}

}
