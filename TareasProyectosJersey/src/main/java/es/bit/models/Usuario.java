package es.bit.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int uid;
	
	
	@Column	
	public String nombre;
	
	@Column
	public String email;
	
	@Column
	public String password;
	
	@Column
	public String foto;
	
	public Usuario() {
	}
	
	
	public Usuario(int uid, String nombre, String email, String foto) {
		super();
		this.uid = uid;
		this.nombre = nombre;
		this.email = email;
		this.foto = foto;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getPassword() {
		this.password=null;
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}	
	
	
}
