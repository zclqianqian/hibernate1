package com.zdsofe.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Test {

	public static void main(String[] args) {
      //通过配置文件对象获得配置文件
	  Configuration cfg = new Configuration().configure();
	  //获取服务注册器对象
	  StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();
	  //获得注册器对象
	  ServiceRegistry serviceRegistry = ssrb.applySettings(cfg.getProperties()).build();
	  //创建SessionFactory
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	  //通过session工厂创建session
	  Session session = sessionFactory.getCurrentSession();
	  //开启事务
	  session.beginTransaction();
	  //进行数据库操作	  
	  Student stu = new Student(); //(瞬时状态,随时可能被销毁，被垃圾回收器回收)
	  stu.setName("饶鹏");
	  stu.setSex("女");
	  stu.setBirthday(new Date());
	 
	  //保存数据
	  Integer i = (Integer)session.save(stu);//当stu对象和session关联，并且有个唯一的标识(主键)，那么此时的状态称为持久化
	  System.out.println(i);
	  stu.setSex("男");//处于持久化状态时会直接修改，不用调用save、update等
	  //查询
	  /*  Student stu2 = (Student) session.get(Student.class, 4);*/
	  //删除
	  /* session.delete(stu2);*/   //此时stu属于删除态，不建议使用
	  //提交事务
      session.getTransaction().commit();//此时的状态称为脱管状态(此时的session已经销毁)
      
      //重新获取session
      session=sessionFactory.getCurrentSession();
      //开启事务
	  session.beginTransaction();
      session.update(stu);//在此与session关联，那么此时的状态称为持久化
      //提交事务
      session.getTransaction().commit();
     
	 	
	}

}
