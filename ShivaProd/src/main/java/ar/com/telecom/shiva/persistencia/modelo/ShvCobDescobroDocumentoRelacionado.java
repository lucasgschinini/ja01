package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;

@Entity
@Table(name = "SHV_COB_DESCOBRO_DOC_RELAC")
public class ShvCobDescobroDocumentoRelacionado extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_DESCOBRO_DOC_REL")
    @SequenceGenerator(name="SEQ_SHV_COB_DESCOBRO_DOC_REL", sequenceName="SEQ_SHV_COB_DESCOBRO_DOC_REL", allocationSize = 1)
	@Column(name="ID_DOCUMENTO_RELACIONADO")
	private Long idDocumentoRelacionado;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_DESCOBRO", referencedColumnName="ID_DESCOBRO")
	
	private ShvCobDescobro descobro;
	
	@Column(name="ID_OPERACION")
	private Long idOperacion;
	
	@Column(name="NRO_TRANSACCION")	
	private Integer nroTransaccion;
	
	@Column(name="ID_COBRANZA_GEN")
	private Long idCobranzaGenerado;
	
	@Column(name="IMPORTE_GEN")
	private BigDecimal importeGenerado;
	
	@Column(name="IMPORTE_CAPITAL_GEN")
	private BigDecimal importeCapitalGenerado;
	
	@Column(name="IMPORTE_IMPUESTOS_GEN")
	private BigDecimal importeImpuestosGenerado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_COMPROBANTE_GEN")
	private TipoComprobanteEnum tipoComprobanteGenerado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CLASE_COMPROBANTE_GEN")
	private TipoClaseComprobanteEnum claseComprobanteGenerado;
	
	@Column(name="SUCURSAL_COMPROBANTE_GEN")
	private String  sucursalComprobanteGenerado;
	
	@Column(name="NUMERO_COMPROBANTE_GEN")
	private String  numeroComprobanteGenerado;
	
	@Column(name="ID_DOC_CUENTAS_COBRANZA_GEN")
	private Long idDocumentoCuentasCobranzaGenerado;
	
	@Column(name="FECHA_VENCIMIENTO_GEN")
	private Date fechaVencimientoGenerada;
	
	@Column(name="IMPORTE_APLICADO_GEN")
	private BigDecimal importeAplicadoGenerado;

	@Enumerated(EnumType.STRING)
	@Column(name="ORIGEN_DOCUMENTO_GEN")
	private OrigenDocumentoEnum origenDocumentoGenerado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_COMPROBANTE_ORI")
	private TipoComprobanteEnum tipoComprobanteOriginal;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CLASE_COMPROBANTE_ORI")
	private TipoClaseComprobanteEnum claseComprobanteOriginal;
	
	@Column(name="SUCURSAL_COMPROBANTE_ORI")
	private String  sucursalComprobanteOriginal;
	
	@Column(name="NUMERO_COMPROBANTE_ORI")
	private String  numeroComprobanteOriginal;
	
	@Column(name="ID_DOC_CUENTAS_COBRANZA_ORI")
	private Long idDocumentoCuentasCobranzaOriginal;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_ORIGEN")
	private SistemaEnum sistemaOrigen;
	
	@Column(name="FECHA_IMPUTACION")
	private Date fechaImputacion;
	
	
	public ShvCobDescobro getDescobro() {
		return descobro;
	}

	public void setDescobro(ShvCobDescobro descobro) {
		this.descobro = descobro;
	}

	/**
	 * @return the idDocumentoRelacionado
	 */
	public Long getIdDocumentoRelacionado() {
		return idDocumentoRelacionado;
	}

	/**
	 * @param idDocumentoRelacionado the idDocumentoRelacionado to set
	 */
	public void setIdDocumentoRelacionado(Long idDocumentoRelacionado) {
		this.idDocumentoRelacionado = idDocumentoRelacionado;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Integer getNroTransaccion() {
		return nroTransaccion;
	}

	public void setNroTransaccion(Integer nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}

	public Long getIdCobranzaGenerado() {
		return idCobranzaGenerado;
	}

	public void setIdCobranzaGenerado(Long idCobranzaGenerado) {
		this.idCobranzaGenerado = idCobranzaGenerado;
	}

	public BigDecimal getImporteGenerado() {
		return importeGenerado;
	}

	public void setImporteGenerado(BigDecimal importeGenerado) {
		this.importeGenerado = importeGenerado;
	}

	public BigDecimal getImporteCapitalGenerado() {
		return importeCapitalGenerado;
	}

	public void setImporteCapitalGenerado(BigDecimal importeCapitalGenerado) {
		this.importeCapitalGenerado = importeCapitalGenerado;
	}

	public BigDecimal getImporteImpuestosGenerado() {
		return importeImpuestosGenerado;
	}

	public void setImporteImpuestosGenerado(BigDecimal importeImpuestosGenerado) {
		this.importeImpuestosGenerado = importeImpuestosGenerado;
	}

	public TipoComprobanteEnum getTipoComprobanteGenerado() {
		return tipoComprobanteGenerado;
	}

	public void setTipoComprobanteGenerado(
			TipoComprobanteEnum tipoComprobanteGenerado) {
		this.tipoComprobanteGenerado = tipoComprobanteGenerado;
	}

	public TipoClaseComprobanteEnum getClaseComprobanteGenerado() {
		return claseComprobanteGenerado;
	}

	public void setClaseComprobanteGenerado(
			TipoClaseComprobanteEnum claseComprobanteGenerado) {
		this.claseComprobanteGenerado = claseComprobanteGenerado;
	}

	public String getSucursalComprobanteGenerado() {
		return sucursalComprobanteGenerado;
	}

	public void setSucursalComprobanteGenerado(String sucursalComprobanteGenerado) {
		this.sucursalComprobanteGenerado = sucursalComprobanteGenerado;
	}

	public String getNumeroComprobanteGenerado() {
		return numeroComprobanteGenerado;
	}

	public void setNumeroComprobanteGenerado(String numeroComprobanteGenerado) {
		this.numeroComprobanteGenerado = numeroComprobanteGenerado;
	}

	public Long getIdDocumentoCuentasCobranzaGenerado() {
		return idDocumentoCuentasCobranzaGenerado;
	}

	public void setIdDocumentoCuentasCobranzaGenerado(
			Long idDocumentoCuentasCobranzaGenerado) {
		this.idDocumentoCuentasCobranzaGenerado = idDocumentoCuentasCobranzaGenerado;
	}

	public Date getFechaVencimientoGenerada() {
		return fechaVencimientoGenerada;
	}

	public void setFechaVencimientoGenerada(Date fechaVencimientoGenerada) {
		this.fechaVencimientoGenerada = fechaVencimientoGenerada;
	}

	public BigDecimal getImporteAplicadoGenerado() {
		return importeAplicadoGenerado;
	}

	public void setImporteAplicadoGenerado(BigDecimal importeAplicadoGenerado) {
		this.importeAplicadoGenerado = importeAplicadoGenerado;
	}

	public OrigenDocumentoEnum getOrigenDocumentoGenerado() {
		return origenDocumentoGenerado;
	}

	public void setOrigenDocumentoGenerado(OrigenDocumentoEnum origenDocumentoGenerado) {
		this.origenDocumentoGenerado = origenDocumentoGenerado;
	}

	public TipoComprobanteEnum getTipoComprobanteOriginal() {
		return tipoComprobanteOriginal;
	}

	public void setTipoComprobanteOriginal(
			TipoComprobanteEnum tipoComprobanteOriginal) {
		this.tipoComprobanteOriginal = tipoComprobanteOriginal;
	}

	public TipoClaseComprobanteEnum getClaseComprobanteOriginal() {
		return claseComprobanteOriginal;
	}

	public void setClaseComprobanteOriginal(
			TipoClaseComprobanteEnum claseComprobanteOriginal) {
		this.claseComprobanteOriginal = claseComprobanteOriginal;
	}

	public String getSucursalComprobanteOriginal() {
		return sucursalComprobanteOriginal;
	}

	public void setSucursalComprobanteOriginal(String sucursalComprobanteOriginal) {
		this.sucursalComprobanteOriginal = sucursalComprobanteOriginal;
	}

	public String getNumeroComprobanteOriginal() {
		return numeroComprobanteOriginal;
	}

	public void setNumeroComprobanteOriginal(String numeroComprobanteOriginal) {
		this.numeroComprobanteOriginal = numeroComprobanteOriginal;
	}

	public Long getIdDocumentoCuentasCobranzaOriginal() {
		return idDocumentoCuentasCobranzaOriginal;
	}

	public void setIdDocumentoCuentasCobranzaOriginal(
			Long idDocumentoCuentasCobranzaOriginal) {
		this.idDocumentoCuentasCobranzaOriginal = idDocumentoCuentasCobranzaOriginal;
	}

	/**
	 * @return the sistemaOrigen
	 */
	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}

	/**
	 * @param sistemaOrigen the sistemaOrigen to set
	 */
	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}

	/**
	 * @return the fechaImputacion
	 */
	public Date getFechaImputacion() {
		return fechaImputacion;
	}

	/**
	 * @param fechaImputacion the fechaImputacion to set
	 */
	public void setFechaImputacion(Date fechaImputacion) {
		this.fechaImputacion = fechaImputacion;
	}
	
	
}
