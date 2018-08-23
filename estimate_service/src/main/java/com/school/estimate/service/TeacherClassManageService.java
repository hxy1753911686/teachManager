package com.school.estimate.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.school.estimate.domain.TeacherClassManage;

public interface TeacherClassManageService {
	//查询所有，带条件查询
	public List<TeacherClassManage> find(String hql, Class<TeacherClassManage> entityClass, Object[] params);
	//获取一条记录
	public TeacherClassManage get(Class<TeacherClassManage> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
//		public Page<TeacherClassManage> findPage(String hql, Page<TeacherClassManage> page, Class<TeacherClassManage> entityClass, Object[] params);
	
	//新增和修改保存
	public void saveOrUpdate(TeacherClassManage entity);
	//批量新增和修改保存
	public void saveOrUpdateAll(Collection<TeacherClassManage> entitys);
	
	//单条删除，按id
	public void deleteById(Class<TeacherClassManage> entityClass, Serializable id);
	//批量删除
	public void delete(Class<TeacherClassManage> entityClass, Serializable[] ids);
}
