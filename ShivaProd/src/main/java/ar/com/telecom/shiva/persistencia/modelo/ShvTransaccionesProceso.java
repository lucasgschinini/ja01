package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_PROCESO_TRANSACCIONES")
public class ShvTransaccionesProceso extends Modelo {

	private static final long serialVersionUID = -329870154020320364L;

	@Id
	@Column(name="PROCESO") 	
	private String proceso;
	
	@Column(name="MINUTOS")	 			
	private	Integer	minutos;
	
	@Column(name="TRANSACCIONES")	 			
	private	Integer	transacciones;
	
	@Column(name="HABILITADO")
	private	Boolean	habilitado;

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public Integer getMinutos() {
		return minutos;
	}

	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}

	public Integer getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Integer transacciones) {
		this.transacciones = transacciones;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

}
