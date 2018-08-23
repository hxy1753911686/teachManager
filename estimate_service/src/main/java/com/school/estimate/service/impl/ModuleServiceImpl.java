package com.school.estimate.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.dao.BaseDao;
import com.school.estimate.domain.Module;
import com.school.estimate.service.ModuleService;

public class ModuleServiceImpl implements ModuleService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Module> find(String hql, Class<Module> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Module get(Class<Module> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return (Module) baseDao.get(entityClass, id);
	}

	@Override
	public void saveOrUpdate(Module entity) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Module> entitys) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<Module> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Module> entityClass, Serializable[] ids) {
		// TODO Auto-generated method stub
		baseDao.delete(entityClass, ids);
	}

}
