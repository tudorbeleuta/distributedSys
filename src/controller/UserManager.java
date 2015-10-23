package controller;

import java.util.List;

import model.User;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserManager {
	
	private static SessionFactory sessionFactory;
	
	@SuppressWarnings("deprecation")
	public UserManager(){
		setSessionFactory(new Configuration().configure().buildSessionFactory());
	}
	
	
	public boolean addUser(String username, String password, String role){
		Session session=sessionFactory.openSession();
		Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         User newUser=new User(username, password, role);
	         session.save(newUser); 
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
	
	
	public User getUser(String username, String password){
		Session session=sessionFactory.openSession();
		Transaction tx = null;
		User newUser=null;
	      try{
	         tx = session.beginTransaction();
	         newUser= queryUser(username,password,session);
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return newUser;
	}
	
	private User checkUser( List<User> list){
		 if(list.size()>0){
        		 return list.get(0);
        	 }
		 return null;
	}
	
	private User queryUser(String username, String password, Session session){
		Query query = session.createQuery("FROM model.User WHERE username = :usr AND password = :psw");
        query.setParameter("usr", username);
        query.setParameter("psw", password);
        return checkUser(query.list());
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		UserManager.sessionFactory = sessionFactory;
	}
}
