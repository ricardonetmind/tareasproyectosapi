package es.bit.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import es.bit.persistence.ProyectosManager;

@Entity
@Table(name="tarea")
public class Tarea {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("tid")
	private int id;
	
	@Column
	@JsonProperty("desc")
	private String nombre;
	
	@Column
	private double duracion;
	
	@ManyToOne
    @JoinColumn(name="proyecto", nullable=false)
	@JsonIgnore
	private Proyecto proyecto;
	
	@Transient
	@JsonProperty("proyecto")
	private int pid;
	
	@ManyToOne
    @JoinColumn(name="responsable", nullable=false)	
	@JsonIgnore
	private Usuario responsable= new Usuario(1, "", "", "");
	
	public Tarea() {
		
	}

	public Tarea(int id, String nombre, double duracion, Proyecto proyecto, Usuario responsable) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.proyecto = proyecto;
		this.responsable = responsable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Usuario getResponsable() {
		return responsable;
	}

	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}

	public int getPid() {
		this.pid=this.proyecto.getPid();
		return pid;
	}

	public void setPid(int pid) throws Exception{
		this.proyecto=ProyectosManager.getInstance().getProyecto(pid);
		this.pid = pid;
	}
		

}
