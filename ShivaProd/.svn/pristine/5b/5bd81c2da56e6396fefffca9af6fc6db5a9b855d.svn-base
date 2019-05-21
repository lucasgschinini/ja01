package ar.com.telecom.shiva.persistencia.modelo;


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

import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;

@Entity
@Table(name = "SHV_COB_TRANSACCION_MSJ_DET")
public class ShvCobTransaccionMensajeriaDetalle extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_MSJ_TRANSACCION_MENS")
    @SequenceGenerator(name="SEQ_SHV_MSJ_TRANSACCION_MENS", sequenceName="SEQ_SHV_MSJ_TRANSACCION_MENS",allocationSize=1)
	@Column(name="ID_TRANSACCION_MENSAJERIA")	
	private Integer idTransaccionMensajeria;	

	@Column(name="ID_TRANSACCION")
	private Integer idTransaccion;
	
	@Column(name="ID_OPERACION")
	private Long idOperacion;
	
	@Column(name="FECHA_CREACION")		
	private Date fechaCreacion;
	
	@Column(name="FECHA_ENVIO")		
	private Date fechaEnvio;
	
	@Column(name="FECHA_RECEPCION")		
	private Date fechaRecepcion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SERVICIO")		
	private MensajeServicioEnum servicio;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")		
	private MensajeEstadoEnum estado;
	
	@Column(name="MENSAJE_ENVIADO")		
	private String mensajeEnviado;
	
	@Column(name="RESPUESTA_RECIBIDA")		
	private String respuestaRecibida;
	
	@Column(name="CANT_REINTENTOS")	
	private Integer cantReintentos;

	public Integer getIdTransaccionMensajeria() {
		return idTransaccionMensajeria;
	}

	public void setIdTransaccionMensajeria(Integer idTransaccionMensajeria) {
		this.idTransaccionMensajeria = idTransaccionMensajeria;
	}

	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public MensajeServicioEnum getServicio() {
		return servicio;
	}

	public void setServicio(MensajeServicioEnum servicio) {
		this.servicio = servicio;
	}

	public MensajeEstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(MensajeEstadoEnum estado) {
		this.estado = estado;
	}

	public String getMensajeEnviado() {
		return mensajeEnviado;
	}

	public void setMensajeEnviado(String mensajeEnviado) {
		this.mensajeEnviado = mensajeEnviado;
	}

	public String getRespuestaRecibida() {
		return respuestaRecibida;
	}

	public void setRespuestaRecibida(String respuestaRecibida) {
		this.respuestaRecibida = respuestaRecibida;
	}

	public Integer getCantReintentos() {
		return cantReintentos;
	}

	public void setCantReintentos(Integer cantReintentos) {
		this.cantReintentos = cantReintentos;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
}
