package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;

@Entity
@Table(name = "SHV_COB_FACTURA_CALIPSO")
@PrimaryKeyJoinColumn(name="ID_FACTURA")
public class ShvCobFacturaCalipso extends ShvCobFactura {
	
	private static final long serialVersionUID = 1L;

	@Column(name="RAZON_SOCIAL_CLIENTE_LEGADO")	
	private String razonSocialClienteLegado;
	
	@Column(name="TIPO_CLIENTE")				
	private String tipoCliente;

	@Column(name="IMPORTE_ORIGINAL_MONEDA_ORIGEN")				
	private BigDecimal importeOriginalMonedaOrigen;

	@Column(name="SALDO_ACTUAL_MONEDA_ORIGEN")				
	private BigDecimal saldoActualMonedaOrigen;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA")
	private MonedaEnum moneda;
	
	@Column(name="TIPO_CAMBIO")				
	private BigDecimal tipoCambio;

	@Column(name="COBRADOR_INTERESES_BONIFICADOS")
	private BigDecimal cobradorInteresesBonificados;
	
	@Column(name="COBRADOR_INTERES_TRASLA_REGU")
	private BigDecimal cobradorInteresesTrasladadosRegulados;
	
	@Column(name="COBRADOR_MONTO_A_CUENTA")
	private BigDecimal cobradorMontoACuenta;
	
	@Column(name="ID_FACTURA_PADRE")
	private Integer idFacturaCalipsoPadre;
	
	@Column(name="ID_DOCUMENTO_CUENTA_COBRANZA")
	private Long idDocumentoCuentaCobranza;
	
	@Column(name="TIPO_DE_CAMBIO_FECHA_EMISION")
	private BigDecimal tipoDeCambioFechaEmision;
			
	@Column(name="TIPO_DE_CAMBIO_FECHA_COBRO")
	private BigDecimal tipoDeCambioFechaCobro;

	@Column(name="IMPORTE_APLIC_FEC_EMIS_MON_PES")
	private BigDecimal importeAplicadoAFechaEmisionMonedaPesos;
			
	@Column(name="IMPORTE_APLIC_FEC_EMIS_MON_ORI")
	private BigDecimal importeAplicadoAFechaEmisionMonedaOrigen;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CHECK_SIN_DIFERENCIA_CAMBIO")
	private SiNoEnum sinDiferenciaCambio;	
	
	@Column(name="IMPORTE_CAPITAL")
	private BigDecimal importeCapital;
	
	@Column(name="IMPORTE_IMPUESTOS")
	private BigDecimal importeImpuestos;
			
	@Column(name="IMPORTE_APLICADO")
	private BigDecimal importeAplicado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ORIGEN_DOCUMENTO", nullable = true)
	private OrigenDocumentoEnum origenDocumento;
	
	@Column(name="UNIDAD_OPERATIVA")
	private String unidadOperativa;
	
	@Column(name="SUBTIPO")
	private String subtipo;
	
	@Column(name="HOLDING")
	private String holding;

	// Sprint Dolares
	@Column(name="ID_DOC_CTAS_COB_PADRE")
	private Long idDocumentoCuentaCobranzaPadre;
	/**
	 * @return the razonSocialClienteLegado
	 */
	public String getRazonSocialClienteLegado() {
		return razonSocialClienteLegado;
	}

	/**
	 * @param razonSocialClienteLegado the razonSocialClienteLegado to set
	 */
	public void setRazonSocialClienteLegado(String razonSocialClienteLegado) {
		this.razonSocialClienteLegado = razonSocialClienteLegado;
	}

	/**
	 * @return the tipoCliente
	 */
	@Override
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	@Override
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
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
	 * @return the importeOriginalPesos
	 */
	public BigDecimal getImporteOriginalMonedaOrigen() {
		return importeOriginalMonedaOrigen;
	}

	/**
	 * @param importeOriginalMonedaOrigen the importeOriginalMonedaOrigen to set
	 */
	public void setImporteOriginalMonedaOrigen(BigDecimal importeOriginalMonedaOrigen) {
		this.importeOriginalMonedaOrigen = importeOriginalMonedaOrigen;
	}

	/**
	 * @return the saldoActualMonedaOrigen
	 */
	public BigDecimal getSaldoActualMonedaOrigen() {
		return saldoActualMonedaOrigen;
	}

