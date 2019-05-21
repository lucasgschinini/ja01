package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.jms.util.definicion.Campo;

public class CampoCompPorOrden implements
		Comparator<Campo> {

	public int compare(Campo o1, Campo o2) {
		return Integer.valueOf(o1.getOrden())
				.compareTo(Integer.valueOf(o2.getOrden()));
	}
}
