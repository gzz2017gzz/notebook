package com.dl.notebook.sys.notebook;

import com.dl.notebook.common.base.Page;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteBookService {
	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private NoteBookDao dao;

	@Transactional
	public int save(NoteBook noteBook) {
		return this.dao.save(noteBook);
	}

	public int delete(Long[] ids) {
		return this.dao.delete(ids);
	}

	public NoteBook findById(Long id) {
		return this.dao.findById(id);
	}

	@Transactional
	public int update(NoteBook noteBook) {
		return this.dao.update(noteBook);
	}

	public Page<NoteBook> queryPage(NoteBookCond cond) {
		return this.dao.queryPage(cond);
	}

	public List<NoteBook> queryList(NoteBookCond cond) {
		return this.dao.queryList(cond);
	}

}
