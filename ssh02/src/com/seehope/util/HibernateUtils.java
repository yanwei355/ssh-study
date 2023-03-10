/**
 * 
 */
package com.seehope.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author kkk
 *
 */
public class HibernateUtils {
	//声明一个私有的静态final类型的Configuration对象
	private static final Configuration config;
	//声明一个私有的静态final类型的SessionFactory对象
	private static final SessionFactory factory;
	//通过静态代码块初始化SessionFactory
	static {
		config = new Configuration().configure();
		factory = config.buildSessionFactory();
	}
	//提供一个公有的静态方法供外部获取，并返回一个Session对象
	public static Session getSession() {
		return factory.openSession();
	}

}
