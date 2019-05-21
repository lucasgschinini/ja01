package ar.com.telecom.shiva.base.jms.datos.salida;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.jms.util.JmsRetorno;

@SuppressWarnings("serial")
public class FacConsultaClienteSalida 
	extends JMS {
	
	private JmsRetorno retorno = new JmsRetorno();
	private Long numeroCliente;
	
  	public String toString() {
		String str = retorno.toString()+ ",";
		str += "[numeroCliente:"+String.valueOf(numeroCliente)+"],";		
		
		return str;
	}

  	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
  	
	public JmsRetorno getRetorno() {
		return retorno;
	}

	public void setRetorno(JmsRetorno retorno) {
		this.retorno = retorno;
	}

	public Long getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(Long numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	
}
