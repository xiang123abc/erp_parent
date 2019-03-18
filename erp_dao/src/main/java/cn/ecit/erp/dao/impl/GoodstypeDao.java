package cn.ecit.erp.dao.impl;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.ecit.erp.dao.IGoodstypeDao;
import cn.ecit.erp.entity.Emp;
import cn.ecit.erp.entity.Goodstype;
import cn.ecit.erp.entity.Orders;
/**
 * 商品分类数据访问类
 * @author Administrator
 *
 */
public class GoodstypeDao extends HibernateDaoSupport implements IGoodstypeDao {

	
	public List<Goodstype> getList() {
		
		 DetachedCriteria dc = getDetachedCriteria();
		 return (List<Goodstype>) this.getHibernateTemplate().findByCriteria(dc);
		}
	/**
	 * 条件查询
	 */
	public List<Goodstype> getList(Goodstype t1,Goodstype t2,Object param) {
		if(t1!=null) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		 return (List<Goodstype>) this.getHibernateTemplate().findByCriteria(dc);
		}else {
			DetachedCriteria dc = getDetachedCriteria();
			 return (List<Goodstype>) this.getHibernateTemplate().findByCriteria(dc);
		}
			
	}

	/**
	 * 记录条件查询的总记录数
	 * @param t1
	 * @return
	 */
	public long getCount(Goodstype t1,Goodstype t2,Object param) {
		DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}
	
	/**
	 * 新增
	 * @param t
	 */
	public void add(Goodstype t){
		this.getHibernateTemplate().save(t);
	}
	
	/**
	 * 删除
	 */
	public void delete(String code1){
		//让对象进入持久化状态
		Goodstype t = this.getHibernateTemplate().get(Goodstype.class, code1);
		//删除持久化状态
		this.getHibernateTemplate().delete(t);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public Goodstype get(String code1){
		return getHibernateTemplate().get(Goodstype.class, code1);
	}
	
	/**
	 * 更新
	 */
	public void update(Goodstype t){
		this.getHibernateTemplate().update(t);
	}

	
	
	
	public DetachedCriteria getDetachedCriteria(){
		DetachedCriteria dc=DetachedCriteria.forClass(Goodstype.class);
		return dc;
	}
	
	/**
	 * 构建查询条件
	 * @param dep1
	 * @param dep2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Goodstype goodstypetype1,Goodstype goodstypetype2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Goodstype.class);
		if(goodstypetype1!=null){
			if(goodstypetype1.getName()!=null &&  goodstypetype1.getName().trim().length()>0)
			{
				dc.add(Restrictions.like("name", goodstypetype1.getName(), MatchMode.ANYWHERE));			
			}
		
		}		
		return dc;
	}
	public List<Orders> getListBySuppliercoeAndStarttimeAndEndtime(String suppliercode, Date starttime, Date endtime) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}

