package ar.com.telecom.shiva.presentacion.bean.paginacion.info;

import ar.com.telecom.shiva.presentacion.bean.paginacion.InformacionPaginadoGenerico;

public class InfoPaginaIceCredito extends InfoPagina {
	private static final long serialVersionUID = 20170611L;

	private InformacionPaginadoGenerico<Long> inicio;
	
	private InformacionPaginadoGenerico<Long> fin;

	/**
	 * @return the inicio
	 */
	public InformacionPaginadoGenerico<Long> getInicio() {
		return inicio;
	}

	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(InformacionPaginadoGenerico<Long> inicio) {
		this.inicio = inicio;
	}

	/**
	 * @return the fin
	 */
	public InformacionPaginadoGenerico<Long> getFin() {
		return fin;
	}

	/**
	 * @param fin the fin to set
	 */
	public void setFin(InformacionPaginadoGenerico<Long> fin) {
		this.fin = fin;
	}

//	public void modificarInicio(CalipsoCredito cred) {
//		InformacionParaPaginadoCredito info = new InformacionParaPaginadoCredito();
//		info.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED)));
//		this.inicio = info;
//	}
//	
//	public void modificarFin(CalipsoCredito cred) {
//		InformacionParaPaginadoCredito info = new InformacionParaPaginadoCredito();
//		info.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED)));
//		this.fin = info;
//	}
	
}
