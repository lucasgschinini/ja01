package ar.com.telecom.shiva.base.jms.datos.salida;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.jms.util.JmsCopiaTecnica;

@SuppressWarnings("serial")
public class MicRespuestaRecepcionSalida 
	extends JMS {
	
	private JmsCopiaTecnica retorno = new JmsCopiaTecnica();
	private String idOperacionTransaccion;
	
	public String toString() {
		String strRetorno = retorno.toString();
		String strOtros = "[idOperacionTransaccion:"+String.valueOf(idOperacionTransaccion)+"]";
		
		return strRetorno + "," + strOtros;
	}
	
	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
	public JmsCopiaTecnica getRetorno() {
		return retorno;
	}
	public void setRetorno(JmsCopiaTecnica retorno) {
		this.retorno = retorno;
	}
	public String getIdOperacionTransaccion() {
		return idOperacionTransaccion;
	}
	public void setIdOperacionTransaccion(String idOperacionTransaccion) {
		this.idOperacionTransaccion = idOperacionTransaccion;
	}
}
