package com.dl.notebook.sys.notebook;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dl.notebook.common.base.Page;
import com.dl.notebook.common.security.PrincipalAction;

@RestController
@RequestMapping({ "api/noteBook" })
public class NoteBookAction extends PrincipalAction {
	@Autowired
	private NoteBookService service;

	@RequestMapping({ "save" })
	public int save(@RequestBody NoteBook noteBook, Principal principal) {
		noteBook.setRelease_date(new Date());
		noteBook.setAuthor(getUser(principal).getName());
		noteBook.setDr((byte) 0);
		return service.save(noteBook);
	}

	@RequestMapping({ "delete" })
	public int delete(@RequestParam("ids[]") Long[] ids) {
		return service.delete(ids);
	}

	@RequestMapping({ "update" })
	public int update(@RequestBody NoteBook noteBook) {
		return service.update(noteBook);
	}

	@RequestMapping({ "queryPage" })
	public Page<NoteBook> queryPage(@RequestBody NoteBookCond cond) {
		return service.queryPage(cond);
	}

	@RequestMapping({ "queryList" })
	public List<NoteBook> queryList(@RequestBody NoteBookCond cond) {
		return service.queryList(cond);
	}

	@RequestMapping({ "findById" })
	public NoteBook findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}
}
