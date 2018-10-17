package com.blog.service;

import com.blog.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youcong123
 * @since 2018-07-22
 */
public interface UserService extends IService<User> {

	
	User getUserById(String userId);
	
	User login(String loginCode);
}
