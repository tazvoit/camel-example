package org.mycompany.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Usuario {

	private int id;
	@NotNull
	private String nombre;
	private String puesto;
	private Date fechanacimiento;

	public Usuario() {
		super();
	}

	public Usuario(int id, String nombre, String puesto, Date fechanacimiento) {
		this.id = id;
		this.nombre = nombre;
		this.puesto = puesto;
		this.fechanacimiento = fechanacimiento;
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
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

}
