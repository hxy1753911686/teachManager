package com.school.estimate.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.domain.Score;

public interface ScoreService {
	//查询所有，带条件查询
	public List<Score> find(String hql, Class<Score> entityClass, Object[] params);
	//获取一条记录
	public Score get(Class<Score> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
//		public Page<Score> findPage(String hql, Page<Score> page, Class<Score> entityClass, Object[] params);
	
	//新增和修改保存
	public void saveOrUpdate(Score entity);
	//批量新增和修改保存
	public void saveOrUpdateAll(Collection<Score> entitys);
	
	//单条删除，按id
	public void deleteById(Class<Score> entityClass, Serializable id);
	//批量删除
	public void delete(Class<Score> entityClass, Serializable[] ids);
}
