package cn.ecut.test;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class TestDemo extends HibernateDaoSupport{

	/*@Test
	public void test01() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		// 分页查询
		Criteria criteria = session.createCriteria(Orders.class);
		criteria.setFirstResult(0);
		criteria.setMaxResults(10);
		List<Orders> list = criteria.list();
		
		for (Orders linkMan : list) {
			System.out.println(linkMan);
		}
		tx.commit();

        
	}*/
}
