package cn.itcast.erp.entity;

import java.util.List;

/**
 * 订单实体类
 * @author Administrator *
 */
public class Orders {	
	
	private String uuid;//uuid
	private String orderscode;
	private String color;
	private String material;//材料
	private String model;//造型
	private java.util.Date starttime;//开始日期
	private java.util.Date endtime;//结束日期
	private String type;//订单类型    1采购     2 销售
	private String starter;//采购员
	private String ender;//库管员
	private String suppliercode;//供应商ID  
	private Double totalmoney;//总金额   
	private String state;//订单状态    采购订单 0未审核  1已审核  2已确认   3已完成    销售订单 0未出库     3已完成
    private Double totalarea;
	private String sn; //运单号
	private String address;
	private List<Orderdetail> orderdetails;  //订单项
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getTotalarea() {
		return totalarea;
	}
	public void setTotalarea(Double totalarea) {
		this.totalarea = totalarea;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOrderscode() {
		return orderscode;
	}
	public void setOrderscode(String orderscode) {
		this.orderscode = orderscode;
	}
	
	
	public String getOrdercode() {
		return orderscode;
	}
	public void setOrdercode(String orderscode) {
		this.orderscode = orderscode;
	}
	public String getSuppliercode() {
		return suppliercode;
	}
	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}
	
	
	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	

	
	public java.util.Date getStarttime() {		
		return starttime;
	}
	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}
	public java.util.Date getEndtime() {		
		return endtime;
	}
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	public String getType() {		
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStarter() {		
		return starter;
	}
	public void setStarter(String starter) {
		this.starter = starter;
	}
	public String getEnder() {		
		return ender;
	}
	public void setEnder(String ender) {
		this.ender = ender;
	}
	
	public Double getTotalmoney() {		
		return totalmoney;
	}
	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}
	public String getState() {		
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
