package com.school.estimate.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.dao.BaseDao;
import com.school.estimate.domain.TeacherClassManage;
import com.school.estimate.service.TeacherClassManageService;


public class TeacherClassManageServiceImpl implements TeacherClassManageService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<TeacherClassManage> find(String hql, Class<TeacherClassManage> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public TeacherClassManage get(Class<TeacherClassManage> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return (TeacherClassManage) baseDao.get(entityClass, id);
	}

	@Override
	public void saveOrUpdate(TeacherClassManage entity) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<TeacherClassManage> entitys) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<TeacherClassManage> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<TeacherClassManage> entityClass, Serializable[] ids) {
		// TODO Auto-generated method stub
		baseDao.delete(entityClass, ids);
	}

}
