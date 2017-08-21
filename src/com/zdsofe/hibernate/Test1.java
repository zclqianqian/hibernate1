package com.zdsofe.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Test1 {

	public static void main(String[] args) {
		//以编程的方式获得配置文件
		Configuration cfg=new Configuration();
		cfg.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver" );
		cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_hibernate");
		cfg.setProperty("hibernate.connection.username", "root");
		cfg.setProperty("hibernate.connection.password", "775297");
		cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		cfg.setProperty("hibernate.current_session_context_class", "thread");
		cfg.addResource("com/zdsofe/hibernate/Student.hbm.xml");
		//获得注册器对象
		ServiceRegistry  serviceRegistry=new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		//创建session工厂
	    SessionFactory  sessionFactory=cfg.buildSessionFactory(serviceRegistry);
	    //创建session
	    Session session=sessionFactory.getCurrentSession();
	    //开启事务
	    session.beginTransaction();
	    //提交事务
	    session.getTransaction().commit();
		
		
		
	}
}
