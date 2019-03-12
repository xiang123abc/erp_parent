package cn.itcast.erp.dao;

import cn.itcast.erp.entity.Emp;

public interface IEmpDao extends IBaseDao<Emp> {

	public Emp findByUsernameAndPwd(String username, String pwd);
}
