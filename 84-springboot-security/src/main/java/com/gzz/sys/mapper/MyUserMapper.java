package com.gzz.sys.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gzz.sys.bean.MyUserBean;

/**
 * 数据库表user的mapper类
 */
@Mapper
public interface MyUserMapper {
	/**
	 * 从数据库中查询用户
	 */
	@Select("select * from user where username = #{username}")
	MyUserBean selectByUsername(@Param("username") String username);
}
