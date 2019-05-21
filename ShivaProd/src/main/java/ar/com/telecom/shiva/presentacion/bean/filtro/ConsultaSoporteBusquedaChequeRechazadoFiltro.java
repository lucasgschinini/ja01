package ar.com.telecom.shiva.presentacion.bean.filtro;

import java.util.HashSet;
import java.util.Set;




import ar.com.telecom.shiva.presentacion.bean.paginacion.ChequeRechazadoControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.PaginadorAccion;

/**
 * @author u578936
 */
public class ConsultaSoporteBusquedaChequeRechazadoFiltro extends VistaSoporteBusquedaValoresFiltro {
	private static final long serialVersionUID = 20170519L;
	
	private String criterio;
	private String busqueda;
	protected String idsClientes;
	private PaginadorAccion paginadorAccion;
	
	protected ChequeRechazadoControlPaginacion chequeRechazadoControlPaginacion = null;

	

	// Fecha alta --> fecha
	
	
	//protected String nroCheque;
	//protected ControlPaginador controlPaginador;
	/**
	 * 
	 */
	public ConsultaSoporteBusquedaChequeRechazadoFiltro() {

	}

	

	/**
	 * @return the controlPaginador
	 */
//	public ControlPaginador getControlPaginador() {
//		return controlPaginador;
//	}
//
//	/**
//	 * @param controlPaginador the controlPaginador to set
//	 */
//	public void setControlPaginador(ControlPaginador controlPaginador) {
//		this.controlPaginador = controlPaginador;
//	}



	/**
	 * @return the criterio
	 */
	public String getCriterio() {
		return criterio;
	}



	/**
	 * @param criterio the criterio to set
	 */
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}



	/**
	 * @return the idsClientes
	 */
	public String getIdsClientes() {
		return idsClientes;
	}

	/**
	 * @param idsClientes the idsClientes to set
	 */
	public void setIdsClientes(String idsClientes) {
		this.idsClientes = idsClientes;
	}
	
	public void setNroChequeLike(String nroCheque) {
		this.setReferenciaDelValorFiltroLike(nroCheque);
	}
	public String getNroChequeLike() {
		return this.getReferenciaDelValorFiltroLike();
	}
	
	/**
	 * @return the busqueda
	 */
	public String getBusqueda() {
		return busqueda;
	}



	/**
	 * @param busqueda the busqueda to set
	 */
	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	

	/**
	 * @return the chequeRechazadoControlPaginacion
	 */
	public ChequeRechazadoControlPaginacion getChequeRechazadoControlPaginacion() {
		return chequeRechazadoControlPaginacion;
	}



	/**
	 * @param chequeRechazadoControlPaginacion the chequeRechazadoControlPaginacion to set
	 */
	public void setChequeRechazadoControlPaginacion(
			ChequeRechazadoControlPaginacion chequeRechazadoControlPaginacion) {
		this.chequeRechazadoControlPaginacion = chequeRechazadoControlPaginacion;
	}



	public ClienteFiltro obtenerClienteSiebelFiltro() {
		ClienteFiltro clienteSiebelFiltro = new ClienteFiltro();
		clienteSiebelFiltro.setCriterio(this.getCriterio());
		clienteSiebelFiltro.setBusqueda(this.getBusqueda());
		return clienteSiebelFiltro;
	}



	/**
	 * @return the paginadorAccion
	 */
	public PaginadorAccion getPaginadorAccion() {
		return paginadorAccion;
	}



	/**
	 * @param paginadorAccion the paginadorAccion to set
	 */
	public void setPaginadorAccion(PaginadorAccion paginadorAccion) {
		this.paginadorAccion = paginadorAccion;
	}
	
	
	public Set<String> getIdsLegadosParaIce() {
		Set<String> ids = new HashSet<String>();
		for (String idString : this.getIdClientesLegado()) {
			if (idString.length() <= 10) {
				ids.add(idString);
			}
		}
		return ids;
	}
}
