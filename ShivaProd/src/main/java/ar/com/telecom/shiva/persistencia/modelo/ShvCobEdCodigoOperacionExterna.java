package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoActivoInactivoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;

@Entity
@Table(name = "SHV_COB_ED_COD_OPER_EXT")
public class ShvCobEdCodigoOperacionExterna extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_ED_COD_OPER_EXT")
    @SequenceGenerator(name="SEQ_SHV_COB_ED_COD_OPER_EXT", sequenceName="SEQ_SHV_COB_ED_COD_OPER_EXT", allocationSize = 1)
	@Column(name="ID_COB_ED_COD_OPER_EXT")
	private Long idCobEdCodigoOperacionExterna;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO") 	
	private ShvCobEdCobro cobro;
	
	@Column(name="CODIGO_OPERACION_EXTERNA") 	
	private String codigoOperacionExterna;
	
	@OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name="ID_ARCHIVO", referencedColumnName="ID_ARCHIVO")
	private ShvArcArchivo archivo;

	@Column(name="SISTEMA")
	private String sistema;
	
	@Column(name="REFERENTE")
	private String referente;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_IMPORTE")
	private MonedaEnum  monedaImporte;
	
	@Column(name="IMPORTE")
	private BigDecimal importe;
	
	@Column(name="ID_TRANSACCION")	
	private Integer idTransaccion;
	
	@Column(name="NUMERO_TRANSACCION_FORMATEADO")	
	private String numeroTransaccionFormateado ;
	
	@Column(name="ID_TRATAMIENTO_DIFERENCIA")	
	private Integer idTratamientoDiferencia;
	
	@Column(name="ID_MEDIO_PAGO")	
	private Integer idMedioPago;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_IMPORTETOTAL_MEDIO_PAGO")
	private MonedaEnum  monedaImporteTotalMedioPago;

	@Column(name="IMPORTE_TOTAL_MEDIO_PAGO")
	private BigDecimal importeTotalMedioPago;
	
	@Column(name="NRO_DOCUMENTO")
	private String nroDocumento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO", nullable = false)
	private EstadoActivoInactivoEnum estado;
	
	@Column(name="GRUPO")
	private String grupo;
	
	@Column(name="REFERENTE_APROBADOR")
	private String nombreApellidoReferenteAprobador;
	
	@Column(name="ID_REFERENTE")
	private String idReferenteAprobador;
	
	@Column(name="FECHA_APROBACION")
	private Date fechaAprobacion;

	@Enumerated(EnumType.STRING)
	@Column(name="ES_NRO_TRANSACCION_FICTICIO")
	private SiNoEnum esNroTransaccionFicticio;
	
	public Long getIdCobEdCodigoOperacionExterna() {
		return idCobEdCodigoOperacionExterna;
	}

	public void setIdCobEdCodigoOperacionExterna(Long idCobEdCodigoOperacionExterna) {
		this.idCobEdCodigoOperacionExterna = idCobEdCodigoOperacionExterna;
	}
	
	public String getCodigoOperacionExterna() {
		return codigoOperacionExterna;
	}

	public void setCodigoOperacionExterna(String codigoOperacionExterna) {
		this.codigoOperacionExterna = codigoOperacionExterna;
	}

	public ShvCobEdCobro getCobro() {
		return cobro;
	}

	public void setCobro(ShvCobEdCobro cobro) {
		this.cobro = cobro;
	}

	public ShvArcArchivo getArchivo() {
		return archivo;
	}

	public void setArchivo(ShvArcArchivo archivo) {
		this.archivo = archivo;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getReferente() {
		return referente;
	}

	public void setReferente(String referente) {
		this.referente = referente;
	}

	public MonedaEnum getMonedaImporte() {
		return monedaImporte;
	}

	public void setMonedaImporte(MonedaEnum monedaImporte) {
		this.monedaImporte = monedaImporte;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getNumeroTransaccionFormateado() {
		return numeroTransaccionFormateado;
	}

	public void setNumeroTransaccionFormateado(String numeroTransaccionFormateado) {
		this.numeroTransaccionFormateado = numeroTransaccionFormateado;
	}

	public Integer getIdTratamientoDiferencia() {
		return idTratamientoDiferencia;
	}

	public void setIdTratamientoDiferencia(Integer idTratamientoDiferencia) {
		this.idTratamientoDiferencia = idTratamientoDiferencia;
	}

	public Integer getIdMedioPago() {
		return idMedioPago;
	}

	public void setIdMedioPago(Integer idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public MonedaEnum getMonedaImporteTotalMedioPago() {
		return monedaImporteTotalMedioPago;
	}

	public void setMonedaImporteTotalMedioPago(
			MonedaEnum monedaImporteTotalMedioPago) {
		this.monedaImporteTotalMedioPago = monedaImporteTotalMedioPago;
	}

	public BigDecimal getImporteTotalMedioPago() {
		return importeTotalMedioPago;
	}

	public void setImporteTotalMedioPago(BigDecimal importeTotalMedioPago) {
		this.importeTotalMedioPago = importeTotalMedioPago;
	}

	/**
	 * @return the factura
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * @param factura the factura to set
	 */
	public void setNroDocumento(String factura) {
		this.nroDocumento = factura;
	}

	/**
	 * @return the estado
	 */
	public EstadoActivoInactivoEnum getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoActivoInactivoEnum estado) {
		this.estado = estado;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getNombreApellidoReferenteAprobador() {
		return nombreApellidoReferenteAprobador;
	}

	public void setNombreApellidoReferenteAprobador(
			String nombreApellidoReferenteAprobador) {
		this.nombreApellidoReferenteAprobador = nombreApellidoReferenteAprobador;
	}

	public String getIdReferenteAprobador() {
		return idReferenteAprobador;
	}

	public void setIdReferenteAprobador(String idReferenteAprobador) {
		this.idReferenteAprobador = idReferenteAprobador;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	/**
	 * @return the esNroTransaccionFicticio
	 */
	public SiNoEnum getEsNroTransaccionFicticio() {
		return esNroTransaccionFicticio;
	}

	/**
	 * @param esNroTransaccionFicticio the esNroTransaccionFicticio to set
	 */
	public void setEsNroTransaccionFicticio(SiNoEnum esNroTransaccionFicticio) {
		this.esNroTransaccionFicticio = esNroTransaccionFicticio;
	}

}
