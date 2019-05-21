package ar.com.telecom.shiva.persistencia.modelo.simple;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.UbicacionChequeEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_LGJ_LEGAJO_CHEQUE_RCHAZADO")
public class ShvLgjLegajoChequeRechazadoSimplificado extends Modelo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_LEGAJO_CHEQUE_RECHAZADO", updatable = false)
	private Long idLegajoChequeRechazado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="UBICACION")
	private UbicacionChequeEnum ubicacion;
	
	@Column(name="ID_COPROPIETARIO")
	private String idCopropietario;

	@Column(name="COPROPIETARIO")
	private String copropietario;
	
	
	
	/**
	 * @return the ubicacion
	 */
	public UbicacionChequeEnum getUbicacion() {
		return ubicacion;
	}
	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(UbicacionChequeEnum ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getIdCopropietario() {
		return idCopropietario;
	}
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}
	public String getCopropietario() {
		return copropietario;
	}
	public void setCopropietario(String copropietario) {
		this.copropietario = copropietario;
	}
	public Long getIdLegajoChequeRechazado() {
		return idLegajoChequeRechazado;
	}
	public void setIdLegajoChequeRechazado(Long idLegajoChequeRechazado) {
		this.idLegajoChequeRechazado = idLegajoChequeRechazado;
	}


}
