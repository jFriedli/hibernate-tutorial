package ch.jfriedli.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ch.jfriedli.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating new student object...");
			Student student = new Student("Jon", "Doe", "jondoe@example.com");
			
			session.beginTransaction();
			
			System.out.println("Saving the student...");
			session.save(student);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

}
