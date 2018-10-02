package com.hibernate.jdbc;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryDriver {

	public static void main(String args[]){
		
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		Session session = factory.getCurrentSession();
				try{

					session.beginTransaction();
					List<Student> students = session.createQuery("from Student").getResultList();
					disply(students);
					
					System.out.println("********************************************");
					
					students = session.createQuery("from Student s where s.lastName='alzahrani'").getResultList();
					disply(students);
					
					System.out.println("********************************************");
					students = session.createQuery("from Student s where s.lastName='alzahrani'" +
					" OR s.firstName='mansour'").getResultList();
					disply(students);
					
					System.out.println("********************************************");
					students = session.createQuery("from Student s where s.email like '%@yahoo.com'").getResultList();
					disply(students);
					
					session.getTransaction().commit();
				
					System.out.println("Done");
				}catch (Exception ex){
					ex.printStackTrace();
				}finally{
					factory.close();
				}
			}
	

	private static void disply(List<Student> students) {
		for(Student student : students){
			System.out.println(student);
		}
	}
}
