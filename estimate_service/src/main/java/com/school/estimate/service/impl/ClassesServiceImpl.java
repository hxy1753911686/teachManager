package com.school.estimate.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.dao.BaseDao;
import com.school.estimate.domain.Classes;
import com.school.estimate.service.ClassesService;


public class ClassesServiceImpl implements ClassesService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Classes> find(String hql, Class<Classes> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Classes get(Class<Classes> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return (Classes) baseDao.get(entityClass, id);
	}

	@Override
	public void saveOrUpdate(Classes entity) {
		// TODO Auto-generated method stub
		//判断是否id是否存在,存在则为更新
		if(entity.getId() == null || entity.getId().equals("null") || entity.getId().equals("")){
			//不存在,则设置isUse为1
			entity.setIsUse(1);
	      }
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Classes> entitys) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<Classes> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Classes> entityClass, Serializable[] ids) {
		// TODO Auto-generated method stub
		baseDao.delete(entityClass, ids);
	}

}
