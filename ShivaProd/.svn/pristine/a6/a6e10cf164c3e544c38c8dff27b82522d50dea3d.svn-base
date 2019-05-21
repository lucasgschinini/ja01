package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SHV_PARAM_TIPO_GEST_CONTABLE")
public class ShvParamTipoGestContable {
	
	@Id
	@Column (name="ID_TIPO_GESTION_CONTABLE")
	private Long idTipoGestionContable;
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_OPERAC_CONTABLE", referencedColumnName="ID_TIPO_OPERAC_CONTABLE")
	private ShvParamTipoOperacContable idTipoOperacContable;
	@Column (name="ID_TIPO_VALOR")
	private Long idTipoValor;
	@Column (name="ESTADO")
	private String estado;
	@Column (name="ID_ORIGEN")
	private Long idOrigen;
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_ORIGEN_CONTABLE", referencedColumnName="ID_TIPO_ORIGEN_CONTABLE")
	private ShvParamTipoOrigenContable idTipoOrigenContable;
	@Column (name="INFORMAR_EN_FECHA_DIFERIDA")
	private String informarEnFechaDiferida;
	@Column (name="INFORMAR_IMPORTE_REVERTIDO")
	private String informarImporteRevertido;
	
	
	/**
	 * @return the idTipoGestionContable
	 */
	public Long getIdTipoGestionContable() {
		return idTipoGestionContable;
	}
	/**
	 * @param idTipoGestionContable the idTipoGestionContable to set
	 */
	public void setIdTipoGestionContable(Long idTipoGestionContable) {
		this.idTipoGestionContable = idTipoGestionContable;
	}
	/**
	 * @return the idTipoValor
	 */
	public Long getIdTipoValor() {
		return idTipoValor;
	}
	/**
	 * @param idTipoValor the idTipoValor to set
	 */
	public void setIdTipoValor(Long idTipoValor) {
		this.idTipoValor = idTipoValor;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the idOrigen
	 */
	public Long getIdOrigen() {
		return idOrigen;
	}
	/**
	 * @param idOrigen the idOrigen to set
	 */
	public void setIdOrigen(Long idOrigen) {
		this.idOrigen = idOrigen;
	}
	/**
	 * @return the informarEnFechaDiferida
	 */
	public String getInformarEnFechaDiferida() {
		return informarEnFechaDiferida;
	}
	/**
	 * @param informarEnFechaDiferida the informarEnFechaDiferida to set
	 */
	public void setInformarEnFechaDiferida(String informarEnFechaDiferida) {
		this.informarEnFechaDiferida = informarEnFechaDiferida;
	}
	/**
	 * @return the informarImporteRevertido
	 */
	public String getInformarImporteRevertido() {
		return informarImporteRevertido;
	}
	/**
	 * @param informarImporteRevertido the informarImporteRevertido to set
	 */
	public void setInformarImporteRevertido(String informarImporteRevertido) {
		this.informarImporteRevertido = informarImporteRevertido;
	}
	/**
	 * @return the idTipoOperacContable
	 */
	public ShvParamTipoOperacContable getIdTipoOperacContable() {
		return idTipoOperacContable;
	}
	/**
	 * @param idTipoOperacContable the idTipoOperacContable to set
	 */
	public void setIdTipoOperacContable(
			ShvParamTipoOperacContable idTipoOperacContable) {
		this.idTipoOperacContable = idTipoOperacContable;
	}
	/**
	 * @return the idTipoOrigenContable
	 */
	public ShvParamTipoOrigenContable getIdTipoOrigenContable() {
		return idTipoOrigenContable;
	}
	/**
	 * @param idTipoOrigenContable the idTipoOrigenContable to set
	 */
	public void setIdTipoOrigenContable(
			ShvParamTipoOrigenContable idTipoOrigenContable) {
		this.idTipoOrigenContable = idTipoOrigenContable;
	}
	
	
}
