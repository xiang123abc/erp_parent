package cn.ecit.erp.action;

import cn.ecit.erp.biz.IEmpBiz;
import cn.ecit.erp.entity.Emp;

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
