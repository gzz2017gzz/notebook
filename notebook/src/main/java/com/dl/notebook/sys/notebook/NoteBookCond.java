package com.dl.notebook.sys.notebook;

import com.dl.notebook.common.base.BaseCondition;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NoteBookCond extends BaseCondition {

	/**
	 * @方法说明:拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(this.title, "AND t.title LIKE ?", 3);
		add(this.content, "AND t.content LIKE ?", 3);
		add(this.release_date_start, "AND t.release_date >= ?");
		add(this.release_date_end, "AND t.release_date <= ?");
		add(this.author, "AND t.author LIKE ?", 3);
		add(this.status, "AND t.status LIKE ?", 3);
		add(this.level, "AND t.level LIKE ?", 3);
		add(this.type, "AND t.type LIKE ?", 3);
		// add(dr, "AND t.dr = ?");
		// add(ids, "AND t.id IN ");
	}

	// 查询条件,把不用的条件清理掉
	private Long id;// 主键
	private String title;// 标题
	private String content;// 内容
	private Date release_date_start;
	private Date release_date_end;
	private String author;// 作者
	private String status;// 状态
	private String level;// 优先级
	private String type;// 问题类型
	private Byte dr;// 删除标记
	// private List<Long> ids;// 主键列表

}
