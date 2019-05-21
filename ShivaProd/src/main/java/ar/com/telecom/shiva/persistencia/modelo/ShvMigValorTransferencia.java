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
@Table(name = "SHV_MIG_VALOR_TRANSFERENCIA")
public class ShvMigValorTransferencia extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_MIG_VALOR_TRANSFERENCIA")
    @SequenceGenerator(name="SEQ_SHV_MIG_VALOR_TRANSFERENCIA", sequenceName="SEQ_SHV_MIG_VALOR_TRANSFERENCIA",allocationSize=1)
	@Column(name="ID")
	private Long idValorTransferencia;
	@Column(name="ID_SEGMENTO")
	private String idSegmento;
	@Column(name="ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	@Column(name="ID_ANALISTA")
	private String idAnalista;
	@Column(name="ID_COPROPIETARIO")
	private String idCopropietario;
	@Column(name="NUMERO_ACUERDO")
	private Long numeroAcuerdo;
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
	@Column(name="NUMERO_REFERENCIA")
	private Long numeroReferencia;
	@Column(name="ID_BANCO_ORIGEN")
	private String idBancoOrigen;
	@Column(name="FECHA_TRANSFERENCIA")
	private Date fechaTransferencia;
	/**
	 * @return the idValorTransferencia
	 */
	public Long getIdValorTransferencia() {
		return idValorTransferencia;
	}
	/**
	 * @param idValorTransferencia the idValorTransferencia to set
	 */
	public void setIdValorTransferencia(Long idValorTransferencia) {
		this.idValorTransferencia = idValorTransferencia;
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
	 * @return the idAnalista
	 */
	public String getIdAnalista() {
		return idAnalista;
	}
	/**
	 * @param idAnalista the idAnalista to set
	 */
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}
	/**
	 * @return the idCopropietario
	 */
	public String getIdCopropietario() {
		return idCopropietario;
	}
	/**
	 * @param idCopropietario the idCopropietario to set
	 */
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}
	/**
	 * @return the numeroAcuerdo
	 */
	public Long getNumeroAcuerdo() {
		return numeroAcuerdo;
	}
	/**
	 * @param numeroAcuerdo the numeroAcuerdo to set
	 */
	public void setNumeroAcuerdo(Long numeroAcuerdo) {
		this.numeroAcuerdo = numeroAcuerdo;
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
	 * @return the numeroReferencia
	 */
	public Long getNumeroReferencia() {
		return numeroReferencia;
	}
	/**
	 * @param numeroReferencia the numeroReferencia to set
	 */
	public void setNumeroReferencia(Long numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
	/**
	 * @return the idBancoOrigen
	 */
	public String getIdBancoOrigen() {
		return idBancoOrigen;
	}
	/**
	 * @param idBancoOrigen the idBancoOrigen to set
	 */
	public void setIdBancoOrigen(String idBancoOrigen) {
		this.idBancoOrigen = idBancoOrigen;
	}
	/**
	 * @return the fechaTransferencia
	 */
	public Date getFechaTransferencia() {
		return fechaTransferencia;
	}
	/**
	 * @param fechaTransferencia the fechaTransferencia to set
	 */
	public void setFechaTransferencia(Date fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}

	
		
}
