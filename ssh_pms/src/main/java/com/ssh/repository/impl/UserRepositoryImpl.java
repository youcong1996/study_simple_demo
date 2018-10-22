package com.ssh.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssh.entity.User;
import com.ssh.repository.UserRepository;
@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}
	
	@Override
	public User load(Integer id) {
		// TODO Auto-generated method stub
		return getCurrentSession().load(User.class, id);
	}

	@Override
	public User get(Integer id) {
		// TODO Auto-generated method stub
		return getCurrentSession().get(User.class, id);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void persist(User entity) {
		// TODO Auto-generated method stub
		getCurrentSession().persist(entity);
	}

	@Override
	public Integer save(User entity) {
		// TODO Auto-generated method stub
		return (Integer) getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(User entity) {
		// TODO Auto-generated method stub
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		getCurrentSession().delete(id);
		}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		getCurrentSession().flush();
	}

}
