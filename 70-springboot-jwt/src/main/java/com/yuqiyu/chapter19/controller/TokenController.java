package com.yuqiyu.chapter19.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yuqiyu.chapter19.config.TokenResult;
import com.yuqiyu.chapter19.entity.TokenInfoEntity;
import com.yuqiyu.chapter19.entity.UserInfoEntity;
import com.yuqiyu.chapter19.jpa.TokenJPA;
import com.yuqiyu.chapter19.jpa.UserInfoJPA;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value = "/jwt")
public class TokenController {
	@Autowired
	private TokenJPA tokenJPA;

	@Autowired
	private UserInfoJPA userInfoJPA;

	@RequestMapping(value = "/token", method = { RequestMethod.POST, RequestMethod.GET })
	public TokenResult token(@RequestParam String appId, @RequestParam String appSecret) {
		TokenResult result = new TokenResult();
		// appId is null
		if (appId == null || appId.trim() == "") {
			result.setFlag(false);
			result.setMsg("appId is not null!");
			return result;
		}
		// appSecret is null
		if (appSecret == null || appSecret.trim() == "") {
			result.setFlag(false);
			result.setMsg("appSecret is not null!");
			return result;
		}

		// 根据appId查询用户实体
		UserInfoEntity userDbInfo = userInfoJPA.findByAppId(appId);
		// 如果不存在
		if (userDbInfo == null) {
			result.setFlag(false);
			result.setMsg("appId : " + appId + ", is not found!");
			return result;
		}

		// 验证appSecret是否存在

		if (!new String(userDbInfo.getAppSecret()).equals(appSecret.replace(" ", "+"))) {
			result.setFlag(false);
			result.setMsg("appSecret is not effective!");
			return result;
		}
		// 检测数据库是否存在该appId的token值
		TokenInfoEntity tokenDBEntity = tokenJPA.findByAppId(appId);
		// 返回token值
		String tokenStr = null;
		// tokenDBEntity == null -> 生成newToken -> 保存数据库 -> 写入内存 -> 返回newToken
		if (tokenDBEntity == null) {
			// 生成jwt,Token
			tokenStr = createNewToken(appId);
			// 将token保持到数据库
			tokenDBEntity = new TokenInfoEntity();
			tokenDBEntity.setAppId(userDbInfo.getAppId());
			tokenDBEntity.setBuildTime(String.valueOf(System.currentTimeMillis()));
			tokenDBEntity.setToken(tokenStr.getBytes());
			tokenJPA.save(tokenDBEntity);
			result.setToken(tokenStr);
			return result;
		}
		// tokenDBEntity != null -> 验证是否超时 ->
		// 不超时 -> 直接返回dbToken
		// 超时 -> 生成newToken -> 更新dbToken -> 更新内存Token -> 返回newToken

		// 判断数据库中token是否过期，如果没有过期不需要更新直接返回数据库中的token即可
		// 数据库中生成时间
		long dbBuildTime = Long.valueOf(tokenDBEntity.getBuildTime());
		// 当前时间
		long currentTime = System.currentTimeMillis();
		// 如果当前时间 - 数据库中生成时间 < 7200 证明可以正常使用
		long second = TimeUnit.MILLISECONDS.toSeconds(currentTime - dbBuildTime);
		if (second > 0 && second < 7200) {
			tokenStr = new String(tokenDBEntity.getToken());
			/// 要刷新时间吧
			result.setToken(tokenStr);
			return result;
		}
		// 超时
		// 生成newToken
		tokenStr = createNewToken(appId);
		// 更新token
		tokenDBEntity.setToken(tokenStr.getBytes());
		// 更新生成时间
		tokenDBEntity.setBuildTime(String.valueOf(System.currentTimeMillis()));
		// 执行更新
		tokenJPA.save(tokenDBEntity);
		// 设置返回token
		result.setToken(tokenStr);
		return result;
	}

	/**
	 * 创建新token
	 * 
	 * @param appId
	 * @return
	 */
	private String createNewToken(String appId) {
		// 获取当前时间
		Date now = new Date(System.currentTimeMillis());
		// 过期时间
		Date expiration = new Date(now.getTime() + 7200000);
		return Jwts.builder().setSubject(appId)
				// .claim(YAuthConstants.Y_AUTH_ROLES, userDbInfo.getRoles())
				.setIssuedAt(now).setIssuer("Online YAuth Builder").setExpiration(expiration).signWith(SignatureAlgorithm.HS256, "HengYuAuthv1.0.0").compact();
	}
}
