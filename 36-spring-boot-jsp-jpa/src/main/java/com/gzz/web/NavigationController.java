package com.gzz.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzz.entity.NavigationEntity;
import com.gzz.service.NavigationService;

@Controller
@RequestMapping("/user/nav/")
public class NavigationController {
	@Autowired
	private NavigationService navigationService;
	
	/**
	 * 查询Home页左边导航
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="homeleft",produces="application/json;charset=utf-8")
	public List<NavigationEntity> homeLeft(){
		return navigationService.findByType(NavigationEntity.TYPE_HOME_LEFT);
	}
}
