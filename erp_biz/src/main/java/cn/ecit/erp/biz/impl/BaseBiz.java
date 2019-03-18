package cn.ecit.erp.biz.impl;

import java.util.List;

import cn.ecit.erp.dao.IBaseDao;

public class BaseBiz<T> {

	private IBaseDao baseDao;
	
	
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public List<T> getList(){
		return baseDao.getList();
	}

	/**
	 * 条件查询
	 * @param t1
	 * @return
	 */
	public List<T> getList(T t1,T t2,Object param){
		return baseDao.getList(t1,t2,param);
	}

	public long getCount(T t1,T t2,Object param) {
		return baseDao.getCount(t1,t2,param);
	}

	public void add(T t) {
		baseDao.add(t);
	}

	/**
	 * 删除
	 */
	public void delete(String code1){
		baseDao.delete(code1);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public T get(String code1){
		return (T) baseDao.get(code1);
	}
	
	/**
	 * 更新
	 */
	public void update(T t){
    	baseDao.update(t);
	}

}
