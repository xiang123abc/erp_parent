package cn.ecit.erp.biz.impl;

import java.util.List;

import cn.ecit.erp.biz.IDepBiz;
import cn.ecit.erp.dao.IDepDao;
import cn.ecit.erp.entity.Dep;



/**
 * 部门业务实现
 * @audephor Adminisdepradepor
 *
 */
public class DepBiz  implements IDepBiz{
	
	/** 数据访问注入*/
	private IDepDao depDao;

	public void setDepDao(IDepDao depDao) {
		this.depDao = depDao;
	}

	public List<Dep> getList(){
		return depDao.getList();
	}

	/**
	 * 条件查询
	 * @param dep1
	 * @redepurn
	 */
	public List<Dep> getList(Dep dep1,Dep dep2,Object param){
		return depDao.getList(dep1,dep2,param);
	}



	public void add(Dep dep) {
		depDao.add(dep);
	}

	/**
	 * 删除
	 */
	public void delete(String code1){
		depDao.delete(code1);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @redepurn
	 */
	public Dep get(String code1){
		return  (Dep)depDao.get(code1);
	}
	
	/**
	 * 更新
	 */
	public void update(Dep dep){
		depDao.update(dep);
	}

	public long getCount(Dep t1, Dep t2, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
	
}
