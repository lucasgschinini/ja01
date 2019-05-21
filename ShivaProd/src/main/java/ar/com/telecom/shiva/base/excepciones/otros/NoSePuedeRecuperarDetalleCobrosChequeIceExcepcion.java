package ar.com.telecom.shiva.base.excepciones.otros;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public class NoSePuedeRecuperarDetalleCobrosChequeIceExcepcion extends NegocioExcepcion {
	
	private static final long serialVersionUID = 1L;
	
	public NoSePuedeRecuperarDetalleCobrosChequeIceExcepcion() {
		super();
	}
	
	public NoSePuedeRecuperarDetalleCobrosChequeIceExcepcion(String mensaje) {
		super(mensaje);
	}
}
