package ar.com.telecom.shiva.persistencia.modelo.simple;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.com.telecom.shiva.base.enumeradores.EstadoConsultaSaldoChequeRechazadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_LGJ_CHEQUE_RECHA_DET_DOCUM")
public class ShvLgjChequeRechazadoDetalleDocumentoSimplificado extends Modelo {
	private static final long serialVersionUID = 20170725L;

	@Id
	@Column(name="ID_CHEQUE_RECHAZADO_DOCUMENTO", updatable = false)
	private Long idChequeRechazadoDocumento;
	
	@Column(name="ID_CHEQUE_RECHAZADO")
	private Long idChequeRechazado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_ORIGEN")
	private SistemaEnum sistemaOrigen;

	@Column(name = "ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_COMPROBANTE", nullable=true)
	private TipoComprobanteEnum tipoComprobante;
	
	@Column(name="NUMERO_LEGAL")
	private String numeroLegal;
	
	@Column(name="NUMERO_REFERENCIA_MIC")
	private String numeroReferenciaMic;
	
	@Column(name="SALDO_TOTAL_DOCUMENTO")
	private BigDecimal saldoDocumento;

	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MONEDA", nullable=true)
	private MonedaEnum monedaDocumento;
	
	@Transient
	private EstadoConsultaSaldoChequeRechazadoEnum estado;

	public ShvLgjChequeRechazadoDetalleDocumentoSimplificado() {}

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
	 * @return the idChequeRechazado
	 */
	public Long getIdChequeRechazado() {
		return idChequeRechazado;
	}

	/**
	 * @param idChequeRechazado the idChequeRechazado to set
	 */
	public void setIdChequeRechazado(Long idChequeRechazado) {
		this.idChequeRechazado = idChequeRechazado;
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

	public BigDecimal getSaldoDocumento() {
		return saldoDocumento;
	}

	public void setSaldoDocumento(BigDecimal saldoDocumento) {
		this.saldoDocumento = saldoDocumento;
	}

	public EstadoConsultaSaldoChequeRechazadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoConsultaSaldoChequeRechazadoEnum estado) {
		this.estado = estado;
	}
}
