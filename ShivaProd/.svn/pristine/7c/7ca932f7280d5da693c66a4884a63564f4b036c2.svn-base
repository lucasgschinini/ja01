package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoConceptoTercerosEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;

@Entity
@Table(name = "SHV_COB_FACTURA_MIC")
@PrimaryKeyJoinColumn(name="ID_FACTURA")
public class ShvCobFacturaMic extends ShvCobFactura{

	private static final long serialVersionUID = 1L;
	
	@Column(name="ID_REFERENCIA_FACTURA")
	private String idReferenciaFactura;
	
	@Column(name="CICLO_FACTURACION")
	private Integer cicloFacturacion;
	
	@Column(name="FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name="MARKETING_CUSTOMER_GROUP")
	private String marketingCustomerGroup;
	
	@Column(name="RAZON_SOCIAL_CLIENTE_LEGADO")
	private String razonSocialClienteLegado;

	@Column(name="TIPO_CLIENTE")
	private String tipoCliente;
	
	@Column(name="TIPO_FACTURA")
	private Integer tipoFactura;
	
	@Column(name="TIPO_DUC")
	private String tipoDuc;
	
	@Column(name="COBRADOR_INTERES_BONIF_REGU")
	private BigDecimal cobradorInteresesBonificadosRegulados;
	
	@Column(name="COBRADOR_INTERES_BONIF_NO_REGU")
	private BigDecimal cobradorInteresesBonificadosNoRegulados;
	
	@Enumerated(EnumType.STRING)
	@Column(name="COBRAR_SEGUNDO_VENCIMIENTO")
	private SiNoEnum cobrarSegundoVencimiento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CHECK_DESTRANSFERIR_TERCEROS")
	private SiNoEnum destransferirTerceros;
	
	@Column(name = "FECHA_PUESTA_COBRO")
	private Date fechaPuestaCobro;
	
	@Column(name = "FECHA_ULTIMO_PAGO_PARCIAL")
	private Date fechaUltimoPagoParcial;
	
	@Column(name = "FECHA_SEGUNDO_VENCIMIENTO")
	private Date fechaSegundoVencimiento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_CONCEPTO_TERCEROS", nullable = true)
	private EstadoConceptoTercerosEnum estadoConceptoTerceros;

	@Enumerated(EnumType.STRING)
	@Column(name = "POSIBILIDAD_DESTRANSFERENCIA", nullable = true)
	private SiNoEnum posibilidadDestransferencia;
	
	@Column(name = "IMPORTE_TERCEROS_TRANSFERIDOS")
	private BigDecimal importeTercerosTransferidos;
	
	@Column(name = "SALDO_TER_FINANCIA_TRANSF")
	private BigDecimal saldoTerceroFinanciableTransferible;
	
	@Column(name = "SALDO_TER_FINANCIA_NO_TRANSF")
	private BigDecimal saldoTerceroFinanciableNOTransferible;
	
	@Column(name = "SALDO_TER_NO_FINANCIA_TRANSF")
	private BigDecimal saldoTerceroNOFinanciableTransferible;
	
	@Column(name="COBRADOR_INTERES_TRASLA_NO_REG")
	private BigDecimal cobradorInteresesTrasladadosNoRegulados;
	
	@Column(name="COBRADOR_INTERES_TRASLA_REGU")
	private BigDecimal cobradorInteresesTrasladadosRegulados;
	
	/**
	 * 
	 * @return
	 */
	public String getIdReferenciaFactura() {
		return idReferenciaFactura;
	}
	
	/**
	 * 
	 * @param idReferenciaFactura
	 */
	public void setIdReferenciaFactura(String idReferenciaFactura) {
		this.idReferenciaFactura = idReferenciaFactura;
	}

	/**
	 * @return the cicloFacturacion
	 */
	public Integer getCicloFacturacion() {
		return cicloFacturacion;
	}

	/**
	 * @param cicloFacturacion the cicloFacturacion to set
	 */
	public void setCicloFacturacion(Integer cicloFacturacion) {
		this.cicloFacturacion = cicloFacturacion;
	}

	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the marketingCustomerGroup
	 */
	public String getMarketingCustomerGroup() {
		return marketingCustomerGroup;
	}

	/**
	 * @param marketingCustomerGroup the marketingCustomerGroup to set
	 */
	public void setMarketingCustomerGroup(String marketingCustomerGroup) {
		this.marketingCustomerGroup = marketingCustomerGroup;
	}

	/**
	 * @return the tipoFactura
	 */
	public Integer getTipoFactura() {
		return tipoFactura;
	}

	/**
	 * @param tipoFactura the tipoFactura to set
	 */
	public void setTipoFactura(Integer tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getCobradorInteresesBonificadosRegulados() {
		return cobradorInteresesBonificadosRegulados;
	}

	/**
	 * 
	 * @param interesesBonificadosRegulados
	 */
	public void setCobradorInteresesBonificadosRegulados(BigDecimal interesesBonificadosRegulados) {
		this.cobradorInteresesBonificadosRegulados = interesesBonificadosRegulados;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getCobradorInteresesBonificadosNoRegulados() {
		return cobradorInteresesBonificadosNoRegulados;
	}

	/**
	 * 
	 * @param interesesBonificadosNoRegulados
	 */
	public void setCobradorInteresesBonificadosNoRegulados(
			BigDecimal interesesBonificadosNoRegulados) {
		this.cobradorInteresesBonificadosNoRegulados = interesesBonificadosNoRegulados;
	}

	/**
	 * 
	 * @return
	 */
	public String getRazonSocialClienteLegado() {
		return razonSocialClienteLegado;
	}

	/**
	 * 
	 * @param razonSocialClienteLegado
	 */
	public void setRazonSocialClienteLegado(String razonSocialClienteLegado) {
		this.razonSocialClienteLegado = razonSocialClienteLegado;
	}
	
	@Override
	public String getTipoCliente() {
		return tipoCliente;
	}

	@Override
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return the cobrarSegundoVencimiento
	 */
	public SiNoEnum getCobrarSegundoVencimiento() {
		return cobrarSegundoVencimiento;
	}
	/**
	 * @param cobrarSegundoVencimiento the cobrarSegundoVencimiento to set
	 */
	public void setCobrarSegundoVencimiento(SiNoEnum cobrarSegundoVencimiento) {
		this.cobrarSegundoVencimiento = cobrarSegundoVencimiento;
	}

	/**
	 * @return the destransferirTerceros
	 */
	public SiNoEnum getDestransferirTerceros() {
		return destransferirTerceros;
	}

	/**
	 * @param destransferirTerceros the destransferirTerceros to set
	 */
	public void setDestransferirTerceros(SiNoEnum destransferirTerceros) {
		this.destransferirTerceros = destransferirTerceros;
	}

	/**
	 * @return the fechaPuestaCobro
	 */
	public Date getFechaPuestaCobro() {
		return fechaPuestaCobro;
	}

	/**
	 * @param fechaPuestaCobro the fechaPuestaCobro to set
	 */
	public void setFechaPuestaCobro(Date fechaPuestaCobro) {
		this.fechaPuestaCobro = fechaPuestaCobro;
	}

	/**
	 * @return the fechaUltimoPagoParcial
	 */
	public Date getFechaUltimoPagoParcial() {
		return fechaUltimoPagoParcial;
	}

	/**
	 * @param fechaUltimoPagoParcial the fechaUltimoPagoParcial to set
	 */
	public void setFechaUltimoPagoParcial(Date fechaUltimoPagoParcial) {
		this.fechaUltimoPagoParcial = fechaUltimoPagoParcial;
	}

	/**
	 * @return the tipoDuc
	 */
	public String getTipoDuc() {
		return tipoDuc;
	}

	/**
	 * @param tipoDuc the tipoDuc to set
	 */
	public void setTipoDuc(String tipoDuc) {
		this.tipoDuc = tipoDuc;
	}

	/**
	 * @return the fechaSegundoVencimiento
	 */
	public Date getFechaSegundoVencimiento() {
		return fechaSegundoVencimiento;
	}

	/**
	 * @param fechaSegundoVencimiento the fechaSegundoVencimiento to set
	 */
	public void setFechaSegundoVencimiento(Date fechaSegundoVencimiento) {
		this.fechaSegundoVencimiento = fechaSegundoVencimiento;
	}

	/**
	 * @return the estadoConceptoTerceros
	 */
	public EstadoConceptoTercerosEnum getEstadoConceptoTerceros() {
		return estadoConceptoTerceros;
	}

	/**
	 * @param estadoConceptoTerceros the estadoConceptoTerceros to set
	 */
	public void setEstadoConceptoTerceros(EstadoConceptoTercerosEnum estadoConceptoTerceros) {
		this.estadoConceptoTerceros = estadoConceptoTerceros;
	}

	/**
	 * @return the posibilidadDestransferencia
	 */
	public SiNoEnum getPosibilidadDestransferencia() {
		return posibilidadDestransferencia;
	}

	/**
	 * @param posibilidadDestransferencia the posibilidadDestransferencia to set
	 */
	public void setPosibilidadDestransferencia(SiNoEnum posibilidadDestransferencia) {
		this.posibilidadDestransferencia = posibilidadDestransferencia;
	}

	/**
	 * @return the importeTercerosTransferidos
	 */
	public BigDecimal getImporteTercerosTransferidos() {
		return importeTercerosTransferidos;
	}

	/**
	 * @param importeTercerosTransferidos the importeTercerosTransferidos to set
	 */
	public void setImporteTercerosTransferidos(
			BigDecimal importeTercerosTransferidos) {
		this.importeTercerosTransferidos = importeTercerosTransferidos;
	}

	/**
	 * @return the saldoTerceroFinanciableTransferible
	 */
	public BigDecimal getSaldoTerceroFinanciableTransferible() {
		return saldoTerceroFinanciableTransferible;
	}

	/**
	 * @param saldoTerceroFinanciableTransferible the saldoTerceroFinanciableTransferible to set
	 */
	public void setSaldoTerceroFinanciableTransferible(
			BigDecimal saldoTerceroFinanciableTransferible) {
		this.saldoTerceroFinanciableTransferible = saldoTerceroFinanciableTransferible;
	}

	/**
	 * @return the saldoTerceroFinanciableNOTransferible
	 */
	public BigDecimal getSaldoTerceroFinanciableNOTransferible() {
		return saldoTerceroFinanciableNOTransferible;
	}

	/**
	 * @param saldoTerceroFinanciableNOTransferible the saldoTerceroFinanciableNOTransferible to set
	 */
	public void setSaldoTerceroFinanciableNOTransferible(
			BigDecimal saldoTerceroFinanciableNOTransferible) {
		this.saldoTerceroFinanciableNOTransferible = saldoTerceroFinanciableNOTransferible;
	}

	/**
	 * @return the saldoTerceroNOFinanciableTransferible
	 */
	public BigDecimal getSaldoTerceroNOFinanciableTransferible() {
		return saldoTerceroNOFinanciableTransferible;
	}

	/**
	 * @param saldoTerceroNOFinanciableTransferible the saldoTerceroNOFinanciableTransferible to set
	 */
	public void setSaldoTerceroNOFinanciableTransferible(
			BigDecimal saldoTerceroNOFinanciableTransferible) {
		this.saldoTerceroNOFinanciableTransferible = saldoTerceroNOFinanciableTransferible;
	}

	public BigDecimal getCobradorInteresesTrasladadosNoRegulados() {
		return cobradorInteresesTrasladadosNoRegulados;
	}

	public void setCobradorInteresesTrasladadosNoRegulados(
			BigDecimal cobradorInteresesTrasladadosNoRegulados) {
		this.cobradorInteresesTrasladadosNoRegulados = cobradorInteresesTrasladadosNoRegulados;
	}

	public BigDecimal getCobradorInteresesTrasladadosRegulados() {
		return cobradorInteresesTrasladadosRegulados;
	}

	public void setCobradorInteresesTrasladadosRegulados(
			BigDecimal cobradorInteresesTrasladadosRegulados) {
		this.cobradorInteresesTrasladadosRegulados = cobradorInteresesTrasladadosRegulados;
	}
	
}
