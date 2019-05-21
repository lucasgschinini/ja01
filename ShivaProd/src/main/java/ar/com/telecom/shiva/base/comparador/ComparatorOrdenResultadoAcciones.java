/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaAcciones;

/**
 * @author Agustina Streule
 * 
 */
public class ComparatorOrdenResultadoAcciones implements Comparator<ResultadoBusquedaAcciones> {

	@Override
	public int compare(ResultadoBusquedaAcciones o1, ResultadoBusquedaAcciones o2) {
		int retornoComparacion = o1.getIdAccion().compareTo(o2.getIdAccion());
		if (!Validaciones.isNullOrEmpty(o1.getUsuario()) && !Validaciones.isNullOrEmpty(o2.getUsuario())) {
			if (!o1.getUsuario().equals(o2.getUsuario())) {
				retornoComparacion = o1.getUsuario().compareTo(o2.getUsuario());
			} else if (!Validaciones.isNullOrEmpty(o1.getIdPerfil()) && !Validaciones.isNullOrEmpty(o2.getIdPerfil())) {
				if (!o1.getIdPerfil().equals(o2.getIdPerfil())) {
					retornoComparacion = new Long(o1.getIdPerfil()).compareTo(new Long(o2.getIdPerfil()));
				}
			}
		}
		return retornoComparacion;
	}
}
