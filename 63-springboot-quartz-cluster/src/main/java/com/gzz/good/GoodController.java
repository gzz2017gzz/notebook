package com.gzz.good;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/good")
public class GoodController {
	/**
	 * 商品业务逻辑实现
	 */
	@Autowired
	private GoodService goodInfoService;

	/**
	 * 添加商品
	 */
	@RequestMapping(value = "/save")
	public Long save(GoodEntity good) throws Exception {
		return goodInfoService.saveGood(good);
	}
}
