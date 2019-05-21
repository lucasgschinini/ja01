package ar.com.telecom.shiva.persistencia.modelo;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;


/**
 * @author u578936 M.A.Uehara
 *
 */
@Entity
@Table(name = "SHV_COB_DESCOBRO_COD_OPER_EXT")
public class ShvCobDescobroCodigoOperacionExterna extends Modelo {
	private static final long serialVersionUID = 120171017L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_DESC_COD_OPER_EXT")
	@SequenceGenerator(name="SEQ_SHV_COB_DESC_COD_OPER_EXT", sequenceName="SEQ_SHV_COB_DESC_COD_OPER_EXT", allocationSize = 1)
	@Column(name="ID_COB_DESCOBRO_COD_OPER_EXT")
	private Long idCobDescobroCodOperExt;

	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="ID_DESCOBRO", referencedColumnName="ID_DESCOBRO")
	private ShvCobDescobro descobro;

	@Column(name="CODIGO_OPERACION_EXTERNA")
	private String codigoOperacionExterna;
	
	@OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name="ID_ARCHIVO", referencedColumnName="ID_ARCHIVO")
	private ShvArcArchivo archivo;
	
	@Column(name="SISTEMA")
	private String sistema;
	
	@Column(name="REFERENTE")
	private String referente;
	
	@Column(name="NUMERO_TRANSACCION_FORMATEADO")
	private String numeroTransaccion;
	
	@Column(name="IMPORTE")
	private BigDecimal importe;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_IMPORTE")
	private MonedaEnum  monedaImporte;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_IMPORTETOTAL_MEDIO_PAGO")
	private MonedaEnum  monedaImporteTotalMedioPago;

	@Column(name="IMPORTE_TOTAL_MEDIO_PAGO")
	private BigDecimal importeTotalMedioPago;

	

	public ShvCobDescobroCodigoOperacionExterna() {
	}

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
	public ShvCobDescobro getDescobro() {
		return descobro;
	}

	/**
	 * @param shvCobDescobro the shvCobDescobro to set
	 */
	public void setDescobro(ShvCobDescobro shvCobDescobro) {
		this.descobro = shvCobDescobro;
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

	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public MonedaEnum getMonedaImporte() {
		return monedaImporte;
	}

	public void setMonedaImporte(MonedaEnum monedaImporte) {
		this.monedaImporte = monedaImporte;
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
	
	
}
