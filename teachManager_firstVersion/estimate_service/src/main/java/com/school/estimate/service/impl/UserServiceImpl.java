package com.school.estimate.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.dao.BaseDao;
import com.school.estimate.domain.User;
import com.school.estimate.service.UserService;

public class UserServiceImpl implements UserService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<User> find(String hql, Class<User> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public User get(Class<User> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return (User) baseDao.get(entityClass, id);
	}

	@Override
	public void saveOrUpdate(User entity) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<User> entitys) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<User> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<User> entityClass, Serializable[] ids) {
		// TODO Auto-generated method stub
		baseDao.delete(entityClass, ids);
	}

	@Override
	public List<User> find(String sql, Object[] params, Class<User> entityClass) {
		// TODO Auto-generated method stub
		return baseDao.find(sql, params, entityClass);
	}

}
