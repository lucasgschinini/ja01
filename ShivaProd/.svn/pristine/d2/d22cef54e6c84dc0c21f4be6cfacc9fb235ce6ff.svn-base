package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.math.BigDecimal;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.persistencia.modelo.ShvArcArchivo;

@Entity
@Table(name = "SHV_COB_DESCOBRO_COD_OPER_EXT")
public class ShvCobDescobroCodigoOperacionExternaSimplificado {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_DESC_COD_OPER_EXT")
	@SequenceGenerator(name="SEQ_SHV_COB_DESC_COD_OPER_EXT", sequenceName="SEQ_SHV_COB_DESC_COD_OPER_EXT", allocationSize = 1)
	@Column(name="ID_COB_DESCOBRO_COD_OPER_EXT")
	private Long idCobDescobroCodOperExt;

	@Column(name="ID_DESCOBRO")
	private Long idDescobro;

	@Column(name="CODIGO_OPERACION_EXTERNA")
	private String codigoOperacionExterna;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ARCHIVO", referencedColumnName="ID_ARCHIVO")
	private ShvArcArchivo archivo;

	@Column(name="SISTEMA")
	private String sistema;
	
	@Column(name="REFERENTE")
	private String referente;
	
	@Column(name="IMPORTE")
	private BigDecimal importe;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_IMPORTE")
	private MonedaEnum  monedaImporte;
	
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

	/**
	 * @return the idCobDescobroCodOperExt
	 */
	public Long getIdCobDescobroCodOperExt() {
		return idCobDescobroCodOperExt;
	}

	/**
	 * @param idCobDescobroCodOperExt the idCobDescobroCodOperExt to set
	 */
	public void setIdCobDescobroCodOperExt(Long idCobDescobroCodOperExt) {
		this.idCobDescobroCodOperExt = idCobDescobroCodOperExt;
	}

	/**
	 * @return the shvCobDescobro
	 */
	public Long getIdDescobro() {
		return idDescobro;
	}

	/**
	 * @param shvCobDescobro the shvCobDescobro to set
	 */
	public void setIdDescobro(Long idDescobro) {
		this.idDescobro = idDescobro;
	}

	/**
	 * @return the codigoOperacionExterna
	 */
	public String getCodigoOperacionExterna() {
		return codigoOperacionExterna;
	}

	/**
	 * @param codigoOperacionExterna the codigoOperacionExterna to set
	 */
	public void setCodigoOperacionExterna(String codigoOperacionExterna) {
		this.codigoOperacionExterna = codigoOperacionExterna;
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

	public MonedaEnum getMonedaImporte() {
		return monedaImporte;
	}

	public void setMonedaImporte(MonedaEnum monedaImporte) {
		this.monedaImporte = monedaImporte;
	}

	public ShvArcArchivo getArchivo() {
		return archivo;
	}

	public void setArchivo(ShvArcArchivo archivo) {
		this.archivo = archivo;
	}
}
