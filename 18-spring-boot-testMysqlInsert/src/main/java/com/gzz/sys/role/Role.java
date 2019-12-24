package com.gzz.sys.role;
 

/**
 * @类说明 [角色]实体类
 * @author 高振中
 * @date 2018-12-28 11:47:11
 **/
 
public class Role {
	
	
    public Role(String role, String name, int available, String role1, String role2, String role3, String role4, String role5, String role6, String role7, String role8,
			String role9, String role10, String role11, String role12, String role13, String role14) {
		super();
		this.role = role;
		this.name = name;
		this.available = available;
		this.role1 = role1;
		this.role2 = role2;
		this.role3 = role3;
		this.role4 = role4;
		this.role5 = role5;
		this.role6 = role6;
		this.role7 = role7;
		this.role8 = role8;
		this.role9 = role9;
		this.role10 = role10;
		this.role11 = role11;
		this.role12 = role12;
		this.role13 = role13;
		this.role14 = role14;
	}
	// 数据库中的字段
	private Long id; // 主键
	private String role; // 角色
	private String name; // 名称
	private int available; // 是否可用
	private String role1; // 角色
	private String role2; // 角色
	private String role3; // 角色
	private String role4; // 角色
	private String role5; // 角色
	private String role6; // 角色
	private String role7; // 角色
	private String role8; // 角色
	private String role9; // 角色
	private String role10; // 角色
	private String role11; // 角色
	private String role12; // 角色
	private String role13; // 角色
	private String role14; // 角色
    // 此处可添加查询显示辅助字段
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public String getRole1() {
		return role1;
	}
	public void setRole1(String role1) {
		this.role1 = role1;
	}
	public String getRole2() {
		return role2;
	}
	public void setRole2(String role2) {
		this.role2 = role2;
	}
	public String getRole3() {
		return role3;
	}
	public void setRole3(String role3) {
		this.role3 = role3;
	}
	public String getRole4() {
		return role4;
	}
	public void setRole4(String role4) {
		this.role4 = role4;
	}
	public String getRole5() {
		return role5;
	}
	public void setRole5(String role5) {
		this.role5 = role5;
	}
	public String getRole6() {
		return role6;
	}
	public void setRole6(String role6) {
		this.role6 = role6;
	}
	public String getRole7() {
		return role7;
	}
	public void setRole7(String role7) {
		this.role7 = role7;
	}
	public String getRole8() {
		return role8;
	}
	public void setRole8(String role8) {
		this.role8 = role8;
	}
	public String getRole9() {
		return role9;
	}
	public void setRole9(String role9) {
		this.role9 = role9;
	}
	public String getRole10() {
		return role10;
	}
	public void setRole10(String role10) {
		this.role10 = role10;
	}
	public String getRole11() {
		return role11;
	}
	public void setRole11(String role11) {
		this.role11 = role11;
	}
	public String getRole12() {
		return role12;
	}
	public void setRole12(String role12) {
		this.role12 = role12;
	}
	public String getRole13() {
		return role13;
	}
	public void setRole13(String role13) {
		this.role13 = role13;
	}
	public String getRole14() {
		return role14;
	}
	public void setRole14(String role14) {
		this.role14 = role14;
	}
	
	
}