package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.negocio.dto.ParametroBancoDto;

public class ComparatorParametroBancoDtoParaAcuerdos implements Comparator<ParametroBancoDto> {

	@Override
	public int compare(ParametroBancoDto arg0, ParametroBancoDto arg1) {
		return arg0.getDescripcion().compareTo(arg1.getDescripcion());
	}
}
