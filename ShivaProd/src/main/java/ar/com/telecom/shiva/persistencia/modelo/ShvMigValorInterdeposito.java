package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_MIG_VALOR_INTERDEPOSITO")
public class ShvMigValorInterdeposito extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_MIG_VALOR_INTERDEPOSITO")
    @SequenceGenerator(name="SEQ_SHV_MIG_VALOR_INTERDEPOSITO", sequenceName="SEQ_SHV_MIG_VALOR_INTERDEPOSITO",allocationSize=1)
	@Column(name="ID")
	private Long idValorInterdeposito;
	@Column(name="ID_SEGMENTO")
	private String idSegmento;
	@Column(name="ID_BANCO_DEPOSITO")
	private String idBancoDeposito;
	@Column(name="IMPORTE")
	private BigDecimal importe;
	@Column(name="SALDO_DISPONIBLE")
	private BigDecimal saldoDisponible;
	@Column(name="OBSERVACIONES")
	private String observaciones;
	@Column(name="NUMERO_PARTIDA_CONTABLE")
	private Long numeroPartidaContable;
	@Column(name="OPERACION_ASOCIADA")
	private Long operacionAsociada;
	@Column(name="ID_MOTIVO")
	private Long idMotivo;
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;
	@Column(name="USUARIO_AUTORIZACION")
	private String usuarioAutorizacion;
	@Column(name="USUARIO_EJECUTIVO")
	private String usuarioEjecutivo;
	@Column(name="USUARIO_ASISTENTE")
	private String usuarioAsistente;
	@Column(name="ID_ORIGEN")
	private Long idOrigen;
	@Column(name="NUMERO_INTERDEPOSITO")
	private Long numeroInterdeposito;
	@Column(name="CODIGO_ORGANISMO")
	private String codigoOrganismo;
	@Column(name="FECHA_INTERDEPOSITO")
	private Date fechaInterdeposito;
	/**
	 * @return the idValorInterdeposito
	 */
	public Long getIdValorInterdeposito() {
		return idValorInterdeposito;
	}
	/**
	 * @param idValorInterdeposito the idValorInterdeposito to set
	 */
	public void setIdValorInterdeposito(Long idValorInterdeposito) {
		this.idValorInterdeposito = idValorInterdeposito;
	}
	/**
	 * @return the idSegmento
	 */
	public String getIdSegmento() {
		return idSegmento;
	}
	/**
	 * @param idSegmento the idSegmento to set
	 */
	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}
	/**
	 * @return the idBancoDeposito
	 */
	public String getIdBancoDeposito() {
		return idBancoDeposito;
	}
	/**
	 * @param idBancoDeposito the idBancoDeposito to set
	 */
	public void setIdBancoDeposito(String idBancoDeposito) {
		this.idBancoDeposito = idBancoDeposito;
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
	 * @return the saldoDisponible
	 */
	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}
	/**
	 * @param saldoDisponible the saldoDisponible to set
	 */
	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the numeroPartidaContable
	 */
	public Long getNumeroPartidaContable() {
		return numeroPartidaContable;
	}
	/**
	 * @param numeroPartidaContable the numeroPartidaContable to set
	 */
	public void setNumeroPartidaContable(Long numeroPartidaContable) {
		this.numeroPartidaContable = numeroPartidaContable;
	}
	/**
	 * @return the operacionAsociada
	 */
	public Long getOperacionAsociada() {
		return operacionAsociada;
	}
	/**
	 * @param operacionAsociada the operacionAsociada to set
	 */
	public void setOperacionAsociada(Long operacionAsociada) {
		this.operacionAsociada = operacionAsociada;
	}
	/**
	 * @return the idMotivo
	 */
	public Long getIdMotivo() {
		return idMotivo;
	}
	/**
	 * @param idMotivo the idMotivo to set
	 */
	public void setIdMotivo(Long idMotivo) {
		this.idMotivo = idMotivo;
	}
	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}
	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	/**
	 * @return the usuarioAutorizacion
	 */
	public String getUsuarioAutorizacion() {
		return usuarioAutorizacion;
	}
	/**
	 * @param usuarioAutorizacion the usuarioAutorizacion to set
	 */
	public void setUsuarioAutorizacion(String usuarioAutorizacion) {
		this.usuarioAutorizacion = usuarioAutorizacion;
	}
	/**
	 * @return the usuarioEjecutivo
	 */
	public String getUsuarioEjecutivo() {
		return usuarioEjecutivo;
	}
	/**
	 * @param usuarioEjecutivo the usuarioEjecutivo to set
	 */
	public void setUsuarioEjecutivo(String usuarioEjecutivo) {
		this.usuarioEjecutivo = usuarioEjecutivo;
	}
	/**
	 * @return the usuarioAsistente
	 */
	public String getUsuarioAsistente() {
		return usuarioAsistente;
	}
	/**
	 * @param usuarioAsistente the usuarioAsistente to set
	 */
	public void setUsuarioAsistente(String usuarioAsistente) {
		this.usuarioAsistente = usuarioAsistente;
	}
	/**
	 * @return the idOrigen
	 */
	public Long getIdOrigen() {
		return idOrigen;
	}
	/**
	 * @param idOrigen the idOrigen to set
	 */
	public void setIdOrigen(Long idOrigen) {
		this.idOrigen = idOrigen;
	}
	/**
	 * @return the numeroInterdeposito
	 */
	public Long getNumeroInterdeposito() {
		return numeroInterdeposito;
	}
	/**
	 * @param numeroInterdeposito the numeroInterdeposito to set
	 */
	public void setNumeroInterdeposito(Long numeroInterdeposito) {
		this.numeroInterdeposito = numeroInterdeposito;
	}
	/**
	 * @return the codigoOrganismo
	 */
	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}
	/**
	 * @param codigoOrganismo the codigoOrganismo to set
	 */
	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}
	/**
	 * @return the fechaInterdeposito
	 */
	public Date getFechaInterdeposito() {
		return fechaInterdeposito;
	}
	/**
	 * @param fechaInterdeposito the fechaInterdeposito to set
	 */
	public void setFechaInterdeposito(Date fechaInterdeposito) {
		this.fechaInterdeposito = fechaInterdeposito;
	}

		
}
