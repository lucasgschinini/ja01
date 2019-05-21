package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;

@Entity
@Table(name="SHV_COB_MED_PAG_DOC_CAP")
public class ShvCobMedioPagoDocumentoCap extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="SEQ_SHV_COB_MP_DOC_CAP", sequenceName="SEQ_SHV_COB_MP_DOC_CAP", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_MP_DOC_CAP")
	@Column(name = "ID_MEDIO_PAGO_DOC_CAP")
	private Long idMedioPagoDocumentoCap;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "documentoCap")
	private Set<ShvCobMedioPagoDocumentoCapDetalle> detalle = new HashSet<ShvCobMedioPagoDocumentoCapDetalle>(0);

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "documentoCap")
	private Set<ShvCobMedioPagoDocumentoCapResultado> resultado = new HashSet<ShvCobMedioPagoDocumentoCapResultado>(0);

	@Column(name="ID_DOCUMENTO")
	private String IdDocumento;

	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "ID_SOCIEDAD")
	private String idSociedad;
	
	@Column(name="ANIO_FISCAL")
	private Long anioFiscal;
	
	@Column(name="ID_CONTRADOCUMENTO")
	private String idContradocumento;
	
	@Column(name="FECHA_CREACION_CONTRADOCUMENTO")
	private Date fechaCreacionContradocumento;
	
	@Column(name = "ID_SOCIEDAD_CONTRADOCUMENTO")
	private String idSociedadContradocumento;
	
	@Column(name="ANIO_FISCAL_CONTRADOCUMENTO")
	private Long anioFiscalContradocumento;

	@Column(name = "USUARIO_CREACION_CONTRADOCUMEN")
	private String usuarioCreacionContradocumento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA")				
	private MonedaEnum moneda;
	
	@Column(name="IMPORTE") 			
	private  BigDecimal importe;

	
	@Column(name = "ID_PROVEEDOR")
	private String idProveedor;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_DOCUMENTO") 
	private TipoDocumentoCapEnum tipoDocumento;
	
	@Column(name="FECHA_VALOR_COMPENSACION")
	private Date fechaValorCompensacion;
	
	@Column(name="FECHA_DERIVACION_CAP")
	private Date fechaDerivacionCap;
	
	@Column(name="ID_COBRO")
	private Long IdCobro;

	@Column(name="ID_DESCOBRO")
	private Long idDescobro;

	@Column(name="ORDEN")
	private Long orden;

	/**
	 * @return the detalle
	 */
	public Set<ShvCobMedioPagoDocumentoCapDetalle> getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(
			Set<ShvCobMedioPagoDocumentoCapDetalle> listaDocumentoCapDetalle) {
		this.detalle = listaDocumentoCapDetalle;
	}

	/**
	 * @return the resultado
	 */
	public Set<ShvCobMedioPagoDocumentoCapResultado> getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(
			Set<ShvCobMedioPagoDocumentoCapResultado> listaDocumentoCapResultado) {
		this.resultado = listaDocumentoCapResultado;
	}

	/**
	 * @return the idMedioPagoDocumentoCap
	 */
	public Long getIdMedioPagoDocumentoCap() {
		return idMedioPagoDocumentoCap;
	}

	/**
	 * @param idMedioPagoDocumentoCap the idMedioPagoDocumentoCap to set
	 */
	public void setIdMedioPagoDocumentoCap(Long idMedioPagoDocumentoCap) {
		this.idMedioPagoDocumentoCap = idMedioPagoDocumentoCap;
	}

	/**
	 * @return the idDocumento
	 */
	public String getIdDocumento() {
		return IdDocumento;
	}

	/**
	 * @param idDocumento the idDocumento to set
	 */
	public void setIdDocumento(String idDocumento) {
		IdDocumento = idDocumento;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the idContradocumento
	 */
	public String getIdContradocumento() {
		return idContradocumento;
	}

	/**
	 * @param idContradocumento the idContradocumento to set
	 */
	public void setIdContradocumento(String idContradocumento) {
		this.idContradocumento = idContradocumento;
	}

	/**
	 * @return the fechaCreacionContradocumento
	 */
	public Date getFechaCreacionContradocumento() {
		return fechaCreacionContradocumento;
	}

	/**
	 * @param fechaCreacionContradocumento the fechaCreacionContradocumento to set
	 */
	public void setFechaCreacionContradocumento(Date fechaCreacionContradocumento) {
		this.fechaCreacionContradocumento = fechaCreacionContradocumento;
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
	 * @return the idProveedor
	 */
	public String getIdProveedor() {
		return idProveedor;
	}

	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	/**
	 * @return the tipoDocumento
	 */
	public TipoDocumentoCapEnum getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumentoCapEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the idCobro
	 */
	public Long getIdCobro() {
		return IdCobro;
	}

	/**
	 * @param idCobro the idCobro to set
	 */
	public void setIdCobro(Long idCobro) {
		IdCobro = idCobro;
	}

	/**
	 * @return the idSociedad
	 */
	public String getIdSociedad() {
		return idSociedad;
	}

	/**
	 * @param idSociedad the idSociedad to set
	 */
	public void setIdSociedad(String idSociedad) {
		this.idSociedad = idSociedad;
	}

	/**
	 * @return the usuarioCreacionContradocumento
	 */
	public String getUsuarioCreacionContradocumento() {
		return usuarioCreacionContradocumento;
	}

	/**
	 * @param usuarioCreacionContradocumento the usuarioCreacionContradocumento to set
	 */
	public void setUsuarioCreacionContradocumento(
			String usuarioCreacionContradocumento) {
		this.usuarioCreacionContradocumento = usuarioCreacionContradocumento;
	}

	public Long getAnioFiscal() {
		return anioFiscal;
	}

	public void setAnioFiscal(Long anioFiscal) {
		this.anioFiscal = anioFiscal;
	}

	public String getIdSociedadContradocumento() {
		return idSociedadContradocumento;
	}

	public void setIdSociedadContradocumento(String idSociedadContradocumento) {
		this.idSociedadContradocumento = idSociedadContradocumento;
	}

	public Long getAnioFiscalContradocumento() {
		return anioFiscalContradocumento;
	}

	public void setAnioFiscalContradocumento(Long anioFiscalContradocumento) {
		this.anioFiscalContradocumento = anioFiscalContradocumento;
	}

	public Date getFechaValorCompensacion() {
		return fechaValorCompensacion;
	}

	public void setFechaValorCompensacion(Date fechaValorCompensacion) {
		this.fechaValorCompensacion = fechaValorCompensacion;
	}

	public Date getFechaDerivacionCap() {
		return fechaDerivacionCap;
	}

	public void setFechaDerivacionCap(Date fechaDerivacionCap) {
		this.fechaDerivacionCap = fechaDerivacionCap;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	/**
	 * @return the idDescobro
	 */
	public Long getIdDescobro() {
		return idDescobro;
	}

	/**
	 * @param idDescobro the idDescobro to set
	 */
	public void setIdDescobro(Long idDescobro) {
		this.idDescobro = idDescobro;
	}

}

