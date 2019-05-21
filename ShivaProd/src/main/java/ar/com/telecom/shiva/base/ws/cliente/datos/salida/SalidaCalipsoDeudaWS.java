package ar.com.telecom.shiva.base.ws.cliente.datos.salida;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ControlPaginado;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoDebito;

@SuppressWarnings("serial")
public class SalidaCalipsoDeudaWS extends SalidaWS {
	
	protected List<CalipsoDebito> listaDebitos = new ArrayList<CalipsoDebito>();
	protected ControlPaginado controlPaginado;
	protected ResultadoProcesamiento resultadoProcesamiento;
	protected String idCobro;
	public SalidaCalipsoDeudaWS() {
		super();
	}
	
	public SalidaCalipsoDeudaWS(int totalRegistros) {
		super();
		this.controlPaginado = new ControlPaginado();
		this.controlPaginado.setCantidadRegistrosTotales(new BigInteger(String.valueOf(totalRegistros)));
		this.controlPaginado.setCantidadRegistrosRetornados(new BigInteger(String.valueOf(0)));
	}
	
	public List<CalipsoDebito> getListaDebitos() {
		return listaDebitos;
	}
	public void setListaDebitos(List<CalipsoDebito> listaDebitos) {
		this.listaDebitos = listaDebitos;
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

	public String getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(String idCobro) {
		this.idCobro = idCobro;
	}
	
}
