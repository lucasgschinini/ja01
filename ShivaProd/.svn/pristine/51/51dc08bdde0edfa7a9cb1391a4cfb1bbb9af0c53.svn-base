package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_MOTIVO_SUSPENSION")
public class ShvParamMotivoSuspension extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_MOTIVO_SUSPENSION")
	private String idMotivoSuspension;
	
	@Column (name="DESCRIPCION")
	private String descripcion;

	public String getIdMotivoSuspension() {
		return idMotivoSuspension;
	}

	public void setIdMotivoSuspension(String idMotivoSuspension) {
		this.idMotivoSuspension = idMotivoSuspension;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
