package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import java.io.Serializable;

public class ConsultaValoresDisponiblesEntrada extends Object implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoCliente;
	private String tipoMedioPago;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(String tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}
	
		
}

