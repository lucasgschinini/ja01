package ar.com.telecom.shiva.base.jms.datos.entrada;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;

@SuppressWarnings("serial")
public class MicRespuestaRecepcionEntrada 
	extends JMS {
	
	//Para guardar los mensajes
	private MensajeServicioEnum servicio;
	private Integer idTransaccion;
	private Long idOperacion;
	private String mensajeRecibido;
	
	//Para envio de datos
	private String idOperacionTransaccion;
	private MicResultado resultado;
	
	public String toString() {
		String str = "[idOperacionTransaccion:"+String.valueOf(idOperacionTransaccion)+"],"
				+ "[resultado:"+resultado.toString()+"]";
		return str;
	}
	
	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
	public MicResultado getResultado() {
		return resultado;
	}
	public void setResultado(MicResultado resultado) {
		this.resultado = resultado;
	}
	public String getIdOperacionTransaccion() {
		return idOperacionTransaccion;
	}
	public void setIdOperacionTransaccion(String idOperacionTransaccion) {
		this.idOperacionTransaccion = idOperacionTransaccion;
	}

	public String getMensajeRecibido() {
		return mensajeRecibido;
	}

	public void setMensajeRecibido(String mensajeRecibido) {
		this.mensajeRecibido = mensajeRecibido;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
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
	
}
