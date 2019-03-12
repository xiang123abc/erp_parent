package cn.itcast.erp.biz.impl;
import java.util.List;

import cn.itcast.erp.biz.IGoodstypeBiz;
import cn.itcast.erp.dao.IGoodstypeDao;
import cn.itcast.erp.entity.Goodstype;
/**
 * 商品分类业务逻辑类
 * @author Administrator
 *
 */
public class GoodstypeBiz  implements IGoodstypeBiz {

	private IGoodstypeDao goodstypeDao;
	
	public void setGoodstypeDao(IGoodstypeDao goodstypeDao) {
		this.goodstypeDao = goodstypeDao;
		//setBaseDao(goodstypeDao);
	}

	

	public List<Goodstype> getList(){
		return goodstypeDao.getList();
	}
	/**
	 * 条件查询
	 * @param emp1
	 * @reempurn
	 */
	public List<Goodstype> getList(Goodstype emp1,Goodstype emp2,Object param){
		return goodstypeDao.getList(emp1,emp2,param);
	}



	public void add(Goodstype emp) {
		goodstypeDao.add(emp);
	}

	/**
	 * 删除
	 */
	public void delete(String code1){
		goodstypeDao.delete(code1);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @reempurn
	 */
	public Goodstype get(String code1){
		return  (Goodstype)goodstypeDao.get(code1);
	}
	
	/**
	 * 更新
	 */
	public void update(Goodstype emp){
		goodstypeDao.update(emp);
	}

	

	public long getCount(Goodstype t1, Goodstype t2, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
