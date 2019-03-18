package cn.ecit.erp.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.ecit.erp.dao.IEmpDao;
import cn.ecit.erp.entity.Emp;
import cn.ecit.erp.entity.Orders;

public class EmpDao  extends HibernateDaoSupport implements IEmpDao{

	public List<Emp> getList() {
		
		 DetachedCriteria dc = getDetachedCriteria();
		 return (List<Emp>) this.getHibernateTemplate().findByCriteria(dc);
		}
		/**
		 * 查询所有的部门信息
		 *//*
		public List<T> getList() {
			return (List<T>) this.getHibernateTemplate().find("from T");
		}*/
	
	/**
	 * 用户登陆
	 * @param username
	 * @param pwd
	 * @return
	 */
	public Emp findByUsernameAndPwd(String username, String pwd){
		String hql = "from Emp where username=? and pwd=?";		
		List<Emp> list = (List<Emp>) this.getHibernateTemplate().find(hql, username, pwd);
		//能够匹配上，则返回第一个元素
		if(list.size() > 0){
			return list.get(0);
		}
		//如果登陆名或密码不正确
		return null;
	}


		/**
		 * 条件查询
		 */
		public List<Emp> getList(Emp t1,Emp t2,Object param) {
			if(t1!=null) {
			DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
			 return (List<Emp>) this.getHibernateTemplate().findByCriteria(dc);
			}else {
				DetachedCriteria dc = getDetachedCriteria();
				List list = (List<Emp>) this.getHibernateTemplate().findByCriteria(dc);
				 return list;
			}
				
		}

		/**
		 * 记录条件查询的总记录数
		 * @param t1
		 * @return
		 */
		public long getCount(Emp t1,Emp t2,Object param) {
			DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
			dc.setProjection(Projections.rowCount());
			List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(dc);
			return list.get(0);
		}
		
		/**
		 * 新增
		 * @param t
		 */
		public void add(Emp t){
			this.getHibernateTemplate().save(t);
		}
		
		/**
		 * 删除
		 */
		public void delete(String code1){
			//让对象进入持久化状态
			Emp t = this.getHibernateTemplate().get(Emp.class, code1);
			//删除持久化状态
			this.getHibernateTemplate().delete(t);
		}
		
		/**
		 * 通过编号查询对象
		 * @param uuid
		 * @return
		 */
		public Emp get(String code1){
			return getHibernateTemplate().get(Emp.class, code1);
		}
		
		/**
		 * 更新
		 */
		public void update(Emp t){
			this.getHibernateTemplate().update(t);
		}
	
	
	
	public DetachedCriteria getDetachedCriteria(){
		DetachedCriteria dc=DetachedCriteria.forClass(Emp.class);
		return dc;
	}
	
	
	public DetachedCriteria getDetachedCriteria(Emp emp1,Emp emp2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Emp.class);
		if(emp1!=null){
			if(emp1.getUsername()!=null &&  emp1.getUsername().trim().length()>0)
			{
				dc.add(Restrictions.like("username", emp1.getUsername(), MatchMode.ANYWHERE));			
			}
			if(emp1.getPwd()!=null &&  emp1.getPwd().trim().length()>0)
			{
				dc.add(Restrictions.like("pwd", emp1.getPwd(), MatchMode.ANYWHERE));			
			}
			if(emp1.getName()!=null &&  emp1.getName().trim().length()>0)
			{
				dc.add(Restrictions.like("name", emp1.getName(), MatchMode.ANYWHERE));			
			}
			
			if(emp1.getTele()!=null &&  emp1.getTele().trim().length()>0)
			{
				dc.add(Restrictions.like("tele", emp1.getTele(), MatchMode.ANYWHERE));			
			}
			
			
			if(emp1.getBirthday()!=null){
				dc.add(Restrictions.ge("birthday", emp1.getBirthday())) ;  //ge = great  equal
			}
			
		}		
		if(emp2!=null){
			if(emp2.getBirthday()!=null){
				dc.add(Restrictions.le("birthday", emp2.getBirthday())) ;  //le = less  equal
			}
		}
		return dc;
	}

	public List<Orders> getListBySuppliercoeAndStarttimeAndEndtime(String suppliercode, Date starttime, Date endtime) {
		// TODO Auto-generated method stub
		return null;
	}
}
