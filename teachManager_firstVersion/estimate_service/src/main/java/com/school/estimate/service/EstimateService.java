package com.school.estimate.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.domain.Estimate;

public interface EstimateService {
	//查询所有，带条件查询
	public List<Estimate> find(String hql, Class<Estimate> entityClass, Object[] params);
	//获取一条记录
	public Estimate get(Class<Estimate> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
//		public Page<Estimate> findPage(String hql, Page<Estimate> page, Class<Estimate> entityClass, Object[] params);
	
	//新增和修改保存
	public void saveOrUpdate(Estimate entity);
	//批量新增和修改保存
	public void saveOrUpdateAll(Collection<Estimate> entitys);
	
	//单条删除，按id
	public void deleteById(Class<Estimate> entityClass, Serializable id);
	//批量删除
	public void delete(Class<Estimate> entityClass, Serializable[] ids);
}
