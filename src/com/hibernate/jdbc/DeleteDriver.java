package com.hibernate.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDriver {
	
	public static void main(String args[]){
		
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		Session session = factory.getCurrentSession();
				try{
					int studentId = 1;
					session.beginTransaction();
					Student student = session.get(Student.class,studentId);
					System.out.println(student);
					session.delete(student);
					
					session.getTransaction().commit();
					System.out.println("Done");
					
					Session session2 = factory.getCurrentSession();
					session2.beginTransaction();
					session2.createQuery("Delete Student where email='mansour.alzara@yahoo.com' ")
					.executeUpdate();
					
					session2.getTransaction().commit();
					
				}catch (Exception ex){
					ex.printStackTrace();
				}finally{
					factory.close();
				}
			}
	}

