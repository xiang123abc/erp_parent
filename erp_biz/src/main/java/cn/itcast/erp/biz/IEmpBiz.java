package cn.itcast.erp.biz;

import cn.itcast.erp.entity.Emp;

/**
 * 部门业务接口
 * @author Administrator
 *
 */
public interface IEmpBiz extends IBaseBiz<Emp>{
	public Emp findByUsernameAndPwd(String username, String pwd);

}
