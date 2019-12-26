package com.gzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzz.entity.BaseEntity;
import com.gzz.entity.NavigationEntity;
import com.gzz.repository.NavigationRepository;

@Service
public class NavigationServiceImpl implements NavigationService {
	@Autowired
	private NavigationRepository navigationRepository;
	
	/**
	 * 根据type查询菜单
	 * @param type
	 * @return
	 */
	public List<NavigationEntity> findByType(int type) {
		return navigationRepository.findByTypeAndStatusIs(type, BaseEntity.STATUS_VALID);
	}
}
