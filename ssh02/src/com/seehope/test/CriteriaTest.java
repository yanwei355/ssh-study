package com.seehope.test;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.seehope.entity.Student;
import com.seehope.util.HibernateUtils;

public class CriteriaTest {
	//查询条件姓名为"李白"的学生
	//@Test
	public void criteriaTest1() {
		//1.获得Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//2.通过Session获得Criteria对象
		Criteria criteria = session.createCriteria(Student.class);
		//3.使用Restrictions的eq方法设定查询条件为studentName="李白"
		Criterion criterion = Restrictions.eq("studentName", "李白");			
		//4.向Criteria对象中添加name="李白"的查询条件
		criteria.add(criterion);
		//5.执行Criteria的list()获得结果
		List<Student> list = criteria.list();
		for (Student student : list) {
			System.out.println(student);
		}
		t.commit();
		session.close();
	}
	
	//查询id=2或者studentName="李白"的学生
	//@Test
	public void criteriaTest2() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//2.创建criteria对象
		Criteria criteria= session.createCriteria(Student.class);
		//3.设定查询条件
		Criterion criterion = Restrictions.or(Restrictions.eq("id", 2),Restrictions.eq("studentName", "李白"));
		//4.添加查询条件
		criteria.add(criterion);
		//5.执行查询返回查询结果
		List<Student> list = criteria.list();
		for (Student student : list) {
			System.out.println(student);
		}
		t.commit();
		session.close();		
	}
	
	//Criteria分页查询,每页三条,查询第一页
	@Test
	public void criteriaTest3() {		
		//得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//创建criteria对象
		Criteria criteria= session.createCriteria(Student.class);
		//从第一个对象开始查询(默认第一个对象序号为0)
		criteria.setFirstResult(0);
		//每次从查询结果中返回3个对象
		criteria.setMaxResults(3);
		//执行查询返回查询结果
		List<Student> list = criteria.list();
		for (Student student : list) {
			System.out.println(student);
		}
		t.commit();
		session.close();
		
	}
}
