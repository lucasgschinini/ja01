package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.presentacion.bean.dto.HistoricoDto;


public class ComparatorOrdenHistoricoObservaciones implements Comparator<HistoricoDto> {
	/**
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	@Override
	public int compare(HistoricoDto o1, HistoricoDto o2) {
		return o1.getFecha().compareTo(o2.getFecha());
	}
}
