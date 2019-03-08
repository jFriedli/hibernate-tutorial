package ch.jfriedli.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ch.jfriedli.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			List<Student> students = session.createQuery("from Student").list();

			displayStudents(students);

			students = session.createQuery(
					"from Student s where s.lastName='Doe'").list();

			displayStudents(students);

			students = session
					.createQuery(
							"from Student s where s.lastName = 'Doe' OR s.firstName='Daffy'")
					.list();
			
			displayStudents(students);

			session.getTransaction().commit();

			System.out.println("Done!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> students) {
		for (Student student : students) {
			System.out.println(student);
		}
	}

}
