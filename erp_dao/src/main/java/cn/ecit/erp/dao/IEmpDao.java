package cn.ecit.erp.dao;

import cn.ecit.erp.entity.Emp;

public interface IEmpDao extends IBaseDao<Emp> {

	public Emp findByUsernameAndPwd(String username, String pwd);
}
