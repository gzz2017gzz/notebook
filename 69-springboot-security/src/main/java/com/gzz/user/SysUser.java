package com.gzz.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {
	private Long id;
	private String userName;
	private String password;
	private List<String> roles;
}
