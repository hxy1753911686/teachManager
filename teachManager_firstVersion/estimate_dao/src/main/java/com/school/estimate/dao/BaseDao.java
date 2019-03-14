package com.school.estimate.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;



public interface BaseDao<T> {

	//查询所有，带条件查询
	public List<T> find(String hql, Class<T> entityClass, Object[] params);
	//获取一条记录
	public T get(Class<T> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
//	public Page<T> findPage(String hql, Page<T> page, Class<T> entityClass, Object[] params);
	
	//新增和修改保存
	public void saveOrUpdate(T entity);
	//批量新增和修改保存
	public void saveOrUpdateAll(Collection<T> entitys);
	
	//单条删除，按id
	public void deleteById(Class<T> entityClass, Serializable id);
	//批量删除
	public void delete(Class<T> entityClass, Serializable[] ids);
	//带条件查询的查询所有
	public List<T> find(String sql,Object[] params, Class<T> entityClass);

}