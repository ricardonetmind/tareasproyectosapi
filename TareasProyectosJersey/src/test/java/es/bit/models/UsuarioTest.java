package es.bit.models;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class UsuarioTest {

	private static SessionFactory sf = null;

	//@BeforeClass
	public static void createSessionFactory() {
		sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	//@Test
	public void testSession() {
		Session session = sf.openSession();

		assertNotNull(session);
	}

}
