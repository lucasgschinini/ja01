package ar.com.telecom.shiva.persistencia.modelo.param;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_CUIT")
public class ShvParamCuit extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_CUIT")
	private Long idCuit;
	
	@Column (name="CUIT")
	private String cuit;
	
	@Column (name="ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	@Column (name="ID_SEGMENTO")
	private String idSegmento;


	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/

	
	public Long getIdCuit() {
		return idCuit;
	}

	public void setIdCuit(Long idCuit) {
		this.idCuit = idCuit;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	public String getIdSegmento() {
		return idSegmento;
	}

	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}

}

