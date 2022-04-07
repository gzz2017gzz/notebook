package com.gzz.sys;

import lombok.Builder;
import lombok.Data;

/**
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2022-04-07 23:56:00
 **/
@Data
@Builder
public class User {
	private Integer id;
	private Integer age;
	private String name;
}