	/**
	 * @param saldoActualMonedaOrigen the saldoActualMonedaOrigen to set
	 */
	public void setSaldoActualMonedaOrigen(BigDecimal saldoActualPesosMonedaOrigen) {
		this.saldoActualMonedaOrigen = saldoActualPesosMonedaOrigen;
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
	 * 
	 * @param cobradorInteresesBonificados
	 */
	public BigDecimal getCobradorInteresesBonificados() {
		return cobradorInteresesBonificados;
	}
	/**
	 * 
	 * @param interesesBonificados
	 */
	public void setCobradorInteresesBonificados(BigDecimal interesesBonificados) {
		this.cobradorInteresesBonificados = interesesBonificados;
	}

	public BigDecimal getCobradorMontoACuenta() {
		return cobradorMontoACuenta;
	}

	public void setCobradorMontoACuenta(BigDecimal montoACuenta) {
		this.cobradorMontoACuenta = montoACuenta;
	}

	public Integer getIdFacturaCalipsoPadre() {
		return idFacturaCalipsoPadre;
	}

	public void setIdFacturaCalipsoPadre(Integer idFacturaCalipsoPadre) {
		this.idFacturaCalipsoPadre = idFacturaCalipsoPadre;
	}

	public Long getIdDocumentoCuentaCobranza() {
		return idDocumentoCuentaCobranza;
	}

	public void setIdDocumentoCuentaCobranza(Long idDocumentoCuentaCobranza) {
		this.idDocumentoCuentaCobranza = idDocumentoCuentaCobranza;
	}

	/**
	 * @return the tipoDeCambioFechaEmision
	 */
	public BigDecimal getTipoDeCambioFechaEmision() {
		return tipoDeCambioFechaEmision;
	}

	/**
	 * @param tipoDeCambioFechaEmision the tipoDeCambioFechaEmision to set
	 */
	public void setTipoDeCambioFechaEmision(BigDecimal tipoDeCambioFechaEmision) {
		this.tipoDeCambioFechaEmision = tipoDeCambioFechaEmision;
	}

	/**
	 * @return the tipoDeCambioFechaCobro
	 */
	public BigDecimal getTipoDeCambioFechaCobro() {
		return tipoDeCambioFechaCobro;
	}

	/**
	 * @param tipoDeCambioFechaCobro the tipoDeCambioFechaCobro to set
	 */
	public void setTipoDeCambioFechaCobro(BigDecimal tipoDeCambioFechaCobro) {
		this.tipoDeCambioFechaCobro = tipoDeCambioFechaCobro;
	}

	/**
	 * @return the importeAplicadoAFechaEmisionMonedaPesos
	 */
	public BigDecimal getImporteAplicadoAFechaEmisionMonedaPesos() {
		return importeAplicadoAFechaEmisionMonedaPesos;
	}

	/**
	 * @param importeAplicadoAFechaEmisionMonedaPesos the importeAplicadoAFechaEmisionMonedaPesos to set
	 */
	public void setImporteAplicadoAFechaEmisionMonedaPesos(
			BigDecimal importeAplicadoAFechaEmisionMonedaPesos) {
		this.importeAplicadoAFechaEmisionMonedaPesos = importeAplicadoAFechaEmisionMonedaPesos;
	}

	/**
	 * @return the importeAplicadoAFechaEmisionMonedaOrigen
	 */
	public BigDecimal getImporteAplicadoAFechaEmisionMonedaOrigen() {
		return importeAplicadoAFechaEmisionMonedaOrigen;
	}

	/**
	 * @param importeAplicadoAFechaEmisionMonedaOrigen the importeAplicadoAFechaEmisionMonedaOrigen to set
	 */
	public void setImporteAplicadoAFechaEmisionMonedaOrigen(
			BigDecimal importeAplicadoAFechaEmisionMonedaOrigen) {
		this.importeAplicadoAFechaEmisionMonedaOrigen = importeAplicadoAFechaEmisionMonedaOrigen;
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
	

	/**
	 * @return the importeCapital
	 */
	public BigDecimal getImporteCapital() {
		return importeCapital;
	}

	/**
	 * @param importeCapital the importeCapital to set
	 */
	public void setImporteCapital(BigDecimal importeCapital) {
		this.importeCapital = importeCapital;
	}

	/**
	 * @return the importeImpuestos
	 */
	public BigDecimal getImporteImpuestos() {
		return importeImpuestos;
	}

	/**
	 * @param importeImpuestos the importeImpuestos to set
	 */
	public void setImporteImpuestos(BigDecimal importeImpuestos) {
		this.importeImpuestos = importeImpuestos;
	}

	/**
	 * @return the importeAplicado
	 */
	public BigDecimal getImporteAplicado() {
		return importeAplicado;
	}

	/**
	 * @param importeAplicado the importeAplicado to set
	 */
	public void setImporteAplicado(BigDecimal importeAplicado) {
		this.importeAplicado = importeAplicado;
	}

	/**
	 * @return the origenDocumento
	 */
	public OrigenDocumentoEnum getOrigenDocumento() {
		return origenDocumento;
	}

	/**
	 * @param origenDocumento the origenDocumento to set
	 */
	public void setOrigenDocumento(OrigenDocumentoEnum origenDocumento) {
		this.origenDocumento = origenDocumento;
	}

	/**
	 * @return the unidadOperativa
	 */
	public String getUnidadOperativa() {
		return unidadOperativa;
	}

	/**
	 * @param unidadOperativa the unidadOperativa to set
	 */
	public void setUnidadOperativa(String unidadOperativa) {
		this.unidadOperativa = unidadOperativa;
	}

	/**
	 * @return the subtipo
	 */
	public String getSubtipo() {
		return subtipo;
	}

	/**
	 * @param subtipo the subtipo to set
	 */
	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	/**
	 * @return the holding
	 */
	public String getHolding() {
		return holding;
	}

	/**
	 * @param holding the holding to set
	 */
	public void setHolding(String holding) {
		this.holding = holding;
	}

	public BigDecimal getCobradorInteresesTrasladadosRegulados() {
		return cobradorInteresesTrasladadosRegulados;
	}

	public void setCobradorInteresesTrasladadosRegulados(
			BigDecimal cobradorInteresesTrasladadosRegulados) {
		this.cobradorInteresesTrasladadosRegulados = cobradorInteresesTrasladadosRegulados;
	}

	/**
	 * @return the idDocumentoCuentaCobranzaPadre
	 */
	public Long getIdDocumentoCuentaCobranzaPadre() {
		return idDocumentoCuentaCobranzaPadre;
	}

	/**
	 * @param idDocumentoCuentaCobranzaPadre the idDocumentoCuentaCobranzaPadre to set
	 */
	public void setIdDocumentoCuentaCobranzaPadre(
			Long idDocumentoCuentaCobranzaPadre) {
		this.idDocumentoCuentaCobranzaPadre = idDocumentoCuentaCobranzaPadre;
	}
}
