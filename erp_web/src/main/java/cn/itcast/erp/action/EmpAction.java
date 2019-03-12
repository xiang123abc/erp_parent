package cn.itcast.erp.action;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.entity.Emp;

/**
 * 部门Action
 * @author Administrator
 *
 */
public class EmpAction extends BaseAction<Emp>{

	private IEmpBiz empBiz;
	
	

	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
		setBaseBiz(empBiz);
	}

}
