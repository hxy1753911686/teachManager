package com.school.estimate.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.domain.Classes;

public interface ClassesService {
	//查询所有，带条件查询
	public List<Classes> find(String hql, Class<Classes> entityClass, Object[] params);
	//获取一条记录
	public Classes get(Class<Classes> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
//		public Page<Classes> findPage(String hql, Page<Classes> page, Class<Classes> entityClass, Object[] params);
	
	//新增和修改保存
	public void saveOrUpdate(Classes entity);
	//批量新增和修改保存
	public void saveOrUpdateAll(Collection<Classes> entitys);
	
	//单条删除，按id
	public void deleteById(Class<Classes> entityClass, Serializable id);
	//批量删除
	public void delete(Class<Classes> entityClass, Serializable[] ids);
}
