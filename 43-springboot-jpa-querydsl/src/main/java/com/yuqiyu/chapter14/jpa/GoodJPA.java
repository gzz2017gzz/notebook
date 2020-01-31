package com.yuqiyu.chapter14.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.yuqiyu.chapter14.entity.GoodEntity;

public interface GoodJPA extends JpaRepository<GoodEntity, Long>, QuerydslPredicateExecutor<GoodEntity> {

}
