package com.school.estimate.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.dao.BaseDao;
import com.school.estimate.domain.Estimate;
import com.school.estimate.service.EstimateService;


public class EstimateServiceImpl implements EstimateService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Estimate> find(String hql, Class<Estimate> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Estimate get(Class<Estimate> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return (Estimate) baseDao.get(entityClass, id);
	}

	@Override
	public void saveOrUpdate(Estimate entity) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Estimate> entitys) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<Estimate> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Estimate> entityClass, Serializable[] ids) {
		// TODO Auto-generated method stub
		baseDao.delete(entityClass, ids);
	}

}
