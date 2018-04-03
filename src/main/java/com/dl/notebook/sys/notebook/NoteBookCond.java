package com.dl.notebook.sys.notebook;

import com.dl.notebook.common.base.BaseCondition;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NoteBookCond extends BaseCondition {
	private Long id;
	private String title;
	private String content;
	private Date release_date_start;
	private Date release_date_end;
	private String author;
	private String status;
	private String level;
	private String type;

	public void addCondition() {
		add(this.title, "AND t.title LIKE ?", 3);
		add(this.content, "AND t.content LIKE ?", 3);
		add(this.release_date_start, "AND t.release_date >= ?");
		add(this.release_date_end, "AND t.release_date <= ?");
		add(this.author, "AND t.author LIKE ?", 3);
		add(this.status, "AND t.status LIKE ?", 3);
		add(this.level, "AND t.level LIKE ?", 3);
		add(this.type, "AND t.type LIKE ?", 3);
	}

}
