package ar.com.telecom.shiva.presentacion.bean.paginacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaIceCredito;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaShivaCredito;

public class ChequeRechazadoControlPaginacion extends ControlPaginacion implements Serializable {
	private static final long serialVersionUID = 20170531L;

	private List<InfoPaginaShivaCredito> chequeShivaInfoPagina = new ArrayList<InfoPaginaShivaCredito>();
	private List<InfoPaginaIceCredito> chequeIceinfoPagina = new ArrayList<InfoPaginaIceCredito>();

	private long cantidadChequeShiva = 0l;
	private long cantidadChequeIce = 0l;
	

	private InformacionPaginadoGenerico<Long> inicioIce;
	private InformacionPaginadoCreditoShiva inicioShiva;
	
	private Filtro filtro = null;
	
	
	//private EntradaCalipsoCreditoWS entradaCalipso = new EntradaCalipsoCreditoWS();
	private VistaSoporteBusquedaValoresFiltro entradaShiva = new VistaSoporteBusquedaValoresFiltro();
	//private InformacionPaginadoGenerico<String> entradaIce;

	public ChequeRechazadoControlPaginacion() {
		this.cantPorPagina = Constantes.CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO;
	}

	/**
	 * @return the chequeShivaInfoPagina
	 */
	public List<InfoPaginaShivaCredito> getChequeShivaInfoPagina() {
		return chequeShivaInfoPagina;
	}
	/**
	 * @param chequeShivaInfoPagina the chequeShivaInfoPagina to set
	 */
	public void setChequeShivaInfoPagina(List<InfoPaginaShivaCredito> chequeShivaInfoPagina) {
		this.chequeShivaInfoPagina = chequeShivaInfoPagina;
	}
	/**
	 * @return the chequeIceinfoPagina
	 */
	public List<InfoPaginaIceCredito> getChequeIceinfoPagina() {
		return chequeIceinfoPagina;
	}
	/**
	 * @param chequeIceinfoPagina the chequeIceinfoPagina to set
	 */
	public void setChequeIceinfoPagina(List<InfoPaginaIceCredito> chequeIceinfoPagina) {
		this.chequeIceinfoPagina = chequeIceinfoPagina;
	}
	/**
	 * @return the cantidadChequeShiva
	 */
	public long getCantidadChequeShiva() {
		return cantidadChequeShiva;
	}
	/**
	 * @param cantidadChequeShiva the cantidadChequeShiva to set
	 */
	public void setCantidadChequeShiva(long cantidadChequeShiva) {
		this.cantidadChequeShiva = cantidadChequeShiva;
	}
	/**
	 * @return the cantidadChequeIce
	 */
	public long getCantidadChequeIce() {
		return cantidadChequeIce;
	}
	/**
	 * @param cantidadChequeIce the cantidadChequeIce to set
	 */
	public void setCantidadChequeIce(long cantidadChequeIce) {
		this.cantidadChequeIce = cantidadChequeIce;
	}
	
