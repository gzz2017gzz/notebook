package com.gzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gzz.entity.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Long>{

}
