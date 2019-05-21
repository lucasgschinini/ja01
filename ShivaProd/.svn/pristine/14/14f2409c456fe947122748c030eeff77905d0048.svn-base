package ar.com.telecom.shiva.base.ws.cliente.datos.salida;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ControlPaginado;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoCredito;


@SuppressWarnings("serial")
public class SalidaCalipsoCreditoWS extends SalidaWS {
	
	protected List<CalipsoCredito> listaCreditos = new ArrayList<CalipsoCredito>();
	protected ControlPaginado controlPaginado;
	protected ResultadoProcesamiento resultadoProcesamiento;
	
	public SalidaCalipsoCreditoWS() {
		super();
	}
	
	public SalidaCalipsoCreditoWS(int totalRegistros) {
		super();
		this.controlPaginado = new ControlPaginado();
		this.controlPaginado.setCantidadRegistrosTotales(new BigInteger(String.valueOf(totalRegistros)));
		this.controlPaginado.setCantidadRegistrosRetornados(new BigInteger(String.valueOf(0)));
	}
	
	public List<CalipsoCredito> getListaCreditos() {
		return listaCreditos;
	}
	public void setListaCreditos(List<CalipsoCredito> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
	public ControlPaginado getControlPaginado() {
		return controlPaginado;
	}
	public void setControlPaginado(ControlPaginado controlPaginado) {
		this.controlPaginado = controlPaginado;
	}
	public ResultadoProcesamiento getResultadoProcesamiento() {
		return resultadoProcesamiento;
	}
	public void setResultadoProcesamiento(
			ResultadoProcesamiento resultadoProcesamiento) {
		this.resultadoProcesamiento = resultadoProcesamiento;
	}

}
