package cn.blog.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {

	
//openSession与getCurrentSession对比

		 public static void main(String[] args) {  
			  
		        Configuration configuration = new Configuration().configure();  
		        SessionFactory sf = configuration.buildSessionFactory();  
		          
		        Session sessionOpen1 = sf.openSession();  
		        Session sessionOpen2 = sf.openSession();  
		          
		        Session sessionThread1 = sf.getCurrentSession();  
		        Session sessionThread2 = sf.getCurrentSession();  
		          
		        System.out.println(sessionOpen1.hashCode() + "<-------->" + sessionOpen2.hashCode());  //每次创建都是新的session对象
		        System.out.println(sessionThread1.hashCode() + "<-------->" + sessionThread2.hashCode());  //每次获得的是当前session
	
}
		 
}
