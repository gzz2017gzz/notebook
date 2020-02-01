package com.yuqiyu.chapter19.entity;

import javax.persistence.*;
import java.io.Serializable;

 
@Entity
@Table(name = "api_token_infos", schema = "jwt")
public class TokenInfoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "ati_id")
	private Long id;
	@Column(name = "ati_app_id")
	private String appId;
	@Column(name = "ati_token")
	private byte[] token;
	@Column(name = "ati_build_time")
	private String buildTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public byte[] getToken() {
		return token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}
}
