package com.gzz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gzz.sys.GoodInfoEntity;
import com.gzz.sys.GoodInfoJPA;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class MockMvcTests {
 
	@Autowired
	private MockMvc mockMvc;// 2

	@Autowired
	private GoodInfoJPA goodInfoJPA;

	@Test
	public void testIndex() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/index").param("name", "admin")).andReturn(); // 5
		int status = mvcResult.getResponse().getStatus(); // 6
		String responseString = mvcResult.getResponse().getContentAsString(); // 7

		Assert.assertEquals("请求错误", 200, status); // 8
		Assert.assertEquals("返回结果不一致", "this is index pageadmin", responseString); // 9
	}

	@Test
	public void testAll() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/all")).andReturn(); // 5
		int status = mvcResult.getResponse().getStatus(); // 6
		String responseString = mvcResult.getResponse().getContentAsString(); // 7

		Assert.assertEquals("return status not equals 200", 200, status); // 8
		log.info(responseString);
	}

	@Test
	public void testDetail() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/detail").param("goodId", "2")).andReturn(); // 5

		// 输出经历的拦截器
		HandlerInterceptor[] interceptors = mvcResult.getInterceptors();
		log.info(interceptors[0].getClass().getName());

		int status = mvcResult.getResponse().getStatus(); // 6
		String responseString = mvcResult.getResponse().getContentAsString(); // 7
		log.info("返回内容：" + responseString);
		Assert.assertEquals("return status not equals 200", 200, status); // 8
	}

	/**
	 * 测试添加商品基本信息
	 */
	@Test
	public void testInsert() {
		/**
		 * 商品基本信息实体
		 */
		GoodInfoEntity goodInfoEntity = new GoodInfoEntity();
		goodInfoEntity.setTitle("西红柿");
		goodInfoEntity.setOrder(2);
		goodInfoEntity.setPrice(5.82);
		goodInfoEntity.setTypeId(1);
		goodInfoJPA.save(goodInfoEntity);
		/**
		 * 测试是否添加成功 验证主键是否存在
		 */
		Assert.assertNotNull(goodInfoEntity.getTgId());
	}

	/**
	 * 测试删除商品基本信息
	 */
	@Test
	public void testDelete() {
		// 根据主键删除
		goodInfoJPA.deleteById(3);

		// 验证数据库是否已经删除
//		Assert.assertNull(goodInfoJPA.findById(3).get());
	}
}
