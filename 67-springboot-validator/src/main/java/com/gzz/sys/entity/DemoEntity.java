package com.gzz.sys.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.gzz.sys.validator.FlagValidator;

 
public class DemoEntity   {
	@NotBlank
	@Length(min = 2, max = 10)
	private String name;

	@Min(value = 1)
	private int age;

	@NotBlank
	@Email
	private String mail;

	@FlagValidator(values = "1,2,3")
	private String flag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
