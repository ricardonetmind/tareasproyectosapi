package es.bit.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import es.bit.models.Tarea;

public class TareasManagerTest {

	private static SessionFactory sf = null;

	//@BeforeClass
	public static void createSessionFactory() {
		sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	//@Test
	public void testGetTareas() {
		try {
			List<Tarea> listT= TareasManager.getInstance().getTareas();
			assertNotNull(listT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

}
