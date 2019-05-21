package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstadoHist;

public class ShvWfWorkflowEstadoHistCompPorFecha implements
		Comparator<ShvWfWorkflowEstadoHist> {

	public int compare(ShvWfWorkflowEstadoHist o1, ShvWfWorkflowEstadoHist o2) {
		return o1.getFechaModificacion().compareTo(o2.getFechaModificacion());
	}
}
