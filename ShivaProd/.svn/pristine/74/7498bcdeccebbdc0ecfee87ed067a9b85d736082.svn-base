/**
 * 
 */
package ar.com.telecom.shiva.presentacion.bean.filtro;

import java.util.ArrayList;
import java.util.Collection;

import ar.com.telecom.shiva.base.constantes.Constantes;


/**
 * @author pablo.m.ibarrola
 *
 */
public class VistaSoporteTareasPendientesFiltro extends Filtro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2808697051584795655L;
	
	private String usuarioCreacion;
	private String perfilAsignacion;
	private String idFiltroSeleccionado;
	
	private Collection<BandejaFiltro> filtros;
	
	public VistaSoporteTareasPendientesFiltro() {
		this.filtros = new ArrayList<BandejaFiltro>();
		this.filtros.add(new BandejaFiltro(Constantes.BANDEJA_ENTRADA_ID_FILTRO_TODOS, "Todas las tareas"));
		this.filtros.add(new BandejaFiltro(Constantes.BANDEJA_ENTRADA_ID_FILTRO_MIS_TAREAS, "Mis tareas"));
		this.filtros.add(new BandejaFiltro(Constantes.BANDEJA_ENTRADA_ID_FILTRO_DERIVADAS, "Tareas derivadas"));
	}
		
	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	/**
	 * @return the perfilAsignacion
	 */
	public String getPerfilAsignacion() {
		return perfilAsignacion;
	}
	/**
	 * @param perfilAsignacion the perfilAsignacion to set
	 */
	public void setPerfilAsignacion(String perfilAsignacion) {
		this.perfilAsignacion = perfilAsignacion;
	}

	public Collection<BandejaFiltro> getFiltros() {
		return filtros;
	}

	public void setFiltros(Collection<BandejaFiltro> filtros) {
		this.filtros = filtros;
	}

	public String getIdFiltroSeleccionado() {
		return idFiltroSeleccionado;
	}

	public void setIdFiltroSeleccionado(String idFiltroSeleccionado) {
		this.idFiltroSeleccionado = idFiltroSeleccionado;
	}
	
}
