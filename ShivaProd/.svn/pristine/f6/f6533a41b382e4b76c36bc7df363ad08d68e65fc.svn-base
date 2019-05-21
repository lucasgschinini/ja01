package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamRespWfTarea;

public class ComparatorOrdenShvParamRespWfTarea implements Comparator<ShvParamRespWfTarea> {
//Compara los registros de la parametrica ShvParamRespWfTarea de forma descendente por empresa,segmento,sociedad y sistema.
	@Override
	public int compare(ShvParamRespWfTarea o1, ShvParamRespWfTarea o2) {
		int retornoComparacion = 1;
		if (!Validaciones.isObjectNull(o1.getIdEmpresa()) && !Validaciones.isObjectNull(o2.getIdEmpresa())) {
			if (o1.getIdEmpresa().equals(o2.getIdEmpresa())) {
				if (!Validaciones.isObjectNull(o1.getIdSegmento()) && !Validaciones.isObjectNull(o2.getIdSegmento())) {
					if (o1.getIdSegmento().equals(o2.getIdSegmento())) {
						if (!Validaciones.isObjectNull(o1.getIdSociedad()) && !Validaciones.isObjectNull(o2.getIdSociedad())) {
							if (o1.getIdSociedad().equals(o2.getIdSociedad())) {
								if (!Validaciones.isObjectNull(o1.getSistema()) && !Validaciones.isObjectNull(o2.getSistema())) {
									if (o1.getSistema().compareTo(o2.getSistema()) > 0) {
										retornoComparacion = -1;
									} else {
										retornoComparacion = 1;
									}
								}
							} else {
								if (o1.getIdSociedad().compareTo(o2.getIdSociedad()) > 0) {
									retornoComparacion = -1;
								} else {
									retornoComparacion = 1;
								}
							}
						}
					} else {
						if (o1.getIdSegmento().compareTo(o2.getIdSegmento()) > 0) {
							retornoComparacion = -1;
						} else {
							retornoComparacion = 1;
						}
					}
				}
			} else {
				if (o1.getIdEmpresa().compareTo(o2.getIdEmpresa()) > 0) {
					retornoComparacion = -1;
				} else {
					retornoComparacion = 1;
				}
			}
		}
		return retornoComparacion;
	}
}
