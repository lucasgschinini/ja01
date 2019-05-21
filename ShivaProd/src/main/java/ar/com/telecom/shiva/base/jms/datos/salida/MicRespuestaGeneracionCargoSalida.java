package ar.com.telecom.shiva.base.jms.datos.salida;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleInteresesGenerados;
import ar.com.telecom.shiva.base.jms.util.JmsCopiaTecnica;

@SuppressWarnings("serial")
public class MicRespuestaGeneracionCargoSalida 
	extends JMS {
	
	private JmsCopiaTecnica retorno = new JmsCopiaTecnica();
	private String idOperacionTransaccionRetorno;
	private String idOperacionTransaccion;
	private Integer idTransaccion;	
	private TipoInvocacionEnum tipoInvocacion;
	private MicResultado resultadoLLamadaServicio;
	private MicDetalleInteresesGenerados detalleInteresesGenerados;
  	
  	public String toString() {
		String str = retorno.toString()+ ",";
		str += "[idOperacionTransaccionRetorno:"+String.valueOf(idOperacionTransaccionRetorno)+"],";		
		str += "[idOperacionTransaccion:"+String.valueOf(idOperacionTransaccion)+"],";
		str += "[tipoInvocacion:"+tipoInvocacion.getValor()+"],";
		str += "[resultadoLLamadaServicio:"+resultadoLLamadaServicio.toString()+"],";
		str += "[detalleInteresesGenerados:"+detalleInteresesGenerados.toString()+"],";
		return str;
	}

  	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
  	
	public String getIdOperacionTransaccion() {
		return idOperacionTransaccion;
	}

	public void setIdOperacionTransaccion(String idOperacionTransaccion) {
		this.idOperacionTransaccion = idOperacionTransaccion;
	}

	public MicResultado getResultadoLLamadaServicio() {
		return resultadoLLamadaServicio;
	}

	public void setResultadoLLamadaServicio(MicResultado resultadoLLamadaServicio) {
		this.resultadoLLamadaServicio = resultadoLLamadaServicio;
	}

	public TipoInvocacionEnum getTipoInvocacion() {
		return tipoInvocacion;
	}

	public void setTipoInvocacion(TipoInvocacionEnum tipoInvocacion) {
		this.tipoInvocacion = tipoInvocacion;
	}

	public JmsCopiaTecnica getRetorno() {
		return retorno;
	}

	public void setRetorno(JmsCopiaTecnica retorno) {
		this.retorno = retorno;
	}

	public String getIdOperacionTransaccionRetorno() {
		return idOperacionTransaccionRetorno;
	}

	public void setIdOperacionTransaccionRetorno(
			String idOperacionTransaccionRetorno) {
		this.idOperacionTransaccionRetorno = idOperacionTransaccionRetorno;
	}

	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public MicDetalleInteresesGenerados getDetalleInteresesGenerados() {
		return detalleInteresesGenerados;
	}

	public void setDetalleInteresesGenerados(
			MicDetalleInteresesGenerados detalleInteresesGenerados) {
		this.detalleInteresesGenerados = detalleInteresesGenerados;
	}

}
