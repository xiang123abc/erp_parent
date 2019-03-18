package cn.ecit.erp.dao.impl;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.ecit.erp.dao.IGoodsDao;
import cn.ecit.erp.entity.Goods;
import cn.ecit.erp.entity.Orders;
/**
 * 商品数据访问类
 * @author Administrator
 *
 */
public class GoodsDao extends HibernateDaoSupport implements IGoodsDao {

	public List<Goods> getList() {
		 DetachedCriteria dc = getDetachedCriteria();
		 return (List<Goods>) this.getHibernateTemplate().findByCriteria(dc);
	}

	/**
	 * 条件查询
	 */
	public List<Goods> getList(Goods t1,Goods t2,Object param) {
		if(t1!=null) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		 return (List<Goods>) this.getHibernateTemplate().findByCriteria(dc);
		}else {
			DetachedCriteria dc = getDetachedCriteria();
			 return (List<Goods>) this.getHibernateTemplate().findByCriteria(dc);
		}
			
	}

	/**
	 * 记录条件查询的总记录数
	 * @param t1
	 * @return
	 */
	public long getCount(Goods t1,Goods t2,Object param) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}
	
	/**
	 * 新增
	 * @param t
	 */
	public void add(Goods t){
		this.getHibernateTemplate().save(t);
	}
	
	/**
	 * 删除
	 */
	public void delete(String code1){
		//让对象进入持久化状态
		Goods t = this.getHibernateTemplate().get(Goods.class, code1);
		//删除持久化状态
		this.getHibernateTemplate().delete(t);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public Goods get(String code1){
		return getHibernateTemplate().get(Goods.class, code1);
	}
	
	/**
	 * 更新
	 */
	public void update(Goods t){
		this.getHibernateTemplate().update(t);
	}

	
	public DetachedCriteria getDetachedCriteria(){
		DetachedCriteria dc=DetachedCriteria.forClass(Goods.class);
		return dc;
	}
	
	/**
	 * 构建查询条件
	 * @param dep1
	 * @param dep2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Goods goods1,Goods goods2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Goods.class);
		if(goods1!=null){
			if(goods1.getName()!=null &&  goods1.getName().trim().length()>0)
			{
				dc.add(Restrictions.like("name", goods1.getName(), MatchMode.ANYWHERE));			
			}
			if(goods1.getOrigin()!=null &&  goods1.getOrigin().trim().length()>0)
			{
				dc.add(Restrictions.like("origin", goods1.getOrigin(), MatchMode.ANYWHERE));			
			}
			if(goods1.getProducer()!=null &&  goods1.getProducer().trim().length()>0)
			{
				dc.add(Restrictions.like("producer", goods1.getProducer(), MatchMode.ANYWHERE));			
			}
			if(goods1.getUnit()!=null &&  goods1.getUnit().trim().length()>0)
			{
				dc.add(Restrictions.like("unit", goods1.getUnit(), MatchMode.ANYWHERE));			
			}
			/*if(goods1.getGoodstype()!=null&&goods1.getGoodstype().getUuid()!=null){
				dc.add(Restrictions.eq("goodstype", goods1.getGoodstype()));
				
			}*/
		
		}		
		return dc;
	}

	public List<Orders> getListBySuppliercoeAndStarttimeAndEndtime(String suppliercode, Date starttime, Date endtime) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}

