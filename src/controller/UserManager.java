package controller;

import model.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserManager {
	
	private static SessionFactory sessionFactory;
	
	public UserManager(){
		setSessionFactory(new Configuration().configure().buildSessionFactory());
	}
	
	
	public boolean addUser(String username, String password, String role){
		Session session=sessionFactory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
	      try{
	         tx = session.beginTransaction();
	         User newUser=new User(username, password, role);
	         employeeID = (Integer) session.save(newUser); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         return false;
	      }finally {
	         session.close(); 
	      }
	      return true;
	}
	

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		UserManager.sessionFactory = sessionFactory;
	}
}
