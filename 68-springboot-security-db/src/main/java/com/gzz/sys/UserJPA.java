package com.gzz.sys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gzz.config.UserEntity;

public interface UserJPA extends JpaRepository<UserEntity, Long> {
	public UserEntity findByUsername(String username);
}
