package ar.com.telecom.shiva.negocio.dto.cobros;

import java.util.Date;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;

public class CobMensajeriaTransaccionDto extends DTO {

	private static final long serialVersionUID = 1L;

	private Integer idTransaccionMensajeria;	

	private Integer idTransaccion;
	private Long idOperacion;
	private MensajeServicioEnum servicio;
	
	//private Date fechaCreacion; --> comento ya que utilizo el campo fechaAlta del DTO
	private Date fechaEnvio;
	private Date fechaRecepcion;
	
	private MensajeEstadoEnum estado;
	private String mensajeEnviado;
	private String respuestaRecibida;
	private Integer cantReintentos;
	
	private TipoProcesoEnum tipoImputacion;
	
	public Integer getCantReintentos() {
		return cantReintentos;
	}
	public void setCantReintentos(Integer cantReintentos) {
		this.cantReintentos = cantReintentos;
	}
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
	public Long getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
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
	public TipoProcesoEnum getTipoImputacion() {
		return tipoImputacion;
	}
	public void setTipoImputacion(TipoProcesoEnum tipoImputacion) {
		this.tipoImputacion = tipoImputacion;
	}
	
}
