package cn.itcast.erp.dao.impl;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.dao.IOrdersDao;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Orders;
/**
 * 订单数据访问类
 * @author Administrator
 *
 */
public class OrdersDao extends HibernateDaoSupport implements IOrdersDao {
	public List<Orders> getListByPage(Orders t1,Orders t2,Object param,int firstResult, int maxResults) {
		/*DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		List<Orders> list = (List<Orders>) this.getHibernateTemplate().findByCriteria(dc,firstResult, maxResults);
		
		if(list!=null&& list.size()>0) {
			for(Orders orders : list) {
				System.out.println(orders.getOrdercode());
			}
		}*/
	 // 分页查询
		Session session  = this.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
 		Criteria criteria = session.createCriteria(Orders.class);
		criteria.setFirstResult(0);
		criteria.setMaxResults(10);
		List<Orders> list = criteria.list();

		for(Orders orders:list) {
			orders.getOrdercode();
		}
	return null;
	}
	
	public List<Orders> getList() {
		 DetachedCriteria dc = getDetachedCriteria();
		 return (List<Orders>) this.getHibernateTemplate().findByCriteria(dc);
	}

	/**
	 * 条件查询
	 */
	public List<Orders> getList(Orders t1,Orders t2,Object param) {
		/*if(t1!=null) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		 return (List<Orders>) this.getHibernateTemplate().findByCriteria(dc);
		}else {
			DetachedCriteria dc = getDetachedCriteria();
			 return (List<Orders>) this.getHibernateTemplate().findByCriteria(dc);
		}
*/	
		 List<Orders> list = null;
		if(t1!=null) {
			DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
			 list =(List<Orders>) this.getHibernateTemplate().findByCriteria(dc);
			}else {
				DetachedCriteria dc = getDetachedCriteria();
				 list = (List<Orders>) this.getHibernateTemplate().findByCriteria(dc);
			}
		return list;
		
	}

	/**
	 * 记录条件查询的总记录数
	 * @param t1
	 * @return
	 */
	public long getCount(Orders t1,Orders t2,Object param) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}
	
	/**
	 * 新增
	 * @param t
	 */
	public void add(Orders t){
		this.getHibernateTemplate().save(t);
	}
	
	/**
	 * 删除
	 */
	public void delete(String code1){
		//让对象进入持久化状态
		Orders t = this.getHibernateTemplate().get(Orders.class,code1);
		//删除持久化状态
		this.getHibernateTemplate().delete(t);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public Orders get(String code1){
		return getHibernateTemplate().get(Orders.class, code1);
	}
	
	/**
	 * 更新
	 */
	public void update(Orders t){
		this.getHibernateTemplate().update(t);
	}

	
	public DetachedCriteria getDetachedCriteria(){
		DetachedCriteria dc=DetachedCriteria.forClass(Orders.class);
		return dc;
	}
	
	/**
	 * 构建查询条件
	 * @param dep1
	 * @param dep2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Orders orders1,Orders orders2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Orders.class);
		if(orders1!=null){
			if(orders1.getState()!=null &&  orders1.getState().trim().length()>0)
			{
				dc.add(Restrictions.like("state", orders1.getState(), MatchMode.ANYWHERE));			
			}	
			if(orders1.getOrdercode()!=null){
				dc.add(Restrictions.like("orderscode", orders1.getOrdercode(), MatchMode.ANYWHERE));
			}
			if(orders1.getSuppliercode()!=null){
				dc.add(Restrictions.like("suppliercode", orders1.getSuppliercode(), MatchMode.ANYWHERE));
			}
			if(orders1.getStarttime()!=null){
				dc.add(Restrictions.ge("starttime", orders1.getStarttime())) ;  //ge = great  equal
			}
		
		}
		if(orders2!=null){
			if(orders2.getStarttime()!=null){
				dc.add(Restrictions.le("starttime", orders2.getStarttime())) ;  //le = less  equal
			}
		}
		return dc;
	}
	
	public List<Orders> getListBySuppliercoeAndStarttimeAndEndtime(String suppliercode,Date starttime,Date endtime){
		DetachedCriteria dc=DetachedCriteria.forClass(Orders.class);
		Orders orders = new Orders();
		orders.setStarttime(starttime);
		orders.setSuppliercode(suppliercode);
		Orders ordersOther = new Orders();
		ordersOther.setEndtime(endtime);
		if(orders!=null) {
			if(suppliercode!=null &&  suppliercode.trim().length()>0)
			{
				dc.add(Restrictions.like("suppliercode", orders.getSuppliercode(), MatchMode.ANYWHERE));			
			}
			if(orders.getStarttime()!=null){
				dc.add(Restrictions.ge("starttime", orders.getStarttime())) ;  //ge = great  equal
			}
		}
		if(ordersOther!=null) {
			if(ordersOther.getEndtime()!=null) {
			dc.add(Restrictions.le("endtime", ordersOther.getEndtime())) ;  //ge = great  equal	
			}
		}
			
		return (List<Orders>) this.getHibernateTemplate().findByCriteria(dc);
	}
	
	
}

