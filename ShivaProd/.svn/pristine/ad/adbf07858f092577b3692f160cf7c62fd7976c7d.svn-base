package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;


@Entity
@Table(name = "SHV_PARAM_MOTIVO_DESCOBRO")
public class ShvParamMotivoDescobro extends Modelo {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_MOTIVO_DESCOBRO")
	private Integer idMotivoDescobro;
	
	@Column (name="DESCRIPCION")
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	@Column (name="USO_OPERACION_MASIVA")
	private SiNoEnum usoOperacionMasiva;

	
	/**
	 * @return the idMotivoDescobro
	 */
	public Integer getIdMotivoDescobro() {
		return idMotivoDescobro;
	}

	/**
	 * @param idMotivoDescobro the idMotivoDescobro to set
	 */
	public void setIdMotivoDescobro(Integer idMotivoDescobro) {
		this.idMotivoDescobro = idMotivoDescobro;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the usoOperacionMasiva
	 */
	public SiNoEnum getUsoOperacionMasiva() {
		return usoOperacionMasiva;
	}

	/**
	 * @param usoOperacionMasiva the usoOperacionMasiva to set
	 */
	public void setUsoOperacionMasiva(SiNoEnum usoOperacionMasiva) {
		this.usoOperacionMasiva = usoOperacionMasiva;
	}

}
