package com.gzz;
/**
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2019-12-24 10:50:00
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
//@ContextConfiguration(locations = "classpath:spring-config.xml")
public class TestJdbc {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;// 注入jdbc模板

	// 返回单行单列(字符串型)
	// 使用聚合函数
	@Test
	public void run9() {
		int id = -1;
		Object[] obj = new Object[] { id };
		String sql = "select max(name) from user where id>?";
		String name = jdbcTemplate.queryForObject(sql, obj, String.class);
		log.info("name={}", name);
	}

	// 返回单行单列(数据型)
	// 使用聚合函数
	@Test
	public void run8() {
		int id = -1;
		Object[] obj = new Object[] { id };
		String sql = "select count(*) from user where id>?";
		Integer count = jdbcTemplate.queryForObject(sql, obj, Integer.class);
		log.info("count={}", count);
	}

	// 返回单行记录弱类型
	// 返回记录个数必须为一条
	@Test
	public void run7() {
		int id = 1;
		Object[] obj = new Object[] { id };
		String sql = "select id,name,age from user where id=?";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, obj);
		log.info("map={}", map);
	}

	// 有参数并且弱类型<Map<String, Object>>
	// 适用于各种场景
	@Test
	public void run6() {
		int id = -1;
		Object[] obj = new Object[] { id };
		String sql = "select id,name,age from user where id>?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, obj);
		log.info("list={}", list);
	}

	// 返回单个对象的查询
	// 返回记录个数必须为一条
	@Test
	public void run5() {
		int id = 1;
		Object[] obj = new Object[] { id };
		String sql = "select id,name,age from user where id=?";
		User user = jdbcTemplate.queryForObject(sql, obj, new BeanPropertyRowMapper<User>(User.class));
		log.info("user={}", user);
	}

	// 有参数并且指定反回记录类型
	@Test
	public void run4() {
		int id = -1;
		Object[] obj = new Object[] { id };
		String sql = "select id,name,age from user where id=?";
		// BeanPropertyRowMapper//bean属性行映射:把一行记录转成实体类对象
		List<User> list = jdbcTemplate.query(sql, obj, new BeanPropertyRowMapper<User>(User.class));
		for (User user : list) {
			log.info("user={}", user);
		}
	}

	// insert update delete用同模板中的同一个方法
	// 有参数delete语句
	@Test
	public void run3() {
		int id = 23;
		Object[] obj = new Object[] { id };
		String sql = "delete from user where id=?";
		int count = jdbcTemplate.update(sql, obj);
		log.info("count={}", count);
	}

	// 有参数update语句
	@Test
	public void run2() {
		User user = new User();
		user.setAge(10);
		user.setName("王五光");
		user.setId(2);
		Object[] obj = new Object[] { user.getName(), user.getAge(), user.getId() };
		String sql = "update user set name=?,age=? where id=?";
		int count = jdbcTemplate.update(sql, obj);
		log.info("count={}", count);
	}

	// 有参数insert语句
	@Test
	public void run1() {
		User user = new User();
		user.setAge(10);
		user.setName("王五");
		Object[] obj = new Object[] { user.getAge(), user.getName() };
		String sql = "insert into user (age,name) values(?,?)";
		int count = jdbcTemplate.update(sql, obj);
		log.info("count={}", count);
	}

	// 没有参数insert语句
	@Test
	public void run() {
		int count = jdbcTemplate.update("insert into user (age,name) values(20,'李四光')");
		log.info("count={}", count);
	}

}
