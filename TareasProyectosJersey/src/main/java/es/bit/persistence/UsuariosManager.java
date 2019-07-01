package es.bit.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import es.bit.models.Usuario;

public class UsuariosManager {
	private static UsuariosManager instancia = null;
	private static SessionFactory sf = null;

	public static UsuariosManager getInstance() throws Exception {
		if (instancia == null)
			instancia = new UsuariosManager();

		return instancia;
	}

	private UsuariosManager() throws Exception {
		sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public int createUsuario(Usuario newU) throws Exception {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		int id = ((Integer) session.save(newU)).intValue();

		t.commit();
		session.close();

		return id;
	}

	public List<Usuario> getUsuarios() throws Exception {
		Session session = sf.openSession();

		List<Usuario> listaU = session.createQuery("FROM Usuario").list();

		session.close();

		return listaU;
	}
	
	public Usuario getUsuario(int id) throws Exception {
		Session session = sf.openSession();

		Usuario recU = session.get(Usuario.class, id);

		session.close();

		return recU;
	}
	
	public boolean updateUsuario(Usuario aU)throws Exception {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		session.update(aU);
		t.commit();
		session.close();
		return true;
	}
	
	public boolean deleteUsuario(Usuario aU)throws Exception {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		session.delete(aU);
		t.commit();
		session.close();
		return true;
	}

	public Usuario authUsuario(String email, String password) {
		Session session = sf.openSession();

		Usuario recU = session.createQuery("FROM Usuario U WHERE U.email = :email AND U.password= :password", Usuario.class)
				.setParameter("email", email).setParameter("password", password).uniqueResult();

		session.close();

		return recU;
	}
	
}
