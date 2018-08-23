package com.school.estimate.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.dao.BaseDao;
import com.school.estimate.domain.Score;
import com.school.estimate.service.ScoreService;


public class ScoreServiceImpl implements ScoreService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Score> find(String hql, Class<Score> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Score get(Class<Score> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return (Score) baseDao.get(entityClass, id);
	}

	@Override
	public void saveOrUpdate(Score entity) {
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Score> entitys) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<Score> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Score> entityClass, Serializable[] ids) {
		// TODO Auto-generated method stub
		baseDao.delete(entityClass, ids);
	}

}
