package com.seehope.test;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


import com.seehope.entity.Student;
import com.seehope.util.HibernateUtils;
public class HQLTest {
	
	//@Test
	//查询所有学生,使用query对象的list()方法执行查询
	public void TestList() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//编写HQL
		String hql = "from Student";
		//创建Query对象
		Query query = session.createQuery(hql);
		//执行查询
		List<Student> list = query.list();
		for (Student student : list) {
			System.out.println(student);
		}
		t.commit();
		session.close();
	}
	
	
	//@Test
	//查询所有学生,使用query对象的iterate()方法执行查询
	public void TestIterate() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//编写HQL
		String hql = "from Student";
		//创建Query对象
		Query query = session.createQuery(hql);
		//执行查询
		Iterator<Student> students = query.iterate();
		Student student=null;
		while(students.hasNext()) {
			student=students.next();
			System.out.println(student);
		}
		t.commit();
		session.close();
	}
	
	//@Test
	//查询id=1的单个学生,使用query对象的uniqueResult()方法执行查询
	public void TestUniqueResult() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//编写HQL
		String hql = "from Student where id=1";
		//创建Query对象
		Query query = session.createQuery(hql);
		//执行查询
		Student student = (Student) query.uniqueResult();		
		System.out.println(student);		
		t.commit();
		session.close();
	}
	
	//查询年龄为18岁的男学生,使用?占位符
    //@Test
	public void findStudents1() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//编写带占位符?的HQL
		String hql = "from Student where age=? and gender=?";
		//创建Query对象
		Query query = session.createQuery(hql);
		//分别给各个?占位符赋值
		query.setInteger(0, 18);
		query.setString(1, "男");
		//执行查询
		List<Student> list = query.list();
		for (Student student : list) {
			System.out.println(student);
		}
		t.commit();
		session.close();
	}
    
  //查询年龄为18岁的男学生,使用命名参数
    //@Test
	public void findStudents2() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//编写带参数名称的HQL
		String hql = "from Student where age=:age and gender=:gender";
		//创建Query对象
		Query query = session.createQuery(hql);
		//分别给各个名称的参数赋值
		query.setInteger("age", 18);
		query.setString("gender", "男");
		//执行查询
		List<Student> list = query.list();
		for (Student student : list) {
			System.out.println(student);
		}
		t.commit();
		session.close();
	}
    
  //查询年龄为18岁的男学生,使用?占位符并使用setParameter简化了参数赋值
    //@Test
	public void findStudents3() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//编写带参数名称的HQL
		String hql = "from Student where age=? and gender=?";
		//创建Query对象
		Query query = session.createQuery(hql);
		//分别给各个名称的参数赋值
		query.setParameter(0, 18);
		query.setParameter(1, "男");
		//执行查询
		List<Student> list = query.list();
		for (Student student : list) {
			System.out.println(student);
		}
		t.commit();
		session.close();
	}
	
    
  //查询年龄为18岁的男学生,使用命名参数并使用setParameter简化了参数赋值
    //@Test
	public void findStudents4() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//编写带参数名称的HQL
		String hql = "from Student where age=:age and gender=:gender";
		//创建Query对象
		Query query = session.createQuery(hql);
		//分别给各个名称的参数赋值
		query.setParameter("age", 18);
		query.setParameter("gender", "男");
		//执行查询
		List<Student> list = query.list();
		for (Student student : list) {
			System.out.println(student);
		}
		t.commit();
		session.close();
	}
	
	
	//查询年龄为18岁的女学生,使用setProperties()方法获取对象的属性值给HQL中的命名参数赋值
    //@Test
	public void findStudents5() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//编写带参数名称的HQL
		String hql = "from Student where age=:age and gender=:gender";
		//创建Query对象
		Query query = session.createQuery(hql);
		//封装一个学生对象
		Student stu=new Student();
		stu.setAge(18);
		stu.setGender("女");
		//利用学生对象的属性给HQL参数赋值
		query.setProperties(stu);
		//执行查询
		List<Student> list = query.list();
		for (Student student : list) {
			System.out.println(student);
		}
		t.commit();
		session.close();
	}
	
	//动态查询(含模糊查询)
	//@Test
	public void findStudents6() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		String hql = "from Student where 1=1";
		Scanner input=new Scanner(System.in);
		Student stu=new Student();
		System.out.print("请输入学生id,也可输入0跳过:");
		int id=input.nextInt();
		if(id!=0) {
			stu.setId(id);
			hql=hql+" and id=:id";
		}
		System.out.print("请输入学生姓名,也可输入0跳过:");
		String name=input.next();
		if(!name.equals("0")) {
			stu.setStudentName("%"+name+"%");
			hql=hql+" and studentName like :studentName";
		}		
		System.out.print("请输入学生年龄,也可输入0跳过:");
		int age=input.nextInt();
		if(age!=0) {
			stu.setAge(age);
			hql=hql+" and age=:age";
		}		
		System.out.print("请输入学生性别,也可输入0跳过:");
		String gender=input.next();
		if(!gender.equals("0")) {
			stu.setGender(gender);
			hql=hql+" and gender=:gender";
		}
		//创建Query对象
		Query query = session.createQuery(hql);		
		//利用学生对象的属性给HQL参数赋值
		query.setProperties(stu);
		//执行查询
		List<Student> list = query.list();
		for (Student student : list) {
			System.out.println(student);
		}
		t.commit();
		session.close();
	}
    //投影查询1,查单个属性
	//@Test
	public void findStudents7() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//编写HQL
		String hql = "select studentName from Student";
		//创建Query对象
		Query query = session.createQuery(hql);
		//执行查询
		List<String> list = query.list();
		for (String studentname : list) {
			System.out.println(studentname);
		}
		t.commit();
		session.close();
	}
	
	//投影查询2，查多个属性
	//@Test
	public void findStudents8() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		//编写HQL
		String hql = "select studentName,age from Student";
		//创建Query对象
		Query query = session.createQuery(hql);
		//执行查询
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			System.out.println("学生姓名:"+obj[0]+",学生年龄:"+obj[1]);
		}
		t.commit();
		session.close();
	}
	
	//投影查询3，查多个属性,封将为对象
		//@Test
		public void findStudents9() {		
			//1.得到一个Session
			Session session = HibernateUtils.getSession();
			Transaction t = session.beginTransaction();
			//编写HQL
			String hql = "select new Student(studentName,age) from Student";
			//创建Query对象
			Query query = session.createQuery(hql);
			//执行查询
			List<Student> list = query.list();
			for (Student student : list) {
				System.out.println("学生姓名:"+student.getStudentName()+",学生年龄:"+student.getAge());
			}
			t.commit();
			session.close();
		}
		
		//分页查询
		//@Test
		public void findStudents10() {		
			//1.得到一个Session
			Session session = HibernateUtils.getSession();
			Transaction t = session.beginTransaction();
			Scanner input=new Scanner(System.in);
			System.out.print("一页显示几条pageSize:");
			int pageSize=input.nextInt();
			System.out.print("查询第几页pageNo:");
			int pageNo=input.nextInt();
			//编写HQL
			String hql = "from Student";
			//创建Query对象
			Query query = session.createQuery(hql);
			//执行查询
			List<Student> list = query.setFirstResult((pageNo-1)*pageSize)
					.setMaxResults(pageSize)
					.list();
			for (Student student : list) {
				System.out.println(student);
			}
			t.commit();
			session.close();
		}
	
		
		//聚合查询 查询学生的总人数
		//@Test
		public void findStudents11() {		
			//1.得到一个Session
			Session session = HibernateUtils.getSession();
			Transaction t = session.beginTransaction();			
			//编写HQL
			String hql = "select count(id) from Student";
			//创建Query对象
			Query query = session.createQuery(hql);
			//执行查询
			Long count = (Long) query.uniqueResult();			
			System.out.println("学生人数:"+count);			
			t.commit();
			session.close();
		}
	
	//分组查询 查询男女生的人数
	//@Test
	public void findStudents12() {		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();			
		//编写HQL
		String hql = "select gender,count(id) from Student group by gender";
		//创建Query对象
		Query query = session.createQuery(hql);
		//执行查询
		List<Object[]> list = query.list();			
		for (Object[] obj : list) {
			System.out.println("性别:"+obj[0]+",学生人数:"+obj[1]);
		}	
		t.commit();
		session.close();
	}
	
	//使用别名
	@Test
	public void aliasTest() {
		
		//1.得到一个Session
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();

		//编写HQL
		String hql = "from Student as s where s.studentName = '李白'";
		//创建Query对象
		Query query = session.createQuery(hql);
		//执行查询
		List<Student> list = query.list();
		for (Student student : list) {
			System.out.println(student);
		}
		t.commit();
		session.close();
		
	}
	
	
}
