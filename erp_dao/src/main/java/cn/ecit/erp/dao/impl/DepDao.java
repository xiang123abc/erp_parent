package cn.ecit.erp.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.ecit.erp.dao.IDepDao;
import cn.ecit.erp.entity.Dep;
import cn.ecit.erp.entity.Orders;

/**
 * 部门的数据访问
 * @author Administrator
 *
 */
public class DepDao extends HibernateDaoSupport  implements IDepDao {
	
	public List<Dep> getList() {
		
		 DetachedCriteria dc = getDetachedCriteria();
		 return (List<Dep>) this.getHibernateTemplate().findByCriteria(dc);
		}
		/**
		 * 查询所有的部门信息
		 *//*
		public List<T> getList() {
			return (List<T>) this.getHibernateTemplate().find("from T");
		}*/

		/**
		 * 条件查询
		 */
		public List<Dep> getList(Dep t1,Dep t2,Object param) {
			if(t1!=null) {
			DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
			 return (List<Dep>) this.getHibernateTemplate().findByCriteria(dc);
			}else {
				DetachedCriteria dc = getDetachedCriteria();
				 return (List<Dep>) this.getHibernateTemplate().findByCriteria(dc);
			}
				
		}

		/**
		 * 记录条件查询的总记录数
		 * @param t1
		 * @return
		 */
		public long getCount(Dep t1,Dep t2,Object param) {
			DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
			dc.setProjection(Projections.rowCount());
			List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(dc);
			return list.get(0);
		}
		
		/**
		 * 新增
		 * @param t
		 */
		public void add(Dep t){
			this.getHibernateTemplate().save(t);
		}
		
		/**
		 * 删除
		 */
		public void delete(String code1){
			//让对象进入持久化状态
			Dep t = this.getHibernateTemplate().get(Dep.class, code1);
			//删除持久化状态
			this.getHibernateTemplate().delete(t);
		}
		
		/**
		 * 通过编号查询对象
		 * @param uuid
		 * @return
		 */
		public Dep get(String code1){
			return getHibernateTemplate().get(Dep.class, code1);
		}
		
		/**
		 * 更新
		 */
		public void update(Dep t){
			this.getHibernateTemplate().update(t);
		}
	
	
	public DetachedCriteria getDetachedCriteria(){
		DetachedCriteria dc=DetachedCriteria.forClass(Dep.class);
		return dc;
	}
	
	
	public DetachedCriteria getDetachedCriteria(Dep dep1,Dep dep2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Dep.class);
		if(dep1!=null){
			if(dep1.getName()!=null &&  dep1.getName().trim().length()>0)
			{
				dc.add(Restrictions.like("name", dep1.getName(), MatchMode.ANYWHERE));			
			}
			if(dep1.getTele()!=null &&  dep1.getTele().trim().length()>0)
			{
				dc.add(Restrictions.like("tele", dep1.getTele(), MatchMode.ANYWHERE));			
			}
		
		}		
		return dc;
	}

	public List<Orders> getListBySuppliercoeAndStarttimeAndEndtime(String suppliercode, Date starttime, Date endtime) {
		// TODO Auto-generated method stub
		return null;
	}

}
