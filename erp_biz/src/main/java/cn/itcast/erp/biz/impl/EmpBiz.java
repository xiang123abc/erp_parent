package cn.itcast.erp.biz.impl;

import java.util.List;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.entity.Emp;


/**
 * 部门业务实现
 * @author Administrator
 *
 */
public class EmpBiz  implements IEmpBiz{
	
	/** 数据访问注入*/
	private IEmpDao empDao;

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
	}

	/**
	 * 查询全部
	 * @param emp1
	 * @reempurn
	 */
	public List<Emp> getList(){
		return empDao.getList();
	}

	/**
	 * 条件查询
	 * @param emp1
	 * @reempurn
	 */
	public List<Emp> getList(Emp emp1,Emp emp2,Object param){
		return empDao.getList(emp1,emp2,param);
	}



	public void add(Emp emp) {
		empDao.add(emp);
	}

	/**
	 * 删除
	 */
	public void delete(String code1){
		empDao.delete(code1);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @reempurn
	 */
	public Emp get(String code1){
		return  (Emp)empDao.get(code1);
	}
	
	/**
	 * 更新
	 */
	public void update(Emp emp){
		empDao.update(emp);
	}

	

	public long getCount(Emp t1, Emp t2, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 用户登陆
	 * @param username
	 * @param pwd
	 * @return
	 */
	public Emp findByUsernameAndPwd(String username, String pwd){
		//查询前先加密
		//pwd = encrypt(pwd, username);
		System.out.println(pwd);
		return empDao.findByUsernameAndPwd(username, pwd);
	}

}
