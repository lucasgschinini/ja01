package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

/**
 * @author u578936 M.A.Uehara
 *
 */
@Entity
@Table(name = "SHV_PARAM_CONF_REGLA_CAMPO")
public class ShvParamConfReglaCampo extends Modelo {
	private static final long serialVersionUID = 20180519L;

	@Id
	@Column (name="ID_CONF_REGLA")
	private Long idConfRegla;
	@Column (name="ID_CONF_CAMPO")
	private String idConfCampo;
	@Column (name="ORDEN")
	private Long orden;
	@Column (name="OBLIGATORIEDAD")
	private String obligatoriedad;

	public ShvParamConfReglaCampo() {
	}

	/**
	 * @return the idConfRegla
	 */
	public Long getIdConfRegla() {
		return idConfRegla;
	}

	/**
	 * @param idConfRegla the idConfRegla to set
	 */
	public void setIdConfRegla(Long idConfRegla) {
		this.idConfRegla = idConfRegla;
	}

	/**
	 * @return the idConfCampo
	 */
	public String getIdConfCampo() {
		return idConfCampo;
	}

	/**
	 * @param idConfCampo the idConfCampo to set
	 */
	public void setIdConfCampo(String idConfCampo) {
		this.idConfCampo = idConfCampo;
	}

	/**
	 * @return the orden
	 */
	public Long getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Long orden) {
		this.orden = orden;
	}

	/**
	 * @return the obligatoriedad
	 */
	public String getObligatoriedad() {
		return obligatoriedad;
	}

	/**
	 * @param obligatoriedad the obligatoriedad to set
	 */
	public void setObligatoriedad(String obligatoriedad) {
		this.obligatoriedad = obligatoriedad;
	}

	
}
