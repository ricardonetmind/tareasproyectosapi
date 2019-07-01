package es.bit.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import es.bit.models.Tarea;

public class TareasManager {
	private static TareasManager instancia = null;
	private static SessionFactory sf = null;

	public static TareasManager getInstance() throws Exception {
		if (instancia == null)
			instancia = new TareasManager();

		return instancia;
	}

	private TareasManager() throws Exception {
		sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public int createTarea(Tarea newT) throws Exception {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		int id = ((Integer) session.save(newT)).intValue();

		t.commit();
		session.close();

		return id;
	}

	public List<Tarea> getTareas() throws Exception {
		Session session = sf.openSession();

		List<Tarea> listaT = session.createQuery("FROM Tarea").list();

		session.close();

		return listaT;
	}
	
	public Tarea getTarea(int id) throws Exception {
		Session session = sf.openSession();

		Tarea recT = session.get(Tarea.class, id);

		session.close();

		return recT;
	}
	
	public boolean updateTarea(Tarea aT)throws Exception {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		session.update(aT);
		t.commit();
		session.close();
		return true;
	}
	
	public boolean deleteTarea(Tarea aT)throws Exception {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		session.delete(aT);
		t.commit();
		session.close();
		return true;
	}

	
}
