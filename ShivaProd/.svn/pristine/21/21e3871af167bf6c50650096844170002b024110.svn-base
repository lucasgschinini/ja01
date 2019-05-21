package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.DetalleAplicacionManualEntrada;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class ComparatorDetalleEntradaAplicacionManual implements Comparator<DetalleAplicacionManualEntrada> {
	@Override
	public int compare(DetalleAplicacionManualEntrada o1, DetalleAplicacionManualEntrada o2) {
		int salida = 0;
		
		if (o1.getNroRegistro() > o2.getNroRegistro()) {
			salida = 1;
		} else if (o1.getNroRegistro() < o2.getNroRegistro()) {
			salida = -1;
		}
		return salida;
	}

	

}
