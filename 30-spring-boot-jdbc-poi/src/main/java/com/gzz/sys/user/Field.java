package com.gzz.sys.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @类说明:用户实体类
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2019-12-24 10:50:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Field {
	private String column_name;
	private String column_comment;
	private String column_type;
	private String is_nullable;
	private String pri;
}