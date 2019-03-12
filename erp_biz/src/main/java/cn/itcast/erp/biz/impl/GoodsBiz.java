package cn.itcast.erp.biz.impl;
import java.util.List;

import cn.itcast.erp.biz.IGoodsBiz;
import cn.itcast.erp.dao.IGoodsDao;
import cn.itcast.erp.entity.Goods;
/**
 * 商品业务逻辑类
 * @author Administrator
 *
 */
public class GoodsBiz  implements IGoodsBiz {

	private IGoodsDao goodsDao;
	
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
		//setBaseDao(goodsDao);
	}

	
	public List<Goods> getList(){
		return goodsDao.getList();
	}
	/**
	 * 条件查询
	 * @param emp1
	 * @reempurn
	 */
	public List<Goods> getList(Goods emp1,Goods emp2,Object param){
		return goodsDao.getList(emp1,emp2,param);
	}



	public void add(Goods emp) {
		goodsDao.add(emp);
	}

	/**
	 * 删除
	 */
	public void delete(String code1){
		goodsDao.delete(code1);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @reempurn
	 */
	public Goods get(String code1){
		return  (Goods)goodsDao.get(code1);
	}
	
	/**
	 * 更新
	 */
	public void update(Goods emp){
		goodsDao.update(emp);
	}

	

	public long getCount(Goods t1, Goods t2, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
}
