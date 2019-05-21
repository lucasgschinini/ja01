package ar.com.telecom.shiva.base.ws.cliente.datos.salida;

import java.util.List;

import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ice.IceCheques;

@SuppressWarnings("serial")
public class SalidaIceConsultaChequesWS extends SalidaWS {
	
	protected List<IceCheques>listaCheques;
	protected Long cantidadDeRegistrosTotales;
	protected Long cantidadRegistrosDevueltos;
	protected ResultadoProcesamiento resultadoProcesamiento;
	
	
	public SalidaIceConsultaChequesWS() {
		super();
	}
	
	
	public List<IceCheques> getListaCheques() {
		return listaCheques;
	}
	public void setListaCheques(List<IceCheques> listaCheques) {
		this.listaCheques = listaCheques;
	}
	public Long getCantidadDeRegistrosTotales() {
		return cantidadDeRegistrosTotales;
	}
	public void setCantidadDeRegistrosTotales(Long cantidadDeRegistrosTotales) {
		this.cantidadDeRegistrosTotales = cantidadDeRegistrosTotales;
	}
	public Long getCantidadRegistrosDevueltos() {
		return cantidadRegistrosDevueltos;
	}
	public void setCantidadRegistrosDevueltos(Long cantidadRegistrosDevueltos) {
		this.cantidadRegistrosDevueltos = cantidadRegistrosDevueltos;
	}
	public ResultadoProcesamiento getResultadoProcesamiento() {
		return resultadoProcesamiento;
	}
	public void setResultadoProcesamiento(
			ResultadoProcesamiento resultadoProcesamiento) {
		this.resultadoProcesamiento = resultadoProcesamiento;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SalidaIceConsultaChequesWS [listaCheques=" + listaCheques
				+ ", cantidadDeRegistrosTotales=" + cantidadDeRegistrosTotales
				+ ", cantidadRegistrosDevueltos=" + cantidadRegistrosDevueltos
				+ "]";
	}

	
}
