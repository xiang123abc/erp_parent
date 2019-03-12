package cn.itcast.erp.dao;

import java.util.Date;
import java.util.List;

import cn.itcast.erp.entity.Orders;

public interface IBaseDao<T> {
	
	
	List<T> getList();
      /*List<T> getList();
	
	*//**
	 * 条件查询
	 * @param t1
	 * @return
	 */
	List<T> getList(T t1,T t2,Object param);
	
	/**
	 * 记录条件查询的总记录数
	 * @param t1
	 * @return
	 */
	long getCount(T t1,T t2,Object param);
	
	/**
	 * 新增
	 * @param t
	 */
	void add(T t);
	
	/**
	 * 删除
	 */
	void delete(String code1);
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	T get(String code1);
	
	/**
	 * 更新
	 */
	void update(T t);
	
	List<Orders> getListBySuppliercoeAndStarttimeAndEndtime(String suppliercode,Date starttime,Date endtime);
	
}


