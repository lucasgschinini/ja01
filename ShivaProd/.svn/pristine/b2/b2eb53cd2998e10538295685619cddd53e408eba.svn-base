package ar.com.telecom.shiva.presentacion.bean.paginacion.info;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionPaginadoCredito;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicCredito;

public class InfoPaginaMicCredito extends InfoPagina {

	private static final long serialVersionUID = 1L;

	private MicInformacionPaginadoCredito inicio;
	
	private MicInformacionPaginadoCredito fin;

	public void modificarInicio(MicCredito cred) {
		MicInformacionPaginadoCredito info = new MicInformacionPaginadoCredito();
		info.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED);
		this.inicio = info;
	}
	
	public void modificarFin(MicCredito cred) {
		MicInformacionPaginadoCredito info = new MicInformacionPaginadoCredito();
		info.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED);
		this.fin = info;
	}

	public MicInformacionPaginadoCredito getInicio() {
		return inicio;
	}

	public void setInicio(MicInformacionPaginadoCredito inicio) {
		this.inicio = inicio;
	}

	public MicInformacionPaginadoCredito getFin() {
		return fin;
	}

	public void setFin(MicInformacionPaginadoCredito fin) {
		this.fin = fin;
	}
		
}
