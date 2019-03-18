 package cn.ecit.erp.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 订单明细实体类
 * @author Administrator *
 */
public class Orderdetail {	
	private String uuid;//编号
	private String orderdetailcode;
	private String orderscode;
	private String code1;//编号1
	private String code2;//预留字段
	private String goodscode;//商品编号
	private String goodsname;//商品名称
	private String goodstype;//商品类型
	private Double price;//价格
	private int num;//数量
	private Double money;//金额
	private int height;
	private int weight;
	private Double area;
	private String note;
	private java.util.Date endtime;//结束日期
	private String ender;//库管员
	private String storecode;//仓库编号
	private String state;//状态   采购订单  0未入库 1 已入库    销售订单 0未出库 1 已出库
//	private Long ordersuuid;//订单编号
	@JSONField(serialize=false)
	private Orders orders;
	
	
	public String getGoodstype() {
		return goodstype;
	}
	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getOrderscode() {
		return orderscode;
	}
	public void setOrderscode(String orderscode) {
		this.orderscode = orderscode;
	}
	/*public String getOrderscode() {
		return orderscode;
	}
	public void setOrderscode(String orderscode) {
		this.orderscode = orderscode;
	}*/
	public String getOrderdetailcode() {
		return orderdetailcode;
	}
	public void setOrderdetailcode(String orderdetailcode) {
		this.orderdetailcode = orderdetailcode;
	}
	
	
	public String getGoodscode() {
		return goodscode;
	}
	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getEnder() {
		return ender;
	}
	public void setEnder(String ender) {
		this.ender = ender;
	}
	public String getStorecode() {
		return storecode;
	}
	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}
	public String getUuid() {
		return uuid;
	}
	public String getCode1() {
		return code1;
	}
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
	
	public String getGoodsname() {		
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public Double getPrice() {		
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getMoney() {		
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public java.util.Date getEndtime() {		
		return endtime;
	}
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	
	
	public String getState() {		
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
