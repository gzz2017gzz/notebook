package com.gzz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gzz.domain.Person;

@Mapper
public interface PersonDao {

	@Update("INSERT INTO person(name,age) VALUES(#{name},#{age})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	int insert(Person person);

	@Update("UPDATE person SET name=#{name},age=#{age} WHERE id=#{id}")
	int update(Person person);

	@Update("DELETE FROM person WHERE id=#{id}")
	int delete(@Param("id") Long id);

	@Select("SELECT id,name,age FROM person")
	List<Person> selectAll();

	@Select("SELECT id,name,age FROM person WHERE id=#{id}")
	Person selectById(@Param("id") Long id);
}
