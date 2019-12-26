package com.gzz.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzz.entity.CityEntity;
import com.gzz.json.ResponseBodyInfo;
import com.gzz.json.ResultInfo;
import com.gzz.service.CityService;

@Controller
public class IndexController {
	@Autowired
	private CityService cityService;

	@RequestMapping(value = { "", "/", "index" }, method = RequestMethod.GET)
	public String index() {
		return "index.jsp";
	}

	// List
	@ResponseBody
	@RequestMapping(value = "list")
	public Map<String, Object> list(String hotelName) {
		System.out.println(hotelName);
		Map<String, Object> map = new HashMap<>();
		List<CityEntity> infos = cityService.findAll(hotelName);
		map.put("rows", infos);
		map.put("total", infos.size());
		return map;
	}

	// Save
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseBodyInfo<Object> save(CityEntity city, HttpServletRequest request) {
		try {
			cityService.save(city);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultInfo.error(-1, e.getMessage());
		}
		return ResultInfo.success();
	}

	/**
	 * Delete
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseBodyInfo<Object> delete(@PathVariable int id) {
		try {
			cityService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultInfo.error(-1, e.getMessage());
		}
		return ResultInfo.success();
	}
}
