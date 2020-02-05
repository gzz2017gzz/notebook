package com.gzz.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.gzz.sys.bean.MyResourceBean;

@Mapper
public interface ResourceMapper {
	/**
	 * 从数据库中查询所有资源
	 */
	@Select("select * from resource ")
	List<MyResourceBean> selectAllResource();
}
