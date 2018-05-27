package com.testhibernate5.main;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.testhibernate5.config.HibernateAnnotationUtil;
import com.testhibernate5.model.Cart;
import com.testhibernate5.model.Employee;
import com.testhibernate5.model.Items;

public class HibernateOneToManyAnnotationMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			Cart cart = new Cart();
			Items item1 = new Items(cart);
			Items item2 = new Items(cart);
			
			Set<Items> itemSet = new HashSet<Items>();
			itemSet.add(item1);
			itemSet.add(item2);
			
			cart.setItems(itemSet);
			
			SessionFactory sessionFactory = null;
			Session session = null;
			Transaction tx = null;
			
			try {
				// Get Session
				sessionFactory = HibernateAnnotationUtil.getSessionFactory();
				session = sessionFactory.getCurrentSession();
				System.out.println("Session created!");
				
				// Start transaction
				tx = session.beginTransaction();
				
				// Save the mode object
				session.save(cart);
				session.save(item1);
				session.save(item2);
								
				// Commit transaction
				tx.commit();
				
				System.out.println("Cart ID " + cart.getId());
				System.out.println("item1 ID " + item1.getId() + " Foreign Key Cart ID " + item1.getCart().getId());
				System.out.println("item1 ID " + item2.getId() + " Foreign Key Cart ID " + item2.getCart().getId());
			
				// Start transaction
				session = sessionFactory.getCurrentSession();
				tx = session.beginTransaction();
				
				Employee manager1 = new Employee("Chuck", "Norris");
				
				Employee employee1 = new Employee("Sergey", "Brin");
				Employee employee2 = new Employee("Larry", "Page");
				
				employee1.setManager(manager1);
				employee2.setManager(manager1);
				
				session.save(employee1);
				session.save(employee2);
				
				// Commit transaction
				tx.commit();
			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage());
				e.printStackTrace();
				
			} finally {
				if (!sessionFactory.isClosed()) {
					System.out.println("Closing SessionFactory");
					sessionFactory.close();
				}
				
			}
	}

}
