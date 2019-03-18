package cn.ecit.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;

import cn.ecit.erp.biz.IEmpBiz;
import cn.ecit.erp.entity.Emp;

public class LoginAction extends BaseAction<Emp>{
	private String username;//登陆用户名
	private String pwd;//密码
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void checkUser(){
		try{
			/*//查询是否存在
			Emp loginUser = empBiz.findByUsernameAndPwd(username, pwd);
			if(loginUser != null){
				//记录当前登陆的用记
				ActionContext.getContext().getSession().put("loginUser", loginUser);
				ajaxReturn(true, "");
			}else{
				ajaxReturn(false, "用户名或密码不正确");
			}*/
			//1. 创建令牌,身份证明
			UsernamePasswordToken upt = new UsernamePasswordToken(username, pwd);
			//2. 获取主题 subject: 封装当前用户的一些操作
			Subject subject = SecurityUtils.getSubject();
			//3. 执行login
			subject.login(upt);
			ajaxReturn(true, "");
		}catch(Exception ex){
			ex.printStackTrace();
			ajaxReturn(false, "登陆失败");
		}
	}
	
	/**
	 * 显示登陆用户名
	 */
	public void showName(){
		//获取当前登陆的用户
		//Emp emp = (Emp) ActionContext.getContext().getSession().get("loginUser");
		//session是否会超时，用户是否登陆过了
		//获取主题
		Subject subject = SecurityUtils.getSubject();
		//提取主角,拿到emp
		Emp emp = (Emp)subject.getPrincipal();
		if(null != emp){
			ajaxReturn(true, emp.getName());
		}else{
			ajaxReturn(false, "");
		}
	}
	
	/**
	 * 退出登陆
	 */
	public void loginOut(){
		ActionContext.getContext().getSession().remove("loginUser");
		SecurityUtils.getSubject().logout();
	}
	
	/**
	 * 返回前端操作结果
	 * @param success
	 * @param message
	 */
	public String ajaxReturn(boolean success, String message){
		//返回前端的JSON数据
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		//JSON.toJSONString(rtn) => {"success":true,"message":'超级管理员'}
		write(JSON.toJSONString(rtn));
		return null;
	}
	
	/**
	 * 输出字符串到前端
	 * @param jsonString
	 */
	public void write(String jsonString){
		try {
			//响应对象
			HttpServletResponse response = ServletActionContext.getResponse();
			//设置编码
			response.setContentType("text/html;charset=utf-8"); 
			//输出给页面
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
