package cn.ecit.erp.entity;
/**
 * 商品分类实体类
 * @author Administrator *
 */
public class Goodstype {	
	private String uuid;//编号
	private String goodstypecode;
	private String name;//名称
	

	public String getGoodstypecode() {
		return goodstypecode;
	}
	public void setGoodstypecode(String goodstypecode) {
		this.goodstypecode = goodstypecode;
	}
	public String getUuid() {		
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
