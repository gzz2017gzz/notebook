package com.gzz.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TestJwt {
	private static Logger logger = LoggerFactory.getLogger(TestJwt.class);

	private static Long timeLimit = 1000 * 60 * 60 * 24l;// 1天
	public static final String PRIVATEKEY = "privateKey";// 私钥

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "高振中");
		map.put("age", "30");
		String userid = "01";
		String subject = "subject";
		
		
		String token = TestJwt.createToken(userid, subject, map);
		logger.info(token);
		Claims claims = TestJwt.parseToken(token);

		for (Entry<String, Object> entry : claims.entrySet()) {
			logger.info("key={},value={}", entry.getKey(), entry.getValue());
		}

	}

	public static String createToken(String userid, String subject, Map<String, Object> map) {
		SignatureAlgorithm signature = SignatureAlgorithm.HS256;
		Date now = new Date();
		long expMillis = now.getTime() + timeLimit;
		Date expDate = new Date(expMillis);

		JwtBuilder builder = Jwts.builder().setId(userid)// UUID
				.setSubject(subject) // 主题
				.setIssuer("Gzz") // 签发者
				.setIssuedAt(now) // 签发时间
				.signWith(signature, generalKey()) // 签名算法以及密匙
				.setExpiration(expDate); // 过期时间
		for (String key : map.keySet()) {
			builder.claim(key, map.get(key));
		}
		return builder.compact();
	}

	private static SecretKey generalKey() {
		byte[] encodedKey = Base64.getDecoder().decode(PRIVATEKEY);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}

	public static Claims parseToken(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(jwt).getBody();
		return claims;
	}

}
