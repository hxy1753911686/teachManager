package com.school.estimate.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.domain.Teacher;

public interface TeacherService {
	//查询所有，带条件查询
	public List<Teacher> find(String hql, Class<Teacher> entityClass, Object[] params);
	//获取一条记录
	public Teacher get(Class<Teacher> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
//		public Page<Teacher> findPage(String hql, Page<Teacher> page, Class<Teacher> entityClass, Object[] params);
	
	//新增和修改保存
	public void saveOrUpdate(Teacher entity);
	//批量新增和修改保存
	public void saveOrUpdateAll(Collection<Teacher> entitys);
	
	//单条删除，按id
	public void deleteById(Class<Teacher> entityClass, Serializable id);
	//批量删除
	public void delete(Class<Teacher> entityClass, Serializable[] ids);
}
