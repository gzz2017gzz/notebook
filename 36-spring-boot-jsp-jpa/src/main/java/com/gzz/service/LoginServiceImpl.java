package com.gzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gzz.entity.UserEntity;
import com.gzz.repository.UserRepository;
import com.gzz.utils.EncryptUtils;
import com.gzz.utils.ServiceException;

/**
 * 登录信息
 * 
 * @author lance
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserRepository userRepository;

	/**
	 * 用户登录
	 * 
	 * @author lance 2014-6-11下午11:26:05
	 * @param user
	 * @return
	 */
	public UserEntity login(UserEntity user) {
		if (StringUtils.isEmpty(user.getEmail())) {
			throw new ServiceException("用户名不能为空");
		}

		if (StringUtils.isEmpty(user.getPassword())) {
			throw new ServiceException("密码不能为空");
		}

		UserEntity userEntity = userRepository.findByEmail(user.getEmail());
		if (null == userEntity) {
			throw new ServiceException("用户名不存在");
		}

		String password = EncryptUtils.encryptMD5(user.getPassword());
		if (!password.equals(userEntity.getPassword())) {
			throw new ServiceException("密码输入错误");
		}

		return userEntity;
	}

}
