package org.nui.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.nui.test.Address;
import org.nui.test.UserDetails;

public class HibernateTest {
	public static SessionFactory sessionFactory = null;
	public static void main(String[] args) {
		System.out.print("start process demo.....");
		sampleInsert();
		sampleGet();
	}
	
	public static Session openSession() {
		if(sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory.openSession();
	}
	
	public static void sampleInsert() {
		UserDetails user = new UserDetails();
		user.setUserId(1);
		user.setUserName("this is Username");
		user.setDescription("description");
		user.setJoinedDate(new Date());
		
		Address addr = new Address();
		addr.setCity("bkk");
		addr.setDescription("test address description");
		user.setAddress(addr);

		Session session = HibernateTest.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	
	public static void sampleGet() {
		Session session = HibernateTest.openSession();
		UserDetails user = session.get(UserDetails.class, 1);
		System.out.print("User name from id 1: " + user.getUserName());
	}
}
