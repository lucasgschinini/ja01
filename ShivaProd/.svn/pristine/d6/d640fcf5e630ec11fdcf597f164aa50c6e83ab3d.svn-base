package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowMarca;

public class ComparatorShvWfWorkflowMarca implements Comparator<ShvWfWorkflowMarca>{

	public ComparatorShvWfWorkflowMarca() {
	}

	@Override
	public int compare(ShvWfWorkflowMarca o1, ShvWfWorkflowMarca o2) {
		int comparacion = 0;
		if (o1.getFechaCreacion().equals(o2.getFechaCreacion())) {
			comparacion = 0;
		} else  if (o1.getFechaCreacion().before(o2.getFechaCreacion())) {
			comparacion = 1;
		} else {
			comparacion = -1;
		}
		return comparacion;
	}
}
