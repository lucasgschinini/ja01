package ar.com.telecom.shiva.presentacion.bean.paginacion.info;

import java.math.BigInteger;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.InformacionParaPaginadoDebito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoDebito;

public class InfoPaginaCalipsoDebito extends InfoPagina {

	private static final long serialVersionUID = 1L;

	private InformacionParaPaginadoDebito inicio;
	
	private InformacionParaPaginadoDebito fin;

	public void modificarInicio(CalipsoDebito deb) {
		InformacionParaPaginadoDebito info = new InformacionParaPaginadoDebito();
		info.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_DEB)));
		info.setUltimaFechaDocumento(deb.getFechaEmision());
		info.setUltimoClienteDocumento(new BigInteger(deb.getIdClienteLegado()));
		info.setUltimoIdDocCtasCob(deb.getIdDocCtasCob());
		this.inicio = info;
	}
	
	public void modificarFin(CalipsoDebito deb) {
		InformacionParaPaginadoDebito info = new InformacionParaPaginadoDebito();
		info.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_DEB)));
		info.setUltimaFechaDocumento(deb.getFechaEmision());
		info.setUltimoClienteDocumento(new BigInteger(deb.getIdClienteLegado()));
		info.setUltimoIdDocCtasCob(deb.getIdDocCtasCob());
		this.fin = info;
	}
	
	public InformacionParaPaginadoDebito getInicio() {
		return inicio;
	}

	public void setInicio(InformacionParaPaginadoDebito inicio) {
		this.inicio = inicio;
	}

	public InformacionParaPaginadoDebito getFin() {
		return fin;
	}

	public void setFin(InformacionParaPaginadoDebito fin) {
		this.fin = fin;
	}
	
}
