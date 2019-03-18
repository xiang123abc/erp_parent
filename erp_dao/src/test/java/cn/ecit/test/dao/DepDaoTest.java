package cn.ecit.test.dao;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ecit.erp.dao.IDepDao;

public class DepDaoTest {

	@Test
	public void Deptest() {
	ApplicationContext ac =	new ClassPathXmlApplicationContext("classpath*:applicationContext_*.xml");
	//ac.getBean("sessionFactory");
	IDepDao depDao = (IDepDao) ac.getBean("depDao");
	
	
	//List list = depDao.getList(null, null, null, 0, 1);
	List list =depDao.getList();
	System.out.println(list.toArray().toString());  
	}
}
