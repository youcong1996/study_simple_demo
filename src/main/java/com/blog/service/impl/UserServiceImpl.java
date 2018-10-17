package com.blog.service.impl;

import com.blog.entity.User;
import com.blog.mapper.UserDao;
import com.blog.service.UserService;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author youcong123
 * @since 2018-07-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public User getUserById(String userId) {
		
		return userDao.selectById(userId);
	}
	@Override
	public User login(String loginCode) {
	
		User user = new User();
		user.setLoginCode(loginCode);
		return userDao.selectOne(user);
	}

}
