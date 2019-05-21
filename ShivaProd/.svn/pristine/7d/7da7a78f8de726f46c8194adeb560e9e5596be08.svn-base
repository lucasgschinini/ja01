package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;

@Entity
@Table(name = "SHV_COB_FACTURA_USUARIO")
@PrimaryKeyJoinColumn(name="ID_FACTURA")
public class ShvCobFacturaUsuarioSinOperacion extends ShvCobFacturaSinOperacion {
	
private static final long serialVersionUID = 1L;
	
	@Column(name="REFERENCIA")	
	private String referencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA")
	private MonedaEnum moneda;
	
	@Column(name="TIPO_CAMBIO")				
	private BigDecimal tipoCambio;

	@Column(name="ID_ADJUNTO")
	private Long idAdjunto;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CHECK_SIN_DIFERENCIA_CAMBIO")
	private SiNoEnum sinDiferenciaCambio;

	/**
	 * @return the referentePago
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referentePago the referentePago to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the moneda
	 */
	public MonedaEnum getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the tipoCambio
	 */
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	/**
	 * @return the idAdjunto
	 */
	public Long getIdAdjunto() {
		return idAdjunto;
	}

	/**
	 * @param long1 the idAdjunto to set
	 */
	public void setIdAdjunto(Long idAdjunto) {
		this.idAdjunto = idAdjunto;
	}

	/**
	 * @return the sinDiferenciaCambio
	 */
	public SiNoEnum getSinDiferenciaCambio() {
		return sinDiferenciaCambio;
	}

	/**
	 * @param sinDiferenciaCambio the sinDiferenciaCambio to set
	 */
	public void setSinDiferenciaCambio(SiNoEnum sinDiferenciaCambio) {
		this.sinDiferenciaCambio = sinDiferenciaCambio;
	}

}
