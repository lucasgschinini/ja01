/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.ReportePerfilesAuditoriaEntrada;


/**
 * @author Agustina Streule
 * 
 */
public class ComparatorOrdenReportePerfilesAuditoria implements Comparator<ReportePerfilesAuditoriaEntrada> {

	@Override
	public int compare(ReportePerfilesAuditoriaEntrada o1, ReportePerfilesAuditoriaEntrada o2) {

		return o1.getUsuario().compareTo(o2.getUsuario());
	}
	

}
