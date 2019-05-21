package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

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

import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;

@Entity
@Table(name="SHV_COB_MED_PAG_DOC_CAP_RESULT")
public class ShvCobMedioPagoDocumentoCapResultado extends Modelo {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="SEQ_SHV_COB_MP_DOC_CAP_RESULT", sequenceName="SEQ_SHV_COB_MP_DOC_CAP_RESULT", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_MP_DOC_CAP_RESULT")
	@Column(name = "ID_MEDIO_PAGO_DOC_CAP_RESULT")
	private Long idMedioPagoDocumentoCapResultado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MEDIO_PAGO_DOC_CAP", referencedColumnName = "ID_MEDIO_PAGO_DOC_CAP")
	private ShvCobMedioPagoDocumentoCap documentoCap;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_RESULTADO")		
	private TipoResultadoEnum tipo;

	@Column(name="CODIGO_RESULTADO")
	private String codigo;

    @Column(name="DESCRIPCION_RESULTADO")
	private String descripcion;
	
	@Column(name = "FECHA_RESULTADO") 
	private Date fecha;

	/**
	 * @return the idMedioPagoDocumentoCapResultado
	 */
	public Long getIdMedioPagoDocumentoCapResultado() {
		return idMedioPagoDocumentoCapResultado;
	}

	/**
	 * @param idMedioPagoDocumentoCapResultado the idMedioPagoDocumentoCapResultado to set
	 */
	public void setIdMedioPagoDocumentoCapResultado(
			Long idMedioPagoDocumentoCapResultado) {
		this.idMedioPagoDocumentoCapResultado = idMedioPagoDocumentoCapResultado;
	}

	/**
	 * @return the documentoCap
	 */
	public ShvCobMedioPagoDocumentoCap getDocumentoCap() {
		return documentoCap;
	}

	/**
	 * @param documentoCap the documentoCap to set
	 */
	public void setDocumentoCap(
			ShvCobMedioPagoDocumentoCap medioPagoDocumentoCap) {
		this.documentoCap = medioPagoDocumentoCap;
	}

	/**
	 * @return the tipo
	 */
	public TipoResultadoEnum getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoResultadoEnum tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}

