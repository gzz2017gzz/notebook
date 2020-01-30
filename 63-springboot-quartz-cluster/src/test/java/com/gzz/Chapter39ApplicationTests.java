package com.gzz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Chapter39ApplicationTests {
	/**
	 * 模拟mvc测试对象
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * 测试添加商品
	 * 
	 * @throws Exception
	 */
	@Test
	public void addGood() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/good/save").param("name", "西红柿").param("unit", "斤").param("price", "12.88"))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		result.getResponse().setCharacterEncoding("UTF-8");
		System.out.println(result.getResponse().getContentAsString());
	}
}
