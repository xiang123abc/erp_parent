package cn.itcast.erp.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionContext;

import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Orders;
public class BaseAction<T> {

	
    private IBaseBiz<T> baseBiz;
	
	public void setBaseBiz(IBaseBiz baseBiz) {
		this.baseBiz = baseBiz;
	}
	
	public IBaseBiz getBaseBiz() {
		return baseBiz;
	}


	//属性驱动:条件查询
	private T t1;
	private T t2;
	private Object param;
	private String beforeDate;
	private String afterDate;
	
	public String getBeforeDate() {
		return beforeDate;
	}

	public void setBeforeDate(String beforeDate) {
		this.beforeDate = beforeDate;
	}

	public String getAfterDate() {
		return afterDate;
	}

	public void setAfterDate(String afterDate) {
		this.afterDate = afterDate;
	}

	public T getT2() {
		return t2;
	}
	public void setT2(T t2) {
		this.t2 = t2;
	}
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}
	public T getT1() {
		return t1;
	}
	public void setT1(T t1) {
		this.t1 = t1;
	}
	
	private int page;//页码
	private int rows;//每页的记录数
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	/**
	 * 获取当前登录人
	 * @return
	 */
	public Emp getUser(){
		return   (Emp)ActionContext.getContext().getSession().get("loginUser");
	}
	
	/**
	 * 查询全部列表
	 */
	public void list(){
		
		List<T> list = baseBiz.getList(t1,t2,param);
		String jsonString = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);			
		write(jsonString);
	}
  
	/**
	 * 查询全部列表
	 */
	/*public void list(){
		
		List<T> list = getBaseBiz().getList();
		String jsonString = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);			
		write(jsonString);
	}*/

	
	/**
	 * 条件查询
	 */
	
	/*public void listPage(){
		int firstResult=(page-1)*rows;
		List<T> list = baseBiz.getListByPage(t1, t2, param, firstResult, rows);
		long count = baseBiz.getCount(t1, t2, param);		
		Map map=new HashMap();
		map.put("rows", list);
		map.put("total", count);		
		String jsonString = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);//取消循环引用
		write(jsonString);
	}*/

	
	/**新增，修改*/
	private T t;
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	/**
	 * 新增
	 * @param jsonString
	 */
	public void add() throws Exception{
		
		try {
			getBaseBiz().add(t);
			write(ajaxReturn(true, "增加成功"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			write(ajaxReturn(false, "增加失败"));
		}
	}
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 删除
	 * @param jsonString
	 */
	public void delete(){
		
		
		try {
			getBaseBiz().delete(id);
			write(ajaxReturn(true, "删除成功"));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "删除失败"));
		}
		
	}
	
	/**
	 * 通过编辑查询对象
	 */
	public void get(){
		T t = (T) getBaseBiz().get(id);
		String jsonString = JSON.toJSONString(t);
		jsonString = JSON.toJSONStringWithDateFormat(t, "yyyy-MM-dd");
		write(mapJson(jsonString, "t"));
	}
	
	/**
	 * 修改
	 */
	public void update(){
		try {
			//Method method = getBaseBiz().getClass().getMethod("setUuid"); // 父类对象调用子类方法(反射原理)
			 //Object o = method.invoke(getBaseBiz(),UuidHelper.primitive());
			getBaseBiz().update(t);
			write(ajaxReturn(true, "修改成功"));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "修改失败"));
		}
	}
	
	/**
	 * //{"name":"管理员组","tele":"000011","uuid":1} 
	 * @param jsonString JSON数据字符串
	 * @param prefix 要加上的前缀
	 * @return  {"t.name":"管理员组","t.tele":"000011","t.uuid":1} 
	 */
	public String mapData(String jsonString, String prefix){
		Map<String, Object> map = JSON.parseObject(jsonString);
		
		//存储key加上前缀后的值
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//给每key值加上前缀
		for(String key : map.keySet()){
			dataMap.put(prefix + "." + key, map.get(key));
		}
		return JSON.toJSONString(dataMap);
	}
	
	public String ajaxReturn(boolean success, String message){
		//返回前端的JSON数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success",success);
		map.put("message",message);
		//write(JSON.toJSONString(map));
		return JSON.toJSONString(map);
	}
	
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
	
	/**
	 * 自定添加前缀
	 * @param jsonString
	 * @param prefix
	 * @return
	 */
	private String mapJson(String jsonString,String prefix){
//		未加前缀的数据                               t.dep.name
//		{"address":"火云洞","birthday":"2016-12-04","dep":{"name":"公关部","tele":"444444","uuid":5},
//			"email":"ssaassa","gender":0,"name":"狐狸精","tele":"12122112","username":"hulijing","uuid":7}
		Map<String,Object> map=JSON.parseObject(jsonString);
		Map<String,Object> newmap=new HashMap();
		for(String key:map.keySet()){
			if((map.get(key)) instanceof Map){
				Map<String,Object> map2 = (Map) map.get(key);
				for(String key2:map2.keySet()){
					newmap.put(prefix+"."+key+"."+key2, map2.get(key2));
				}
			}else{
				newmap.put(prefix+"."+key, map.get(key));			
			}
		}
//		<!-- {"t.address":"火云洞","t.birthday":"2016-12-04","t.dep":{"name":"公关部","tele":"444444","uuid":5},"t.email":"ssaassa", -->
//		<!-- "t.gender":0,"t.name":"狐狸精","t.tele":"12122112","t.username":"hulijing","t.uuid":7} -->
//
//		<!-- "t.dep":{"name":"公关部","tele":"444444","uuid":5},    "t.dep.name":"公关部","t.dep.tele":"444444","t.dep.uuid":5 -->
		
		
		return  JSON.toJSONString(newmap);		
	}
	
}
