package cn.itcast.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.dao.IBaseDao;


public class BaseDao<T> extends HibernateDaoSupport {

	
	private Class<T> entityClass;
	
	public BaseDao(){
		Type type = getClass().getGenericSuperclass();
		ParameterizedType ptype=(ParameterizedType)type;
		Type[] types = ptype.getActualTypeArguments();
		entityClass=(Class<T>) types[0];		
	}
	
	
	public List<T> getList() {
		
	 DetachedCriteria dc = getDetachedCriteria();
	 return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
	}
	/**
	 * 查询所有的部门信息
	 *//*
	public List<T> getList() {
		return (List<T>) this.getHibernateTemplate().find("from T");
	}*/

	/**
	 * 条件查询
	 */
	public List<T> getList(T t1,T t2,Object param) {
		if(t1!=null) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		 return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
		}else {
			DetachedCriteria dc = getDetachedCriteria();
			 return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
		}
			
	}

	/**
	 * 记录条件查询的总记录数
	 * @param t1
	 * @return
	 */
	public long getCount(T t1,T t2,Object param) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}
	
	/**
	 * 新增
	 * @param t
	 */
	public void add(T t){
		this.getHibernateTemplate().save(t);
	}
	
	/**
	 * 删除
	 */
	public void delete(int id){
		//让对象进入持久化状态
		T t = this.getHibernateTemplate().get(entityClass, id);
		//删除持久化状态
		this.getHibernateTemplate().delete(t);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public T get(int id){
		return getHibernateTemplate().get(entityClass, id);
	}
	
	/**
	 * 更新
	 */
	public void update(T t){
		this.getHibernateTemplate().update(t);
	}
	
	public DetachedCriteria getDetachedCriteria(T t1,T t2,Object param){
		return null;
	}
	public DetachedCriteria getDetachedCriteria(){
		return null;
	}
	

}
