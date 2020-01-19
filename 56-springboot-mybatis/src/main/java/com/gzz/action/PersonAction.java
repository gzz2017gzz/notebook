//package com.gzz.action;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.gzz.dao.PersonDao;
//import com.gzz.domain.Person;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@RequestMapping("person")
//@Slf4j
//public class PersonAction {
//
//	@Autowired
//	private PersonDao dao;
//
//	@PostMapping("save")
//	public int save(Person person) {
//		log.info("save");
//		return dao.insert(person);
//
//	}
//
//}
