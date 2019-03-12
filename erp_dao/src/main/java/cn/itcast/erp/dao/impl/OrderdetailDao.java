package cn.itcast.erp.dao.impl;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.dao.IOrderdetailDao;
import cn.itcast.erp.entity.Orderdetail;
import cn.itcast.erp.entity.Orders;
/**
 * 订单明细数据访问类
 * @author Administrator
 *
 */
public class OrderdetailDao extends HibernateDaoSupport implements IOrderdetailDao {

	public List<Orderdetail> getList() {
		 DetachedCriteria dc = getDetachedCriteria();
		 return (List<Orderdetail>) this.getHibernateTemplate().findByCriteria(dc);
	}

	/**
	 * 条件查询
	 */
	public List<Orderdetail> getList(Orderdetail t1,Orderdetail t2,Object param) {
		if(t1!=null) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		 return (List<Orderdetail>) this.getHibernateTemplate().findByCriteria(dc);
		}else {
			DetachedCriteria dc = getDetachedCriteria();
			 return (List<Orderdetail>) this.getHibernateTemplate().findByCriteria(dc);
		}
			
	}

	/**
	 * 记录条件查询的总记录数
	 * @param t1
	 * @return
	 */
	public long getCount(Orderdetail t1,Orderdetail t2,Object param) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}
	
	/**
	 * 新增
	 * @param t
	 */
	public void add(Orderdetail t){
		this.getHibernateTemplate().save(t);
	}
	
	/**
	 * 删除
	 */
	public void delete(String code1){
		//让对象进入持久化状态
		Orderdetail t = this.getHibernateTemplate().get(Orderdetail.class, code1);
		//删除持久化状态
		this.getHibernateTemplate().delete(t);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public Orderdetail get(String code1){
		return getHibernateTemplate().get(Orderdetail.class,code1);
	}
	
	/**
	 * 更新
	 */
	public void update(Orderdetail t){
		this.getHibernateTemplate().update(t);
	}

	
	public DetachedCriteria getDetachedCriteria(){
		DetachedCriteria dc=DetachedCriteria.forClass(Orderdetail.class);
		return dc;
	}


	/**
	 * 构建查询条件
	 * @param dep1
	 * @param dep2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Orderdetail orderdetail1,Orderdetail orderdetail2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Orderdetail.class);
		if(orderdetail1!=null){
			if(orderdetail1.getGoodsname()!=null &&  orderdetail1.getGoodsname().trim().length()>0)
			{
				dc.add(Restrictions.like("goodsname", orderdetail1.getGoodsname(), MatchMode.ANYWHERE));			
			}
			if(orderdetail1.getState()!=null &&  orderdetail1.getState().trim().length()>0)
			{
				dc.add(Restrictions.like("state", orderdetail1.getState(), MatchMode.ANYWHERE));			
			}
			if(orderdetail1.getOrderscode()!=null &&  orderdetail1.getOrderscode().trim().length()>0)
			{
				
				dc.add(Restrictions.eq("orderscode", orderdetail1.getOrderscode()));		
				//dc.add(Restrictions.like("orderscode", orderdetail1.getOrderscode(), MatchMode.ANYWHERE));			
			}
			if(orderdetail1.getOrders()!=null&&orderdetail1.getOrders().getUuid()!=null){
				dc.add(Restrictions.eq("orders", orderdetail1.getOrders()));
			}
		
		}		
		return dc;
	}

	public List<Orders> getListBySuppliercoeAndStarttimeAndEndtime(String suppliercode, Date starttime, Date endtime) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

