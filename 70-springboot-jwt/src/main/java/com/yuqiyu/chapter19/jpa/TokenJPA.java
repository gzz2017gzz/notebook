package com.yuqiyu.chapter19.jpa;

import com.yuqiyu.chapter19.entity.TokenInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenJPA extends JpaRepository<TokenInfoEntity, String>, JpaSpecificationExecutor<TokenInfoEntity> {
	TokenInfoEntity findByAppId(String appId);
}
