package cn.ecit.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import cn.ecit.erp.biz.IDepBiz;
import cn.ecit.erp.entity.Dep;


/**
 * 部门Action
 * @author Administrator
 *
 */
public class DepAction extends BaseAction<Dep>{

	private IDepBiz depBiz;


	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
		setBaseBiz(depBiz);
	}

}
