package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateUtils;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

@Entity
@Table(name = "SHV_COB_MED_PAG_NOTA_CRED_CLP")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
public class ShvCobMedioPagoNotaCreditoCalipsoSinOperacion extends ShvCobMedioPagoCalipsoSinOperacion {
	
	private static final long serialVersionUID = 1L;

	
	@Column(name="NUMERO_COMPROBANTE")
	private String nroComprobante;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CLASE_COMPROBANTE")
	private TipoClaseComprobanteEnum claseComprobante;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_COMPROBANTE")
	private TipoComprobanteEnum  tipoComprobante;
	
	@Column(name="SUCURSAL_COMPROBANTE")
	private String	sucursalComprobante;
	
	@Column(name="ID_CLIENTE_LEGADO")	 	
	private Long idClienteLegado;

	@Column(name="FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name="ID_COBRANZA")
	private Long idCobranza;

	@Enumerated(EnumType.STRING)
	@Column(name="GENERADO_POR_COBRO")
	private SiNoEnum generadoPorCobro;

	@Column(name="ID_DOCUMENTO_CUENTA_COBRANZA")
	private Long idDocumentoCuentaCobranza;

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

	@Column(name="FECHA_VENCIMIENTO")
	private Date fechaVencimiento;
	
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
	
	@ManyToOne
	@JoinColumn(name = "ID_MEDIO_PAGO_PADRE", referencedColumnName = "ID_MEDIO_PAGO", nullable=true)
	private ShvCobMedioPagoSinOperacion medioPagoPadre;
	
	@Column(name="IMPORTE_APLIC_FEC_EMIS_MON_PES")
	private BigDecimal importeAplicadoAFechaEmisionMonedaPesos;
	
	@Override
	public Date getFechaValor() {
		return DateUtils.truncate(fechaEmision, Calendar.DATE);
	}
	
	@Override
	public String getReferencia() {

		StringBuffer numeroLegal = new StringBuffer();
		
        // Si el comprobante es "D" o "S", el mismo no se envía ya que para el usuario es un blanco. (aplicará solo para MIC)
        if (!Validaciones.isObjectNull(this.getClaseComprobante()) && !TipoClaseComprobanteEnum.D.equals(this.getClaseComprobante()) && !TipoClaseComprobanteEnum.S.equals(this.getClaseComprobante())) {
            numeroLegal.append(this.getClaseComprobante().name());
        }
        
        numeroLegal.append(Utilidad.rellenarCerosIzquierda(this.getSucursalComprobante(), 4));
        numeroLegal.append("-");
        numeroLegal.append(Utilidad.rellenarCerosIzquierda(this.getNroComprobante(), 8));
       
        return numeroLegal.toString();	
    }	
	
	@Override
	public String getIdBusquedaRespuestaCobrador() {
		String idBusquedaRespuestaCobrador = "";
		
		if(!Validaciones.isObjectNull(tipoComprobante)
				&& !Validaciones.isObjectNull(claseComprobante)
				&& !Validaciones.isNullEmptyOrDash(sucursalComprobante)
				&& !Validaciones.isNullEmptyOrDash(nroComprobante)
				){
			idBusquedaRespuestaCobrador = tipoComprobante.name() + claseComprobante.name() + sucursalComprobante + nroComprobante;
		}
		
		return idBusquedaRespuestaCobrador;	
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
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * @param nroComprobante the nroComprobante to set
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}


	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}

	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}

	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	/**
	 * @return the sucursalComprobante
	 */
	public String getSucursalComprobante() {
		return sucursalComprobante;
	}

	/**
	 * @param sucursalComprobante the sucursalComprobante to set
	 */
	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}

	/**
	 * @return the idClienteLegado
	 */
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	public SiNoEnum getGeneradoPorCobro() {
		return generadoPorCobro;
	}

	public void setGeneradoPorCobro(SiNoEnum generadoPorCobro) {
		this.generadoPorCobro = generadoPorCobro;
	}

	public Long getIdCobranza() {
		return idCobranza;
	}

	public void setIdCobranza(Long idCobranza) {
		this.idCobranza = idCobranza;
	}

	public ShvCobMedioPagoSinOperacion getMedioPagoPadre() {
		return medioPagoPadre;
	}

	public void setMedioPagoPadre(ShvCobMedioPagoSinOperacion medioPagoPadre) {
		this.medioPagoPadre = medioPagoPadre;
	}

	public Long getIdDocumentoCuentaCobranza() {
		return idDocumentoCuentaCobranza;
	}

	public void setIdDocumentoCuentaCobranza(Long idDocumentoCuentaCobranza) {
		this.idDocumentoCuentaCobranza = idDocumentoCuentaCobranza;
	}

	public String getRazonSocialClienteLegado() {
		return razonSocialClienteLegado;
	}

	public void setRazonSocialClienteLegado(String razonSocialClienteLegado) {
		this.razonSocialClienteLegado = razonSocialClienteLegado;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public BigDecimal getImporteOriginalMonedaOrigen() {
		return importeOriginalMonedaOrigen;
	}

	public void setImporteOriginalMonedaOrigen(
			BigDecimal importeOriginalMonedaOrigen) {
		this.importeOriginalMonedaOrigen = importeOriginalMonedaOrigen;
	}

	public BigDecimal getSaldoActualMonedaOrigen() {
		return saldoActualMonedaOrigen;
	}

	public void setSaldoActualMonedaOrigen(
			BigDecimal saldoActualPesosMonedaOrigen) {
		this.saldoActualMonedaOrigen = saldoActualPesosMonedaOrigen;
	}

	public MonedaEnum getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

	public BigDecimal getImporteAplicadoAFechaEmisionMonedaPesos() {
		return importeAplicadoAFechaEmisionMonedaPesos;
	}

	public void setImporteAplicadoAFechaEmisionMonedaPesos(
			BigDecimal importeAplicadoAFechaEmisionMonedaPesos) {
		this.importeAplicadoAFechaEmisionMonedaPesos = importeAplicadoAFechaEmisionMonedaPesos;
	}
}
