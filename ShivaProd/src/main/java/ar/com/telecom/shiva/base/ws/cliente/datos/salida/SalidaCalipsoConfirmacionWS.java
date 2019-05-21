package ar.com.telecom.shiva.base.ws.cliente.datos.salida;

import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;

@SuppressWarnings("serial")
public class SalidaCalipsoConfirmacionWS extends SalidaWS {
	
	protected String idOperacion;
    protected Resultado resultadoInvocacion;
    
	public String getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}
	public Resultado getResultadoInvocacion() {
		return resultadoInvocacion;
	}
	public void setResultadoInvocacion(Resultado resultadoInvocacion) {
		this.resultadoInvocacion = resultadoInvocacion;
	}
    
}
