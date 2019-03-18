package cn.ecit.erp.biz;

import java.util.List;

public interface IBaseBiz<T> {
	
	
	public List<T> getList();

	/**
	 * 条件查询
	 * @param t1
	 * @return
	 */
	List<T> getList(T t1,T t2,Object param);
	
	public long getCount(T t1,T t2,Object param);
	
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
}
