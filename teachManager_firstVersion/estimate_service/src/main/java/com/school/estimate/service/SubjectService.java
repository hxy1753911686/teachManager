package com.school.estimate.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.domain.Subject;

public interface SubjectService {
	//查询所有，带条件查询
	public List<Subject> find(String hql, Class<Subject> entityClass, Object[] params);
	//获取一条记录
	public Subject get(Class<Subject> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
//		public Page<Subject> findPage(String hql, Page<Subject> page, Class<Subject> entityClass, Object[] params);
	
	//新增和修改保存
	public void saveOrUpdate(Subject entity);
	//批量新增和修改保存
	public void saveOrUpdateAll(Collection<Subject> entitys);
	
	//单条删除，按id
	public void deleteById(Class<Subject> entityClass, Serializable id);
	//批量删除
	public void delete(Class<Subject> entityClass, Serializable[] ids);
}
