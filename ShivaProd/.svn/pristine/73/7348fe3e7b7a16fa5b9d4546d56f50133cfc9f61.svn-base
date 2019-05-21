package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.ConceptoDocumentoScardEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoGeneradoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_COB_DOCUMENTO")
public class ShvCobDocumentoSimplificado extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_COB_DOCUMENTO")
	@SequenceGenerator(name = "SEQ_SHV_COB_DOCUMENTO", sequenceName = "SEQ_SHV_COB_DOCUMENTO", allocationSize=1)
	@Column(name = "ID_DOCUMENTO", unique = true, nullable = false)
	private Long idDocumento;

	@Column(name = "ID_OPERACION", nullable = false)
	private Long idOperacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_OPERACION", nullable = false)
	private TipoOperacionEnum tipoOperacion;
	
	@Column(name = "DETALLE_DOCUMENTO_XML", nullable = false)
	private String detalleDocumentoXml;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CONCEPTO", nullable = false)
	private ConceptoDocumentoScardEnum concepto;

	@Column(name = "ESTADO", nullable = false)
	private String estado;
	
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Column(name = "FECHA_PROCESAMIENTO", nullable = false)
	private Date fechaProcesamiento;

	@Column(name = "NUMERO_RECIBO", nullable = false)
	private String numeroRecibo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_DOCUMENTO", nullable = false)
	private TipoDocumentoGeneradoEnum tipoDocumento;
	
	@Column(name = "ID_CLIENTE_LEGADO", nullable = false)
	private Long idClienteLegado;

	/**
	 * @return the idDocumento
	 */
	public Long getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento the idDocumento to set
	 */
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return the tipoDocumento
	 */
	public TipoOperacionEnum getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoOperacion(TipoOperacionEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * @return the detalleDocumentoXml
	 */
	public String getDetalleDocumentoXml() {
		return detalleDocumentoXml;
	}

	/**
	 * @param detalleDocumentoXml the detalleDocumentoXml to set
	 */
	public void setDetalleDocumentoXml(String detalleDocumentoXml) {
		this.detalleDocumentoXml = detalleDocumentoXml;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * @return the fechaProcesamiento
	 */
	public Date getFechaProcesamiento() {
		return fechaProcesamiento;
	}

	/**
	 * @param fechaProcesamiento the fechaProcesamiento to set
	 */
	public void setFechaProcesamiento(Date fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}

	public String getNumeroRecibo() {
		return numeroRecibo;
	}

	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	public TipoDocumentoGeneradoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoGeneradoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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

	/**
	 * 
	 * @return
	 */
	public Long getIdOperacion() {
		return idOperacion;
	}

	/**
	 * 
	 * @param idOperacion
	 */
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
	
	/**
	 * @return the concepto
	 */
	public ConceptoDocumentoScardEnum getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(ConceptoDocumentoScardEnum concepto) {
		this.concepto = concepto;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getClaveUnica() {
		String claveUnica = "";
		
		claveUnica += this.getIdOperacion();
		claveUnica += "|";
		claveUnica += this.getIdClienteLegado();
		claveUnica += "|";
		claveUnica += this.getTipoDocumento().name();
		claveUnica += "|";
		claveUnica += this.getConcepto().name();
		
		return claveUnica;
	}
	
	/**
	 * Para comparar
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (!(obj instanceof Modelo)) {
			return false;
		}
		
		return ((ShvCobDocumentoSimplificado) obj).getClaveUnica().equals(this.getClaveUnica());
	}
}
