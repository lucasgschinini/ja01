package ar.com.telecom.shiva.base.ws.cliente.datos.salida;

import java.util.HashMap;
import java.util.Map;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.siebel.RespuestaClienteCRM;

public class SalidaSiebelConsultarClienteWS {
	
	protected Map<String, RespuestaClienteCRM> listaClientes = new HashMap<String, RespuestaClienteCRM>();
	
	/**
	 * Devuelve el primer cliente si son varios
	 */
	public RespuestaClienteCRM getCliente() {
		if (Validaciones.isMapNotEmpty(listaClientes)) {
			return listaClientes.values().iterator().next();
		}
		return null;
	}

	public Map<String, RespuestaClienteCRM> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(Map<String, RespuestaClienteCRM> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	
}
