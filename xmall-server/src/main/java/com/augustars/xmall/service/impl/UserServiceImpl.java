package com.augustars.xmall.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.augustars.xmall.entity.User;
import com.augustars.xmall.service.UserService;

@Service("userService")
@com.alibaba.dubbo.config.annotation.Service(version="1.0.0")
@Transactional
public class UserServiceImpl implements UserService{

	public User getUserByLoginName(String loginName) throws Exception {
		User user = new User();
		user.setLoginName("admin");
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");
		user.setUsername("蔡之峰");
		return user;
	}

}
