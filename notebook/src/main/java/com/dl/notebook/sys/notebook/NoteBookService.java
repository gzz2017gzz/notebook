package com.dl.notebook.sys.notebook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dl.notebook.common.base.Page;

@Service
public class NoteBookService {

	@Autowired
	private NoteBookDao dao;

	public int save(NoteBook noteBook) {
		return dao.save(noteBook);
	}

	public int delete(Long[] ids) {
		return dao.deleteLogic(ids);
	}

	public NoteBook findById(Long id) {
		return dao.findById(id);
	}

	public int update(NoteBook noteBook) {
		return dao.update(noteBook);
	}

	public Page<NoteBook> queryPage(NoteBookCond cond) {
		return dao.queryPage(cond);
	}

	public List<NoteBook> queryList(NoteBookCond cond) {
		return dao.queryList(cond);
	}

}
