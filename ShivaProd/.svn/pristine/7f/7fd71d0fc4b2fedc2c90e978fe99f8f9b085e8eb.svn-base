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

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;

@Entity
@Table(name = "SHV_LGJ_CHEQUE_RECHA_DET_DOCUM")
public class ShvLgjChequeRechazadoDetalleDocumento extends Modelo {
	private static final long serialVersionUID = 20170724L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_LGJ_CH_RECHA_DET_DOCUM")
	@SequenceGenerator(name = "SEQ_SHV_LGJ_CH_RECHA_DET_DOCUM", sequenceName = "SEQ_SHV_LGJ_CH_RECHA_DET_DOCUM", allocationSize = 1)
	@Column(name="ID_CHEQUE_RECHAZADO_DOCUMENTO", updatable = false)
	private Long idChequeRechazadoDocumento;
	
	@Column(name = "ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_COMPROBANTE", nullable=true)
	private TipoComprobanteEnum tipoComprobante;

	@Column(name="NUMERO_LEGAL")
	private String numeroLegal;

	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_ORIGEN")
	private SistemaEnum sistemaOrigen;

	@Column(name="NUMERO_REFERENCIA_MIC")
	private String numeroReferenciaMic;
	
	
	@Column(name="SALDO_TOTAL_DOCUMENTO")
	private BigDecimal importeBono;

	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_CHEQUE_RECHAZADO", referencedColumnName="ID_CHEQUE_RECHAZADO") 	
	private ShvLgjChequeRechazado chequeRechazado; 

	@Enumerated(EnumType.STRING)
	@Column(name = "MONEDA", nullable=true)
	private MonedaEnum monedaDocumento;

	
	public ShvLgjChequeRechazadoDetalleDocumento() {}


	/**
	 * @return the idChequeRechazadoDocumento
	 */
	public Long getIdChequeRechazadoDocumento() {
		return idChequeRechazadoDocumento;
	}


	/**
	 * @param idChequeRechazadoDocumento the idChequeRechazadoDocumento to set
	 */
	public void setIdChequeRechazadoDocumento(Long idChequeRechazadoDocumento) {
		this.idChequeRechazadoDocumento = idChequeRechazadoDocumento;
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
	 * @return the tipoComprobante
	 */
	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}


	/**
	 * @param tipoComprobante the tipoComprobante to set
	 */
	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	/**
	 * @return the numeroLegal
	 */
	public String getNumeroLegal() {
		return numeroLegal;
	}


	/**
	 * @param numeroLegal the numeroLegal to set
	 */
	public void setNumeroLegal(String numeroLegal) {
		this.numeroLegal = numeroLegal;
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
	 * @return the numeroReferenciaMic
	 */
	public String getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}


	/**
	 * @param numeroReferenciaMic the numeroReferenciaMic to set
	 */
	public void setNumeroReferenciaMic(String numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}

	/**
	 * @return the importeBono
	 */
	public BigDecimal getImporteBono() {
		return importeBono;
	}


	/**
	 * @param importeBono the importeBono to set
	 */
	public void setImporteBono(BigDecimal importeBono) {
		this.importeBono = importeBono;
	}


	/**
	 * @return the fechaActualizacion
	 */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}


	/**
	 * @param fechaActualizacion the fechaActualizacion to set
	 */
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	/**
	 * @return the chequeRechazado
	 */
	public ShvLgjChequeRechazado getChequeRechazado() {
		return chequeRechazado;
	}


	/**
	 * @param chequeRechazado the chequeRechazado to set
	 */
	public void setChequeRechazado(ShvLgjChequeRechazado chequeRechazado) {
		this.chequeRechazado = chequeRechazado;
	}


	/**
	 * @return the monedaDocumento
	 */
	public MonedaEnum getMonedaDocumento() {
		return monedaDocumento;
	}


	/**
	 * @param monedaDocumento the monedaDocumento to set
	 */
	public void setMonedaDocumento(MonedaEnum monedaDocumento) {
		this.monedaDocumento = monedaDocumento;
	}

	
	
}
