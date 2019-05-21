package ar.com.telecom.shiva.base.ws.cliente.datos.salida;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.ResultadoInvocacion;

/**
 * @author u586743
 *
 */
public class SalidaSapVerificacionPartidasWS extends SalidaWS {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected List<ResultadoInvocacion> listaverificaciones = new ArrayList<ResultadoInvocacion>();
	
	protected ResultadoInvocacion resultadoInvocacion = new ResultadoInvocacion();
	/**
	 * @return the listaErroresFuncionales
	 */
	public List<ResultadoInvocacion> getListaverificaciones() {
		return listaverificaciones;
	}
	/**
	 * @param listaErroresFuncionales the listaErroresFuncionales to set
	 */
	public void setListaverificaciones(
			List<ResultadoInvocacion> listaErroresFuncionales) {
		this.listaverificaciones = listaErroresFuncionales;
	}
	/**
	 * @return the resultadoInvocacion
	 */
	public ResultadoInvocacion getResultadoInvocacion() {
		return resultadoInvocacion;
	}
	/**
	 * @param resultadoInvocacion the resultadoInvocacion to set
	 */
	public void setResultadoInvocacion(ResultadoInvocacion resultadoInvocacion) {
		this.resultadoInvocacion = resultadoInvocacion;
	}


}