	/**
	 * Actulizar el punto de incio de las consultas a los servios de datos
	 * Similiar a setPuntoDeInicio de CobroOnline paginado
	 * @param accion
	 * @return
	 */
	public void actualizarPuntoDeInicio(PaginadorAccion accion) {
		this.entradaShiva.setInformacionPaginadoCredito(this.inicioShiva);
		
		
		if (PaginadorAccion.BUSCAR.equals(accion)) {
			InformacionPaginadoGenerico<Long> infoPagIce = new InformacionPaginadoGenerico<Long>();
			infoPagIce.setCantidadRegistrosARetornar(Constantes.CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO);
			inicioIce = infoPagIce;

			InformacionPaginadoCreditoShiva infoPagShiva = new InformacionPaginadoCreditoShiva();
			infoPagShiva.setCantidadRegistrosARetornar(Constantes.CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO);
			inicioShiva = infoPagShiva;
		} else if (PaginadorAccion.ANTERIOR.equals(accion)) {
			this.paginaActual--;
			if (this.paginaActual > 1) {
				inicioIce = this.chequeIceinfoPagina.get(this.paginaActual - 2).getFin();
				inicioShiva = this.chequeShivaInfoPagina.get(this.paginaActual - 2).getFin();
			} else {
				InformacionPaginadoCreditoShiva infoPagShiva = new InformacionPaginadoCreditoShiva();
				infoPagShiva.setCantidadRegistrosARetornar(Constantes.CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO);
				inicioShiva = infoPagShiva;

				InformacionPaginadoGenerico<Long> infoPagIce = new InformacionPaginadoGenerico<Long>();
				infoPagIce.setCantidadRegistrosARetornar(Constantes.CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO);
				inicioIce = infoPagIce;
				
			}
		} else if (PaginadorAccion.SIGUIENTE.equals(accion)) {
//			entradaCalipso.setInformacionParaPaginado(this.chequeIceinfoPagina.get(this.paginaActual - 1).getFin());
			inicioShiva = this.chequeShivaInfoPagina.get(this.paginaActual - 1).getFin();
			inicioIce = this.chequeIceinfoPagina.get(this.paginaActual - 1).getFin();
			
			if (paginaActual < this.totalPaginas) {
				this.paginaActual++;
			}
		}
	
		// Elimino las paginas que quedan mas alla de la cota superiot
		if (PaginadorAccion.ANTERIOR.equals(accion)) {
			this.chequeShivaInfoPagina = new ArrayList<InfoPaginaShivaCredito>(this.chequeShivaInfoPagina.subList(0, this.paginaActual - 1)); 
			this.chequeIceinfoPagina = new ArrayList<InfoPaginaIceCredito>(this.chequeIceinfoPagina.subList(0, this.paginaActual - 1)); 
		}
	}
	public InformacionPaginadoCreditoShiva getShivaActualInfoPagina() {
		if (this.chequeShivaInfoPagina.size() < 1) {
			return null;
		}
		return this.chequeShivaInfoPagina.get(this.paginaActual - 1).getFin();
	}
	public InformacionPaginadoGenerico<Long> getShivaActualInfoPaginaIce() {
		if (this.chequeIceinfoPagina.size() < 1) {
			return null;
		}
		return this.chequeIceinfoPagina.get(this.paginaActual - 1).getFin();
	}
	/**
	 * @return the filtro
	 */
	public Filtro getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}
	public long getCantidadTotal() {
		return cantidadChequeShiva + cantidadChequeIce;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChequeRechazadoControlPaginacion [chequeShivaInfoPagina="
				+ chequeShivaInfoPagina.size() + ", cantidadChequeShiva="
				+ cantidadChequeShiva + ", cantidadChequeIce="
				+ cantidadChequeIce + ", paginaActual=" + paginaActual
				+ ", totalPaginas=" + totalPaginas + ", cantPorPagina="
				+ cantPorPagina + ", totalRegistros=" + totalRegistros
				+ ", cantRegistrosMostrados=" + cantRegistrosMostrados + "]";
	}

	/**
	 * @return the inicioCalipso
	 */
	public InformacionPaginadoGenerico<Long> getInicioIce() {
		return inicioIce;
	}

	/**
	 * @param inicioCalipso the inicioCalipso to set
	 */
	public void setInicioIce(InformacionPaginadoGenerico<Long> inicioIce) {
		this.inicioIce = inicioIce;
	}

	/**
	 * @return the inicioShiva
	 */
	public InformacionPaginadoCreditoShiva getInicioShiva() {
		return inicioShiva;
	}

	/**
	 * @param inicioShiva the inicioShiva to set
	 */
	public void setInicioShiva(InformacionPaginadoCreditoShiva inicioShiva) {
		this.inicioShiva = inicioShiva;
	}

	public ChequeRechazadoControlPaginacion clone() {
		ChequeRechazadoControlPaginacion clone = new ChequeRechazadoControlPaginacion();
		clone.setPaginaActual(this.paginaActual);
		clone.setTotalPaginas(this.totalPaginas);
		clone.setCantPorPagina(this.cantPorPagina);
		clone.setTotalRegistros(this.totalRegistros);
		clone.setCantRegistrosMostrados(this.cantRegistrosMostrados);
		clone.setInhabilitarAnt(this.inhabilitarAnt);
		clone.setInhabilitarSig(this.inhabilitarSig);
		return clone;
	}
	@Override
	public void actualizarPagina() {
		// TODO Mejora hay que pasar todo a Long a INT
		this.setTotalRegistros((new Long(this.cantidadChequeShiva)).intValue() + (new Long(this.cantidadChequeIce)).intValue());
		super.actualizarPagina();
	}
}
