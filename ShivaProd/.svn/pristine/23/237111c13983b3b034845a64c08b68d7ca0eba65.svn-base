package ar.com.telecom.shiva.presentacion.bean.paginacion.info;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionPaginadoDebito;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDebito;

public class InfoPaginaMicDebito extends InfoPagina {

	private static final long serialVersionUID = 1L;

	private MicInformacionPaginadoDebito inicio;
	
	private MicInformacionPaginadoDebito fin;

	public void modificarInicio(MicDebito deb) {
		MicInformacionPaginadoDebito info = new MicInformacionPaginadoDebito();
		info.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_DEB);
		this.inicio = info;
	}
	
	public void modificarFin(MicDebito deb) {
		MicInformacionPaginadoDebito info = new MicInformacionPaginadoDebito();
		info.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_DEB);
		this.fin = info;
	}

	public MicInformacionPaginadoDebito getInicio() {
		return inicio;
	}

	public void setInicio(MicInformacionPaginadoDebito inicio) {
		this.inicio = inicio;
	}

	public MicInformacionPaginadoDebito getFin() {
		return fin;
	}

	public void setFin(MicInformacionPaginadoDebito fin) {
		this.fin = fin;
	}
		
}
