package com.gzz.sys;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole {
	private Integer id;
	private String roleName;
	private List<String> permissions;
}
