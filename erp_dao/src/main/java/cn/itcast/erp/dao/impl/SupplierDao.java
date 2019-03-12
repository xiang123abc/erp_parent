package cn.itcast.erp.dao.impl;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.dao.ISupplierDao;
import cn.itcast.erp.entity.Orders;
import cn.itcast.erp.entity.Supplier;
/**
 * 供应商数据访问类
 * @author Administrator
 *
 */
public class SupplierDao  extends HibernateDaoSupport implements ISupplierDao {

	public List<Supplier> getList() {
		 DetachedCriteria dc = getDetachedCriteria();
		 return (List<Supplier>) this.getHibernateTemplate().findByCriteria(dc);
	}

	/**
	 * 条件查询
	 */
	public List<Supplier> getList(Supplier t1,Supplier t2,Object param) {
		if(t1!=null) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		 return (List<Supplier>) this.getHibernateTemplate().findByCriteria(dc);
		}else {
			DetachedCriteria dc = getDetachedCriteria();
			 return (List<Supplier>) this.getHibernateTemplate().findByCriteria(dc);
		}
			
	}

	/**
	 * 记录条件查询的总记录数
	 * @param t1
	 * @return
	 */
	public long getCount(Supplier t1,Supplier t2,Object param) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}
	
	/**
	 * 新增
	 * @param t
	 */
	public void add(Supplier t){
		this.getHibernateTemplate().save(t);
	}
	
	/**
	 * 删除
	 */
	public void delete(String code1){
		//让对象进入持久化状态
		Supplier t = this.getHibernateTemplate().get(Supplier.class,  code1);
		//删除持久化状态
		this.getHibernateTemplate().delete(t);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public Supplier get(String suppliercode){
		return getHibernateTemplate().get(Supplier.class, suppliercode);
	}
	
	/**
	 * 更新
	 */
	public void update(Supplier t){
		this.getHibernateTemplate().update(t);
	}

	
	public DetachedCriteria getDetachedCriteria(){
		DetachedCriteria dc=DetachedCriteria.forClass(Supplier.class);
		return dc;
	}
	/**
	 * 构建查询条件
	 * @param dep1
	 * @param dep2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Supplier supplier1,Supplier supplier2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Supplier.class);
		if(supplier1!=null){
			if(supplier1.getName()!=null &&  supplier1.getName().trim().length()>0)
			{
				dc.add(Restrictions.like("name", supplier1.getName(), MatchMode.ANYWHERE));			
			}
			if(supplier1.getAddress()!=null &&  supplier1.getAddress().trim().length()>0)
			{
				dc.add(Restrictions.like("address", supplier1.getAddress(), MatchMode.ANYWHERE));			
			}
			if(supplier1.getContact()!=null &&  supplier1.getContact().trim().length()>0)
			{
				dc.add(Restrictions.like("contact", supplier1.getContact(), MatchMode.ANYWHERE));			
			}
			if(supplier1.getTele()!=null &&  supplier1.getTele().trim().length()>0)
			{
				dc.add(Restrictions.like("tele", supplier1.getTele(), MatchMode.ANYWHERE));			
			}
			if(supplier1.getEmail()!=null &&  supplier1.getEmail().trim().length()>0)
			{
				dc.add(Restrictions.like("email", supplier1.getEmail(), MatchMode.ANYWHERE));			
			}
			if(supplier1.getType()!=null &&  supplier1.getType().trim().length()>0)
			{
				dc.add(Restrictions.like("type", supplier1.getType(), MatchMode.ANYWHERE));			
			}
		
		}		
		return dc;
	}

	public List<Orders> getListBySuppliercoeAndStarttimeAndEndtime(String suppliercode, Date starttime, Date endtime) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

