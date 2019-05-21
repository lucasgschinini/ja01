package ar.com.telecom.shiva.presentacion.bean.paginacion.info;

import java.math.BigInteger;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.InformacionParaPaginadoCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoCredito;

public class InfoPaginaCalipsoCredito extends InfoPagina {

	private static final long serialVersionUID = 1L;

	private InformacionParaPaginadoCredito inicio;
	
	private InformacionParaPaginadoCredito fin;

	public void modificarInicio(CalipsoCredito cred) {
		InformacionParaPaginadoCredito info = new InformacionParaPaginadoCredito();
		info.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED)));
		this.inicio = info;
	}
	
	public void modificarFin(CalipsoCredito cred) {
		InformacionParaPaginadoCredito info = new InformacionParaPaginadoCredito();
		info.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED)));
		this.fin = info;
	}
	
	public InformacionParaPaginadoCredito getInicio() {
		return inicio;
	}

	public void setInicio(InformacionParaPaginadoCredito inicio) {
		this.inicio = inicio;
	}

	public InformacionParaPaginadoCredito getFin() {
		return fin;
	}

	public void setFin(InformacionParaPaginadoCredito fin) {
		this.fin = fin;
	}
	
}
