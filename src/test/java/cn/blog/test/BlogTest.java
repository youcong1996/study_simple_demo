package cn.blog.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.blog.entity.User;
import cn.blog.utils.HibernateUtil;

public class BlogTest {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setUserId(2);
        user.setLoginCode("yc@163.com");
    	user.setUserName("聪哥哥");
    	user.setPassword("test123");
    	user.setIdentityCard("1234");
    	user.setCreateBy("系统");
    	user.setCreateTime("2018-10-21 10:00");
    	user.setUpdateBy("系统");
    	user.setUpdateTime("2018-10-21 10:00");
    	user.setSex(1);
    	user.setStatus(1);
        session.delete(user);
        tx.commit();
		
	/*	
	 * Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
        	   User user = (User) session.get(User.class, 1);
               System.out.println(user.getUserName());
               
               tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
     */
        
		
        
	/*	Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
        	User user = new User();
            user.setUserId(2);
            user.setLoginCode("yc@163.com");
        	user.setUserName("聪哥哥");
        	user.setPassword("test123");
        	user.setIdentityCard("1234");
        	user.setCreateBy("系统");
        	user.setCreateTime("2018-10-21 10:00");
        	user.setUpdateBy("系统");
        	user.setUpdateTime("2018-10-21 10:00");
        	user.setSex(1);
        	user.setStatus(1);
            
            session.saveOrUpdate(user);
            System.out.println("update succes");
            tx.commit();
            	
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			 System.out.println("update fail");
		}finally {
			HibernateUtil.closeSession();
		}
        
        */
        
		
/*		Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        
        String userName="游";
        Criteria c= session.createCriteria(User.class);
        c.add(Restrictions.like("userName", "%"+userName+"%"));
        
        List<User> user = c.list();
	      for (User user2 : user) {
			System.out.println(user2.getUserName());
		}
        tx.commit();
    */
		
		
		/*Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
			
        	User user = new User();
        	user.setLoginCode("yc@163.com");
        	user.setUserName("游先生");
        	user.setPassword("test123");
        	user.setIdentityCard("1234");
        	user.setCreateBy("系统");
        	user.setCreateTime("2018-10-21 10:00");
        	user.setUpdateBy("系统");
        	user.setUpdateTime("2018-10-21 10:00");
        	user.setSex(1);
        	user.setStatus(1);
        	session.save(user);
        	System.out.println("insert data success");
        	tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			System.out.println("insert data fail");
		}finally {
			
			HibernateUtil.closeSession();
		}*/
	}
}
