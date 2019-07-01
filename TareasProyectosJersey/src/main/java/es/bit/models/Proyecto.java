package es.bit.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="proyecto")
@JsonIgnoreProperties(value = { "responsable" })
public class Proyecto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int pid;
	
	@Column
	public String titulo;
	
	@Column
	@JsonIgnore
	public Date fecha_inicio;
	
	@Column
	@JsonIgnore
	public Date fecha_fin;
	
	@ManyToOne
    @JoinColumn(name="responsable", nullable=false)
	@JsonIgnore
	public Usuario responsable= new Usuario(1, "", "", "");
	
	public Proyecto() {
	}

	public Proyecto(int pid, String titulo, Date fecha_inicio, Date fecha_fin, Usuario responsable) {
		this.pid = pid;
		this.titulo = titulo;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.responsable = responsable;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public Usuario getResponsable() {
		return responsable;
	}
	
	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}
	
	 
}
