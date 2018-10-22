package com.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssh.entity.User;
import com.ssh.repository.UserRepository;
import com.ssh.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public int save() {
		// TODO Auto-generated method stub
		User user = new User();
    	user.setUsername("张三");
    	user.setPassword("1234");
		return userRepository.save(user);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		 userRepository.delete(id);;
	}

}
