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

import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

@Entity
@Table(name = "SHV_COB_MED_PAG_CTA")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
public class ShvCobMedioPagoCTASinOperacion extends ShvCobMedioPagoCalipsoSinOperacion {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="NUMERO_COMPROBANTE")	 	
	private String nroComprobante;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CLASE_COMPROBANTE")
	private TipoClaseComprobanteEnum claseComprobante;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_COMPROBANTE")
	private TipoComprobanteEnum	tipoComprobante;
	
	@Column(name="SUCURSAL_COMPROBANTE")
	private String sucursalComprobante;

	@Column(name="ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	@Column(name="FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name="ID_COBRANZA")
	private Long idCobranza;

	@Enumerated(EnumType.STRING)
	@Column(name="GENERADO_POR_COBRO")
	private SiNoEnum generadoPorCobro;

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
	@JoinColumn(name = "ID_MEDIO_PAGO_PADRE", referencedColumnName = "ID_MEDIO_PAGO", updatable = false)
	private ShvCobMedioPagoCTASinOperacion medioPagoPadre;

	@Column(name="ID_DOCUMENTO_CUENTA_COBRANZA")
	private Long idDocumentoCuentaCobranza;
	
	@Override
	public Date getFechaValor() {
		return DateUtils.truncate(fechaEmision, Calendar.DATE);
	}
	
	@Override
	public String getReferencia() {
		
		StringBuffer numeroLegal = new StringBuffer();
		
        // Si el comprobante es "D" o "S", el mismo no se envía ya que para el usuario es un blanco. (aplicará solo para MIC)
        if (!TipoClaseComprobanteEnum.D.equals(this.getClaseComprobante()) && !TipoClaseComprobanteEnum.S.equals(this.getClaseComprobante())) {
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

	public String getNroComprobante() {
		return nroComprobante;
	}

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

	public String getSucursalComprobante() {
		return sucursalComprobante;
	}

	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
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
	public ShvCobMedioPagoCTASinOperacion getMedioPagoPadre() {
		return medioPagoPadre;
	}
	public void setMedioPagoPadre(ShvCobMedioPagoCTASinOperacion medioPagoPadre) {
		this.medioPagoPadre = medioPagoPadre;
	}
	public Long getIdDocumentoCuentaCobranza() {
		return idDocumentoCuentaCobranza;
	}
	public void setIdDocumentoCuentaCobranza(Long idDocumentoCuentaCobranza) {
		this.idDocumentoCuentaCobranza = idDocumentoCuentaCobranza;
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
}
