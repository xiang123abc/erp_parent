package cn.ecit.erp.biz;

import cn.ecit.erp.entity.Emp;

/**
 * 部门业务接口
 * @author Administrator
 *
 */
public interface IEmpBiz extends IBaseBiz<Emp>{
	public Emp findByUsernameAndPwd(String username, String pwd);

}
