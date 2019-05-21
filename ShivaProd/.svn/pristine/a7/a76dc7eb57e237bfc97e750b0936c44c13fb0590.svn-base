package ar.com.telecom.shiva.presentacion.bean.paginacion.info;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.presentacion.bean.paginacion.InformacionPaginadoCreditoShiva;

public class InfoPaginaShivaCredito extends InfoPagina {

	private static final long serialVersionUID = 1L;

	private InformacionPaginadoCreditoShiva inicio;
	
	private InformacionPaginadoCreditoShiva fin;

	public void modificarInicio(VistaSoporteResultadoBusquedaValor cred) {
		InformacionPaginadoCreditoShiva info = new InformacionPaginadoCreditoShiva();
		info.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED);
		this.inicio = info;
	}
	
	public void modificarFin(VistaSoporteResultadoBusquedaValor cred) {
		InformacionPaginadoCreditoShiva info = new InformacionPaginadoCreditoShiva();
		info.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED);
		this.fin = info;
	}

	public InformacionPaginadoCreditoShiva getInicio() {
		return inicio;
	}

	public void setInicio(InformacionPaginadoCreditoShiva inicio) {
		this.inicio = inicio;
	}

	public InformacionPaginadoCreditoShiva getFin() {
		return fin;
	}

	public void setFin(InformacionPaginadoCreditoShiva fin) {
		this.fin = fin;
	}
		
}
