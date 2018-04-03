package com.dl.notebook.sys.notebook;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteBook {
	private Long id;
	private String title;
	private String content;
	private Date release_date;
	private String author;
	private String status;
	private String level;
	private String type;
}
