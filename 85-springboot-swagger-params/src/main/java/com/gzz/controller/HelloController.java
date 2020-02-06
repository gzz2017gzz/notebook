package com.gzz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/* 类注解 */
@Api(tags = { "用户相关 " })
@RestController
public class HelloController {

	/* 方法注解 */
	@ApiOperation(value = "方法定义", notes = "方法的一个相对比较详细的描述")
	@GetMapping(value = "/hello")
	public Object hello( /* 参数注解 */ @ApiParam(value = "参数描述", required = true) @RequestParam String name) {
		return "Hello " + name + "!";
	}
}