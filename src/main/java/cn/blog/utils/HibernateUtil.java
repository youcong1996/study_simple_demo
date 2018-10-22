package cn.blog.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil extends Object{
	private static SessionFactory sessionFactory;
	static
	{
		try{
			Configuration configuration=new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
	  	}catch (Throwable ex){
	            throw new ExceptionInInitializerError(ex);
	    }
	}
      private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}	
	public static Session getSession() throws HibernateException
	{
		Session session = (Session) threadLocal.get();
		if (session == null){
			session = sessionFactory.openSession();
			threadLocal.set(session);
		}
	        return session;
	}
	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
        if (session != null)
        	session.close();
        threadLocal.set(null);
    }
	
	public static void shutdown(){
		getSessionFactory().close();
	}
	
}
