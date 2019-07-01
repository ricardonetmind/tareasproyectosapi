package es.bit.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import es.bit.models.Proyecto;

public class ProyectosManager {
	private static ProyectosManager instancia = null;
	private static SessionFactory sf = null;

	public static ProyectosManager getInstance() throws Exception {
		if (instancia == null)
			instancia = new ProyectosManager();

		return instancia;
	}

	private ProyectosManager() throws Exception {
		sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public int createProyecto(Proyecto newP) throws Exception {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		int id = ((Integer) session.save(newP)).intValue();

		t.commit();
		session.close();

		return id;
	}

	public List<Proyecto> getProyectos() throws Exception {
		Session session = sf.openSession();

		List<Proyecto> listaT = session.createQuery("FROM Proyecto").list();

		session.close();

		return listaT;
	}
	
	public Proyecto getProyecto(int id) throws Exception {
		Session session = sf.openSession();

		Proyecto recT = session.get(Proyecto.class, id);

		session.close();

		return recT;
	}
	
	public boolean updateProyecto(Proyecto aP)throws Exception {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		session.update(aP);
		t.commit();
		session.close();
		return true;
	}
	
	public boolean deleteProyecto(Proyecto aP)throws Exception {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		session.delete(aP);
		t.commit();
		session.close();
		return true;
	}
	
}
