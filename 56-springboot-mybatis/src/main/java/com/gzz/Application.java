package com.gzz;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.gzz.dao.PersonDao;
import com.gzz.domain.Person;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private PersonDao dao;

	public Integer save() {
		Person person = new Person();
		person.setName("张三");
		person.setAge(18);
		dao.insert(person);
		return person.getId();
	}

	public int update() {
		Person person = new Person();
		person.setId(2);
		person.setName("阿里旺旺");
		person.setAge(12);
		return dao.update(person);
	}

	public int delete() {
		return dao.delete(1L);
	}

	public Person selectById() {
		return dao.selectById(2L);
	}

	@PostConstruct
	public List<Person> selectAll() {
		List<Person> persons = dao.selectAll();
		log.info("persons={}", JSON.toJSONString(persons));
		return persons;
	}

	@Transactional // 需要事务的时候加上
	public Boolean transaction() {
		delete();
		@SuppressWarnings("unused")
		int i = 3 / 0;
		save();
		return true;
	}

}
