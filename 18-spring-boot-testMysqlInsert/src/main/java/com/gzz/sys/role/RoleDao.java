package com.gzz.sys.role;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gzz.base.BaseDao;

/**
 * @类说明 [角色]数据访问层
 * @author 高振中
 * @date 2018-12-24 21:36:34
 **/
@Repository
public class RoleDao extends BaseDao {

	/**
	 * @方法说明 批量插入角色记录
	 */
	public int[] insertBatch(List<Role> list) {
		StringBuilder insert = new StringBuilder();
		insert.append("INSERT INTO role (role,name,available,role1,role2,role3,role4,role5,role6,");
		insert.append("role7,role8,role9,role10,role11,role12,role13,role14) ");
		insert.append(" VALUES (:role,:name,:available,:role1,:role2,:role3,:role4,:role5,:role6,");
		insert.append(":role7,:role8,:role9,:role10,:role11,:role12,:role13,:role14)");
		return batchOperate(list, insert.toString());
	}

	public void queryList() {
		String sql = "select * from role where id in (?,?,?,?,?)";

		Object obj[] = { 80001, 80002, 80003, 80004, 80005 };

		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql, obj);
		for (Map<String, Object> map : queryForList) {
			logger.info(map);
		}
	}

}