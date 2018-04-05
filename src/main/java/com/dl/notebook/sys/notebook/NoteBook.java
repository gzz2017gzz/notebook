package com.dl.notebook.sys.notebook;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @类说明:记事本实体类
 * @author:高振中
 * @date:2018-04-04 18:46:20
 **/
@Setter
@Getter
public class NoteBook {

	//数据库中的字段
	private Long id;// 主键
	private String title;// 标题
	private String content;// 内容
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date release_date;// 发布日期
	private String author;// 作者
	private String status;// 状态
	private String level;// 优先级
	private String type;// 问题类型
	private Byte dr;// 删除标记

	//此处可添加查询显示辅助字段

}