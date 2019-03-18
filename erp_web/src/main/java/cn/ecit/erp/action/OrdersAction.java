package cn.ecit.erp.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.ecit.erp.biz.IOrdersBiz;
import cn.ecit.erp.dao.impl.OrdersDao;
import cn.ecit.erp.entity.Orderdetail;
import cn.ecit.erp.entity.Orders;

/**
 * 璁㈠崟Action 
 * @author Administrator
 *
 */
public class OrdersAction extends BaseAction<Orders> {
	
	private OrdersDao ordersDao;
	
	public OrdersDao getOrdersDao() {
		return ordersDao;
	}

	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	private IOrdersBiz ordersBiz;
	
	//鎺ユ敹璁㈠崟鏄庣粏鐨刯son鏍煎紡鐨勫瓧绗�,鏁扮粍褰㈠紡鐨刯son瀛楃涓�,閲岄潰鐨勫厓绱犲簲璇ユ槸姣忎釜璁㈠崟鏄庣粏
		private String json;
		
	private String suppliercode;

	private String sn;

	private String orderdetailsJSON;
	
	
	public String getSuppliercode() {
		return suppliercode;
	}

	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}

	public IOrdersBiz getOrdersBiz() {
		return ordersBiz;
	}

	public String getOrderdetailsJSON() {
		return orderdetailsJSON;
	}

	public void setOrderdetailsJSON(String orderdetailsJSON) {
		this.orderdetailsJSON = orderdetailsJSON;
	}

	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public void setOrdersBiz(IOrdersBiz ordersBiz) {
		this.ordersBiz = ordersBiz;
		setBaseBiz(ordersBiz);
	}
	
	public void listByPage(){
		int page = getPage();
		int rows = getRows();
	
		//System.out.println("椤电爜锛�" + page + " 璁板綍鏁�:" + rows);
		int firstResult = (page -1) * rows;
		List<Orders> list = ordersDao.getListByPage(null,null,null,firstResult, rows);
		
		
		long total = ordersDao.getCount(null,null,null);
		//{total: total, rows:[]}
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", total);
		mapData.put("rows", list);
		//鎶婇儴闂ㄥ垪琛ㄨ浆JSON瀛楃涓�
		//DisableCircularReferenceDetect绂佺敤寰幆寮曠敤淇濇姢
		String listString = JSON.toJSONString(mapData, SerializerFeature.DisableCircularReferenceDetect);
		write(listString);
	}

	/**
	 * 娣诲姞璁㈠崟
	 */
	public void add(){
		//own
		Orders orders = getT();	
		
		
//		鎶妀son瀛楃涓茶浆鎴恖ist闆嗗悎
	//	List<Orderdetail> orderdetails = JSON.parseArray(orderdetailsJSON, Orderdetail.class);
		List<Orderdetail> orderdetails = JSON.parseArray(json, Orderdetail.class);
		orders.setOrderdetails(orderdetails);
		try {
			ordersBiz.add(orders, suppliercode);
			write(ajaxReturn(true, "保存成功"));
		} catch (Exception e) {
			write(ajaxReturn(false, "保存失败"));
			e.printStackTrace();
		}
	}
	

	/**
	 * 	瀵煎嚭
	 * @throws FileNotFoundException
	 * @throws IOException
	 *//**
	 * 瀵煎嚭璁㈠崟
	 */
	public void export(){
		String filename = "Orders_" + getId() + ".xls";
		//鍝嶅簲瀵硅薄
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			 response.setHeader("Content-Disposition", "attachment;filename=" +"abc"+".xls");
		        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		        response.setHeader("Pragma", "no-cache");
		        response.setHeader("Cache-Control", "no-cache");
		        response.setDateHeader("Expires", 0);
			ordersBiz.export(response.getOutputStream(), getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exportAll() throws ParseException{
		String suppliercode = getSuppliercode();
		String beforeDate=getBeforeDate();
		String afterDate = getAfterDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date befDate = sdf.parse(beforeDate);
        Date aftDate = sdf.parse(afterDate);
        Orders orders = new Orders();
        orders.setStarttime(befDate);
        orders.setEndtime(aftDate);
        orders.setSuppliercode(suppliercode);
        
		//鍝嶅簲瀵硅薄
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			 response.setHeader("Content-Disposition", "attachment;filename=" +"abc"+".xls");
		        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		        response.setHeader("Pragma", "no-cache");
		        response.setHeader("Cache-Control", "no-cache");
		        response.setDateHeader("Expires", 0);
		        String filepath = ServletActionContext.getServletContext().getRealPath(File.separator)+"ExcleModel"+File.separator+"MouthOrders.xls";  //工程部署的路径
			ordersBiz.exportAll(null,response.getOutputStream(), orders);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
