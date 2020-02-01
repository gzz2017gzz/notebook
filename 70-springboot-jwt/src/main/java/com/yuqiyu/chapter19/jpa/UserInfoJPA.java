package com.yuqiyu.chapter19.jpa;

import com.yuqiyu.chapter19.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

 
@Repository
public interface UserInfoJPA extends JpaRepository<UserInfoEntity, String>, JpaSpecificationExecutor<UserInfoEntity> {
	UserInfoEntity findByAppId(String appId);
}
