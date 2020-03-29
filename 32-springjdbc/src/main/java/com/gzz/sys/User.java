package com.gzz.sys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2019-12-24 10:50:00
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer id;
	private Integer age;
	private String name;
}
